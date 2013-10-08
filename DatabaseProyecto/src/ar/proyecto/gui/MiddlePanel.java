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
		this.setBorder(BorderFactory.createTitledBorder("Command"));
		this.setBackground(Color.CYAN);
		this.setVisible(true);
	}
	
	public void setGui(MainWindow gui) {
		this.gui = gui;
	}
}
