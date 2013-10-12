package ar.proyecto.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

import ar.proyecto.gui.MainWindow;
import ar.proyecto.gui.MiddlePanelDelete;

public class ActionMiddlePanelDeleteOk extends AbstractAction {


	MainWindow gui;
	JFormattedTextField dni;
	JTextField nombreYApellido;	
	JFormattedTextField fechaDeNacimiento;
	JTextField direccion;	
	JTextField telefono;
	JFormattedTextField puntosCarnet;


	public ActionMiddlePanelDeleteOk(MiddlePanelDelete panel, String name) {
		super(name);
		this.gui = panel.getGui();
		this.dni = panel.getTxtDni();
		this.nombreYApellido = panel.getTxtNombreYApellido();
		this.fechaDeNacimiento = panel.getTxtFechaDeNacimiento();
		this.direccion = panel.getTxtDireccion();
		this.telefono = panel.getTxtTelefono();
		this.puntosCarnet = panel.getTxtPuntosCarnet();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("In ActionDeleteOk : received action " + e.getActionCommand());
		if (e.getActionCommand().equals("OK")) {
			System.out.println(" In ActionDeleteOk : Check if action is OK");	
			if (everythigEnteredWell()) {
				System.out.println(" In ActionDeleteOk : everything entered well");
				String request = buildRequest();
				System.out.println(" In ActionDeleteOk : request = " + request);
				gui.sendRequestToController(request, RequestType.DELETE);
				System.out.println("In ActionDeleteOk : sending request to controller : " + request);
			}
		}
	}

	private boolean everythigEnteredWell() {
		// TODO Auto-generated method stub
		boolean result = true;
		if (dni.getText().isEmpty() && nombreYApellido.getText().isEmpty() && fechaDeNacimiento.getText().equals("  -  -    ") && direccion.getText().isEmpty() && telefono.getText().isEmpty() && puntosCarnet.getText().isEmpty()) {
			dni.setBackground(Color.RED);
			nombreYApellido.setBackground(Color.RED);
			fechaDeNacimiento.setBackground(Color.RED);
			direccion.setBackground(Color.RED);
			telefono.setBackground(Color.RED);
			puntosCarnet.setBackground(Color.RED);	
			result = false;
		}
		else if (telefono.getText().length() > 11) {
			telefono.setBackground(Color.RED);
			result = false;
		}
		else if (!puntosCarnet.getText().isEmpty()){
			if (Integer.valueOf(puntosCarnet.getText())> 10 || Integer.valueOf(puntosCarnet.getText())< 0 )
				{
					telefono.setBackground(Color.RED);
					result = false;
				}
			}
		else{
			dni.setBackground(Color.WHITE);
			nombreYApellido.setBackground(Color.WHITE);
			fechaDeNacimiento.setBackground(Color.WHITE);
			direccion.setBackground(Color.WHITE);
			telefono.setBackground(Color.WHITE);
			puntosCarnet.setBackground(Color.WHITE);
			result = true;
		}
		return result;
	}

	private String buildRequest() {
		String request = new String();
		String current = new String();
		request += "DELETE FROM Persona WHERE ";
		current = dni.getText();
		if (!current.isEmpty()) {
			request += " dni = " + current;
			if (!nombreYApellido.getText().isEmpty()  || !fechaDeNacimiento.getText().equals("  -  -    ") || !direccion.getText().isEmpty() || !telefono.getText().isEmpty() || !puntosCarnet.getText().isEmpty()){
				request += " AND ";
			}
		}
		else if (!nombreYApellido.getText().isEmpty()) {
			request += "nombreYApellido = " + nombreYApellido.getText();
			if (!fechaDeNacimiento.getText().equals("  -  -    ") || !direccion.getText().isEmpty() || !telefono.getText().isEmpty() || !puntosCarnet.getText().isEmpty()){
				request += " AND ";
			}
		}
		else if (!fechaDeNacimiento.getText().equals("  -  -    ")) {
			request += "fechaDeNacimiento = " + fechaDeNacimiento.getText();
			if (!direccion.getText().isEmpty() || !telefono.getText().isEmpty() || !puntosCarnet.getText().isEmpty()){
				request += " AND ";
			}
		}
		else if (!direccion.getText().isEmpty()) {
			request += "direccion = " + direccion.getText();
			if (!telefono.getText().isEmpty() || !puntosCarnet.getText().isEmpty()){
				request += " AND ";
			}
		}
		else if (!telefono.getText().isEmpty()) {
			request += "telefono = " + telefono.getText();
			if (!puntosCarnet.getText().isEmpty()){
				request += " AND ";
			}
		}
		else if (!puntosCarnet.getText().isEmpty()){
			request += "puntosCarnet = " + puntosCarnet.getText();
		}
		return request;
	}
}