package ar.proyecto.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

import ar.proyecto.gui.MainWindow;

public class ActionMiddlePanelSelectOk extends AbstractAction{

	MainWindow gui;
	JFormattedTextField nroMulta;
	JComboBox table;

	public ActionMiddlePanelSelectOk(MainWindow gui,JFormattedTextField nroMulta, JComboBox table, String name) {
		super(name);
		this.gui = gui;
		this.nroMulta = nroMulta;
		this.table = table;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("En ActionMiddlePanelSelectOk : accion recibida " + e.getActionCommand());
		if (e.getActionCommand().equals("OK")) {
			if (everythigEnteredWell()) {
				String request = buildRequest();
				System.out.println("En ActionMiddlePanelSelectOk : enviando request al controllador : " + request);
				gui.sendRequestToController(request, RequestType.SELECT);
			}
		}
	}

	private boolean everythigEnteredWell() {
		// TODO Auto-generated method stub
		boolean result = true;
		if (table.getSelectedItem().equals("Multa")) {
			if (nroMulta.getText().isEmpty()) {
				nroMulta.setBackground(Color.RED);
				result = false;
			} else {
				nroMulta.setBackground(Color.WHITE);
			}
		}
		return result;
	}

	private String buildRequest() {
		String request = new String();
		request += "SELECT * FROM " + table.getSelectedItem();
		if (table.getSelectedItem().equals("Multa")) {
			request += " WHERE nro_multa = " + nroMulta.getText();
		}
		return request;
	}

}
