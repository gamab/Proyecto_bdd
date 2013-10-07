package ar.proyecto.gui;

import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MiddlePanelSelect extends MiddlePanel {
	private JLabel from;
	private JComboBox cbTable;
	private JLabel whereNum;
	private JTextField nroMulta;
		
	private JButton ok;

	//Para communicar con la gui
	private MainWindow gui;


	//constructor
	public MiddlePanelSelect() {
		super();		
	}

	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub

	}

	public MainWindow getGui() {
		return gui;
	}

	public void setGui(MainWindow gui) {
		this.gui = gui;
	}

}
