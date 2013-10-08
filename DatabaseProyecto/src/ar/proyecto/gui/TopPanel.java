package ar.proyecto.gui;

import java.awt.Color;

import javax.swing.JPanel;

public class TopPanel extends JPanel {
		
	//Para communicar con la gui
		private MainWindow gui;	
		
		public TopPanel() {
			super();
			this.setBackground(new Color(Integer.parseInt("fdffe3",16)));
		}

		public void setGui(MainWindow gui) {
			this.gui = gui;
		}
}
