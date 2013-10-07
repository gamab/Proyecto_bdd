package ar.proyecto.gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MiddlePanel extends JPanel {
	//Para crear el panel
	protected Color backgroundColor;
	protected int height;
	protected int width;
	
	//Para communicar con la gui
	private MainWindow gui;	
	
	public MiddlePanel() {
		super();
		this.height = 100;
		this.width = 800;
		this.setSize(height, width);
		backgroundColor = Color.CYAN;
	}

	public void paintComponent(Graphics g) {
		g.setColor(backgroundColor);
		g.fillRect(0, 0, width, height);
	}
	
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
	
	public void setGui(MainWindow gui) {
		this.gui = gui;
	}
}
