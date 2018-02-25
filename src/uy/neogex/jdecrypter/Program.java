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

import javax.swing.SwingUtilities;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

import uy.neogex.jdecrypter.GraphicInterface.PopupInterface;

public class Program {

    static Server server = new Server(9666);
    public static void main(String[] args) throws Exception {
        System.setProperty("org.eclipse.jetty.util.log.class", "org.eclipse.jetty.util.log.StdErrLog");
        System.setProperty("org.eclipse.jetty.LEVEL", "OFF");
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
        handler.addServletWithMapping(Servlet.class,"/flash/addcrypted2");
        server.start();
        SwingUtilities.invokeLater(new Runnable() {
        	public void run() {
        		new PopupInterface().createAndShowGUI();
        	}
        });
        
        
        server.join();
    }
    
}
