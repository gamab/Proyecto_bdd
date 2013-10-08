package ar.proyecto.gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class TopPanel extends JPanel {
		
	//Para communicar con la gui
		private MainWindow gui;	
		
		public TopPanel() {
			super();
		}

		public void setGui(MainWindow gui) {
			this.gui = gui;
		}
}
