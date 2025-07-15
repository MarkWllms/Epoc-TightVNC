//
//  Copyright (C) 1999 AT&T Laboratories Cambridge.  All Rights Reserved.
//  Copyright (C) 2002-2006 Constantin Kaplinsky.  All Rights Reserved.
//
//  This is free software; you can redistribute it and/or modify
//  it under the terms of the GNU General Public License as published by
//  the Free Software Foundation; either version 2 of the License, or
//  (at your option) any later version.
//
//  This software is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU General Public License for more details.
//
//  You should have received a copy of the GNU General Public License
//  along with this software; if not, write to the Free Software
//  Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307,
//  USA.
//

import java.awt.*;
import java.awt.event.*;

//
// The panel which implements the user authentication scheme
//

class HostPanel extends Panel implements ActionListener {
	
  public int port;
  private TextField hostField = new TextField(20);
  private Choice portField = new Choice();
  private Button okButton = new Button("OK");

  //
  // Constructor.
  //

  public HostPanel(VncViewer parent) 
  {
	GridBagLayout gridbag = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();

    setLayout(gridbag);
	
	Label titleLabel = new Label("VNC Host", Label.CENTER);
    titleLabel.setFont(new Font("Helvetica", Font.BOLD, 18));
	gbc.gridwidth = GridBagConstraints.REMAINDER;
    add(titleLabel,gbc);
	   

    gbc.fill = GridBagConstraints.NONE;
	gbc.anchor = GridBagConstraints.NORTH;
    gbc.gridwidth = 1;
    gbc.insets = new Insets(2,2,2,2);
	add(new Label("Host:", Label.CENTER),gbc);
	
	gbc.fill = GridBagConstraints.BOTH;
	gbc.gridwidth = GridBagConstraints.REMAINDER;
	add(hostField,gbc);
    hostField.addActionListener(this);
	gbc.gridwidth = 1;
	gbc.fill = GridBagConstraints.NONE;
	add(new Label("Port:", Label.CENTER),gbc);
	
	gbc.gridwidth = GridBagConstraints.REMAINDER;
	gbc.fill = GridBagConstraints.BOTH;
	add(portField,gbc);
	portField.addItem("0");
	portField.addItem("1");
	portField.addItem("2");
	portField.addItem("3");
	portField.addItem("4");
	portField.addItem("5");
	portField.addItem("6");
	portField.addItem("7");
	portField.addItem("8");
	portField.addItem("9");
	portField.addItem("10");
    //portField.addActionListener(this);

    gbc.ipadx = 30;
    gridbag.setConstraints(okButton,gbc);
    add(okButton,gbc);
	okButton.addActionListener(this);
  }
  
    
    public synchronized void actionPerformed( ActionEvent evt )  
    {  
		setVisible(false);
		notify();
    }  
         
  
  public synchronized String getHost() throws Exception
  {
 
	 if (port>=5900 && port <=5910) 
		portField.select(port-5900);
	
	setVisible(true);
	hostField.requestFocus();
	
    try {
      wait();
    } catch (InterruptedException e) { }
	
	port = 5900+portField.getSelectedIndex();
    return hostField.getText();
  }
}
