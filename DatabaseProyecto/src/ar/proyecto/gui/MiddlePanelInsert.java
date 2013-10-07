package ar.proyecto.gui;

import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MiddlePanelInsert extends MiddlePanel {
	private JLabel into;
	
	private JLabel nroPatente;
	private JTextField txtNroPatente;
	private JLabel typo;
	private JTextField txtTypo;	
	private JLabel modelo;
	private JTextField txtModelo;	
	private JLabel ano;
	private JTextField txtAno;	

	private JComboBox cboxMarca;
	
	private JButton ok;
	
	
	
	//constructor
	public MiddlePanelInsert() {
		super();		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		
	}


}
