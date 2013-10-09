package ar.proyecto.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import ar.proyecto.gui.MiddlePanelSelect;

public class ActionSelectOk extends AbstractAction{

	MiddlePanelSelect parent;

	public ActionSelectOk(MiddlePanelSelect parent, String Name) {
		super(Name);
		this.parent = parent;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("In ActionSelectOk : received action " + e.getActionCommand());
		if (e.getActionCommand().equals("OK")) {
			if (everythigEnteredWell()) {
				String request = buildRequest();
				parent.getGui().sendRequestToController(request, RequestType.SELECT);
				System.out.println("In ActionSelectOk : sending request to controller : " + request);
			}
		}
	}

	private boolean everythigEnteredWell() {
		// TODO Auto-generated method stub
		boolean result = true;
		if (parent.getCbTable().getSelectedItem().equals("Multa")) {
			if (parent.getNroMulta().getText().isEmpty()) {
				parent.getNroMulta().setBackground(Color.RED);
				result = false;
			} else {
				parent.getNroMulta().setBackground(Color.WHITE);
			}
		}
		return result;
	}

	private String buildRequest() {
		String request = new String();
		request += "SELECT * FROM " + parent.getCbTable().getSelectedItem();
		if (parent.getCbTable().getSelectedItem().equals("Multa")) {
			request += " WHERE nro_multa = " + parent.getNroMulta().getText();
		}
		return request;
	}

}
