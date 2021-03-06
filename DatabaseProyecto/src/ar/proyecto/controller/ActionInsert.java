package ar.proyecto.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import ar.proyecto.gui.MiddlePanelInsert;

public class ActionInsert extends AbstractAction {

	MiddlePanelInsert panel;

	public ActionInsert(MiddlePanelInsert panel, String Name) {
		super(Name);
		this.panel = panel;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getActionCommand().equals("OK")) {
			System.out.println("En ActionInsert : fue detectado un clic sobre el button OK");
			if (everythigEnteredWell()) {
				String request = new String();
				request = buildRequest();
				System.out.println("En ActionInsert : fue enviada una consulta al controllador : " + request);
				panel.getGui().sendRequestToController(request, RequestType.INSERT);
			}
		}
	}
	
	private boolean isFilledWithSpace(String txt) {
		boolean res = true;
		for (int i=0;i<txt.length();i++) {
			if (txt.charAt(i)!=' ') {
				res = false;
			}
		}
		return res;
	}

	private boolean everythigEnteredWell() {
		boolean allOk = true;
		//Check NroPatente
		if (isFilledWithSpace(panel.getTxtNroPatente().getText())) {
			System.out.println("En ActionInsert : NroPatente no esta bien");
			panel.getTxtNroPatente().setBackground(Color.red);
			allOk = false;
		} else {
			panel.getTxtNroPatente().setBackground(Color.white);			
		}
		//Check Typo
		if (panel.getTxtTypo().getText().isEmpty()) {
			System.out.println("En ActionInsert : Typo no esta bien");
			panel.getTxtTypo().setBackground(Color.red);
			allOk = false;
		} else {
			panel.getTxtTypo().setBackground(Color.white);			
		}
		//Check Modelo
		if (panel.getTxtModelo().getText().isEmpty()) {
			System.out.println("En ActionInsert : Modelo no esta bien");
			panel.getTxtModelo().setBackground(Color.red);
			allOk = false;
		} else {
			panel.getTxtModelo().setBackground(Color.white);			
		}
		//Check Ano
		if (panel.getTxtAno().getText().isEmpty()) {
			System.out.println("En ActionInsert : Ano no esta bien");
			panel.getTxtAno().setBackground(Color.red);
			allOk = false;
		} else {
			panel.getTxtAno().setBackground(Color.white);			
		}
		return allOk;
	}
	private String buildRequest() {
		String request = new String();
		request += "INSERT INTO Vehiculo VALUES(";
		request += "'" + panel.getTxtNroPatente().getText() + "',";
		request += "'" + panel.getTxtTypo().getText() + "',";
		request += "'" + panel.getCboxMarca().getSelectedItem() + "',";
		request += "'" + panel.getTxtModelo().getText() + "',";	
		request += "'" + panel.getTxtAno().getText() + "'";
		request += ")";

		return request;
	}

}
