package ar.proyecto.gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class MiddlePanel extends JPanel {
	
	//Para communicar con la gui
	private MainWindow gui;	
	
	public MiddlePanel() {
		super();
//		this.setBorder(BorderFactory.createTitledBorder("Command"));
		this.setBackground(new Color(Integer.parseInt("fff2e3",16)));
		this.setVisible(true);
	}
	
	public void setGui(MainWindow gui) {
		this.gui = gui;
	}
	
	public MainWindow getGui() {
		return gui;
	}
}
