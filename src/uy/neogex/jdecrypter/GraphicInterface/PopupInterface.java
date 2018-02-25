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
package uy.neogex.jdecrypter.GraphicInterface;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;


public class PopupInterface {
	public void createAndShowGUI() {
		if (!SystemTray.isSupported()) {
			System.out.println("SystemTray is not supported");
			return;
		}
		final JPopupMenu popup = new JPopupMenu();
		final TrayIcon trayIcon = new TrayIcon(createImage("/uy/neogex/jdecrypter/Images/JDecrypter.png", "JDecrypter v4.0"));
		trayIcon.setToolTip("JDecrypter v4.0");
		final SystemTray tray = SystemTray.getSystemTray();

		JMenuItem titleItem = new JMenuItem("JDecrypter v4.0");
		titleItem.setEnabled(false);
		JMenuItem exitItem = new JMenuItem("Exit");
		
		popup.add(titleItem);
		popup.add(exitItem);
		trayIcon.addMouseListener(new MouseAdapter() {

		    @Override
		    public void mouseReleased(MouseEvent e) {
		        maybeShowPopup(e);
		    }

		    @Override
		    public void mousePressed(MouseEvent e) {
		        maybeShowPopup(e);
		    }

		    private void maybeShowPopup(MouseEvent e) {
		        if (e.isPopupTrigger()) {
		            popup.setLocation(e.getX(), e.getY());
		            popup.setInvoker(popup);
		            popup.setVisible(true);
		        }
		    }
		});
		
		try {
			tray.add(trayIcon);
		} catch (AWTException e) {
			System.out.println("TrayIcon could not be added.");
			return;
		}
		
		exitItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tray.remove(trayIcon);
				System.exit(0);
				
			}
		});
		
	}
	protected static Image createImage(String path, String description) {
		URL imageURL = PopupInterface.class.getResource(path);
		
		if (imageURL == null) {
			System.err.println("Resource not found: "+ path);
			return null;
		}else {
			return (new ImageIcon(imageURL,description)).getImage();
		}
	}


}
