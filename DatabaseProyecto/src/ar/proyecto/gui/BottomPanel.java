package ar.proyecto.gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public abstract class BottomPanel extends JPanel {
	
	
	//Para crear el panel
		protected Color backgroundColor;
		protected int height;
		protected int width;
		
		protected BottomPanel() {
			
		}

		protected abstract void paintComponent(Graphics g);
		
		public Color getBackgroundColor() {
			return backgroundColor;
		}

		public void setBackgroundColor(Color backgroundColor) {
			this.backgroundColor = backgroundColor;
		}

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}

		public int getWidth() {
			return width;
		}

		public void setWidth(int width) {
			this.width = width;
		}
		
		public void setSize(int height, int width) {
			this.height = height;
			this.width = width;
		}
	
}
