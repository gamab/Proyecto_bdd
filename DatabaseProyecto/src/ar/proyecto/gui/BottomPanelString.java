package ar.proyecto.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BottomPanelString extends JPanel {
	private JLabel result;

	public BottomPanelString() {
		super();
		this.setBackground(Color.YELLOW);
		this.setBorder(BorderFactory.createTitledBorder("Result"));
		this.setVisible(true);
	}

	public void setResult(String result){
		//modifica el resultado 
		
	}
	
}
