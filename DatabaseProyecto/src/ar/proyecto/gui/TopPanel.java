package ar.proyecto.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ar.proyecto.controller.ActionTopPanelCbox;

public class TopPanel extends JPanel {
	
	//Inicializar el cobobox
	JComboBox combo = new JComboBox();
	//Inicializar los Labeles
	JLabel consultation = new JLabel("Consult : ");
	JPanel container = new JPanel();
	//Para communicar con la gui
	private MainWindow gui;	

	public TopPanel(MainWindow gui) {
		super();
		this.gui = gui;
		this.setLayout(new BorderLayout());
		container.setLayout(new FlowLayout());
		//Implement combobox
		combo.addItem("--"); 
		combo.addItem("SELECT"); 
		combo.addItem("INSERT");
		combo.addItem("DELETE");
		//setea el accion de los Items
		combo.setAction(new ActionTopPanelCbox(this.gui));
		container.add(consultation);
		container.add(combo);
		container.setBackground(gui.getBackground());
//		container.setBackground(new Color(Integer.parseInt("fdffe3",16)));
		this.add(container,BorderLayout.WEST);
		this.setBackground(gui.getBackground());
//		this.setBackground(new Color(Integer.parseInt("fdffe3",16)));

		
	}

	public void setGui(MainWindow gui) {
		this.gui = gui;
	}
}
