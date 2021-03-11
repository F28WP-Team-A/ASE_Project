package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import f21as_coursework.*;
import views.*;

public class CafeGUIController {
	
	private CafeGUIView gui;
	
	
	public CafeGUIController(CafeGUIView gui) {
		this.gui = gui;
		
		gui.addSetListener(new UpdateListener());
	}
	
	public class UpdateListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println("Action listener working");
		}
	}
	

}
