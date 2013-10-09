package ar.proyecto.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JComboBox;

import ar.proyecto.gui.MainWindow;

public class ActionTopPanelCbox extends AbstractAction {

	// para modificar el panel central de la gui
	private MainWindow gui;
	
	public ActionTopPanelCbox (MainWindow gui){
		super();
		this.gui = gui;

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

		//variable
		JComboBox combobox;
		String result;
		combobox = (JComboBox) arg0.getSource();
		result = (String) combobox.getSelectedItem();
		System.out.println("In ActionTopPanelCbox : Received Action " + result);
		if (result.equals("--")) {
			gui.setMiddlePanel(RequestType.NONE);
		}	
		else if (result.equals("SELECT")) {
			gui.setMiddlePanel(RequestType.SELECT);
		}
		else if (result.equals("INSERT")){
			gui.setMiddlePanel(RequestType.INSERT);
		}
		else if (result.equals("DELETE")){
			gui.setMiddlePanel(RequestType.DELETE);
		}

	}
}
