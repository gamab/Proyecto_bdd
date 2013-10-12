package ar.proyecto.gui;

import java.awt.FlowLayout;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import ar.proyecto.controller.ActionInsert;
import ar.proyecto.controller.ActionMiddlePanelDeleteOk;

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

		fromPersona = new JLabel("FROM Persona");
		where = new JLabel("WHERE");
		dni = new JLabel("Dni");
		nombreYApellido = new JLabel("Nombre y Apellido");	
		fechaDeNacimiento = new JLabel("Fecha de Nacimiento");
		direccion = new JLabel("Direccion");	
		telefono = new JLabel("Telefono");
		puntosCarnet = new JLabel("Punto Carnet");
		
		//Inicializar los textFields
		NumberFormat formatInteger = NumberFormat.getIntegerInstance();
		formatInteger.setGroupingUsed(false);
		txtDni = new JFormattedTextField(formatInteger);
		txtDni.setColumns(5);
		txtNombreYApellido = new JTextField();
		txtNombreYApellido.setColumns(35);
		try {
			txtFechaDeNacimiento = new JFormattedTextField( new MaskFormatter("####'-##'-##"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			txtFechaDeNacimiento = new JFormattedTextField();
		}		
		txtFechaDeNacimiento.setColumns(7);
		txtDireccion = new JTextField();
		txtDireccion.setColumns(40); 
		txtTelefono = new JTextField();	
		txtTelefono.setColumns(11);
		txtPuntosCarnet = new JFormattedTextField(NumberFormat.getIntegerInstance());
		txtPuntosCarnet.setColumns(2);
		
		//Inicializar el Button
		ok = new JButton(new ActionMiddlePanelDeleteOk(this,"OK"));
		
		//Agregar todo al panel
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.add(fromPersona);
		this.add(where);
		this.add(dni);
		this.add(txtDni);
		this.add(nombreYApellido);
		this.add(txtNombreYApellido);
		this.add(direccion);
		this.add(txtDireccion);
		this.add(fechaDeNacimiento);
		this.add(txtFechaDeNacimiento);
		this.add(telefono);
		this.add(txtTelefono);
		this.add(puntosCarnet);
		this.add(txtPuntosCarnet);
		this.add(ok);
	}
		
		public JFormattedTextField getTxtDni() {
			return txtDni;
		}
		
		public JTextField getTxtNombreYApellido() {
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

		public JFormattedTextField getTxtPuntosCarnet() {
			return txtPuntosCarnet;
		}

	}


