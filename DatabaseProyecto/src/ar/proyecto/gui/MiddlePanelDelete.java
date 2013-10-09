package ar.proyecto.gui;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.sql.Date;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class MiddlePanelDelete extends MiddlePanel {
	private JLabel into;

	
	private JLabel fromPersona;
	private JLabel where;
	private JLabel dni;
	private JFormattedTextField txtDni;
	private JLabel nombreYApellido;
	private JTextField txtNombreYApellido;	
	private JLabel fechaDeNacimiento;
	private JFormattedTextField txtFechaDeNacimiento;
	private JLabel direccion;
	private JTextField txtDireccion;	
	private JLabel telefono;
	private JTextField txtTelefono;
	private JLabel puntosCarnet;
	private JFormattedTextField txtPuntosCarnet;


	private JButton ok;


	//constructor
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MiddlePanelDelete(MainWindow gui) {
		super(gui);
		//Inicializar los Labeles

		fromPersona = new JLabel("From Persona");
		where = new JLabel("Where");
		dni = new JLabel("dni");
		nombreYApellido = new JLabel("nombre y apellido");	
		fechaDeNacimiento = new JLabel("fecha de nacimiento");
		direccion = new JLabel("direccion");	
		telefono = new JLabel("telefono");
		puntosCarnet = new JLabel("punto carnet");
		
		//Inicializar los textFields
		txtDni = new JFormattedTextField(NumberFormat.getIntegerInstance());
		txtDni.setColumns(5);
		txtNombreYApellido = new JTextField();
		txtNombreYApellido.setColumns(80);
		try {
			txtFechaDeNacimiento = new JFormattedTextField( new MaskFormatter("##'-##'-####"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			txtFechaDeNacimiento = new JFormattedTextField();
		}		
		txtFechaDeNacimiento.setColumns(10);
		txtDireccion = new JTextField();
		txtDireccion.setColumns(30); 
		txtTelefono = new JTextField();	
		txtTelefono.setColumns(11);
		txtPuntosCarnet = new JFormattedTextField(NumberFormat.getIntegerInstance());
		txtPuntosCarnet.setColumns(2);
		
		//Agregar todo al panel
		this.setLayout(new FlowLayout());
		this.add(into);
		this.add(dni);
		this.add(txtNombreYApellido);
		this.add(txtDni);
		this.add(direccion);
		this.add(txtDireccion);
		this.add(txtFechaDeNacimiento);
		this.add(telefono);
		this.add(txtTelefono);
		this.add(puntosCarnet);
		this.add(txtPuntosCarnet);
		
		public JFormattedTextField getTxtDni() {
			return txtNroDni;
		}
		
		public JFormattedTextField getTxtNombreYApellido() {
			return txtNombreYApellido;
		}
		
		public JFormattedTextField getTxtFechaDeNacimiento() {
			return txtFechaDeNacimiento;
		}

		public JTextField getTxtDireccion() {
			return txtDireccion;
		}
		
		public JTextField getTxtTelefono() {
			return txtTelefono;
		}

		public JTextField getTxtPuntosCarnet() {
			return txtPuntosCarnet;
		}

	}

}
