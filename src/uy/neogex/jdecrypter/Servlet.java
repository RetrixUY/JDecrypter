/* JDecrypter
 * Copyright (C) 2018 Neogex Studios
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package uy.neogex.jdecrypter;

import java.io.IOException;
import java.security.*;
import java.util.*;
import javax.crypto.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.eclipse.jetty.server.Request;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;
import uy.neogex.jdecrypter.Model.EncryptedData;
import uy.neogex.jdecrypter.Model.Link;

public class Servlet extends HttpServlet {
	Configuration cfg;

	public Servlet() {
		super();    
	}
	
	@SuppressWarnings("deprecation")
	public void init(){

		cfg = new Configuration();
		cfg.setClassForTemplateLoading(this.getClass(), "Templates");
		cfg.setIncompatibleImprovements(new Version(2, 3, 20));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);  

	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			EncryptedData encryptedData = new EncryptedData(request);
			List<Link> links = encryptedData.Decrypt();
			Map<String, Object> input = new HashMap<String, Object>();
			input.put("links", links);
			Template template = cfg.getTemplate("Template.ftl");
			template.process(input, response.getWriter());
		}
		catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | TemplateException e) {
			e.printStackTrace();
		}
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		if(req instanceof Request){
			Request jReq = ((Request)req);
			jReq.setQueryEncoding("ISO-8859-1");
		}
		chain.doFilter(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("ISO-8859-1");
		response.setCharacterEncoding("ISO-8859-1");
		doGet(request, response);
	}


}
