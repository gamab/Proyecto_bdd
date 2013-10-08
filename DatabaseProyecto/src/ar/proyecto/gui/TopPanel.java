package ar.proyecto.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TopPanel extends JPanel {
	JComboBox combo = new JComboBox();
	JLabel consultation = new JLabel("Consult : ");
	JPanel container = new JPanel();

	//Para communicar con la gui
	private MainWindow gui;	

	public TopPanel() {
		super();
		this.setLayout(new BorderLayout());
		container.setLayout(new FlowLayout());
		combo.addItem("--"); 
		combo.addItem("SELECT"); 
		combo.addItem("INSERT");
		combo.addItem("DELETE");
		container.add(consultation);
		container.add(combo);
		container.setBackground(new Color(Integer.parseInt("fdffe3",16)));
		this.add(container,BorderLayout.WEST);
		this.setBackground(new Color(Integer.parseInt("fdffe3",16)));

		
	}

	public void setGui(MainWindow gui) {
		this.gui = gui;
	}
}
