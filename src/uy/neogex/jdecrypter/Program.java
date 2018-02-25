/*
 * Copyright (C) 2018 Neogex Studios
 * 
 * Author: Agustin Rojas
 *
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
