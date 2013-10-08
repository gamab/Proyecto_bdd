package ar.proyecto.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ar.proyecto.controller.Controller;
import ar.proyecto.controller.RequestType;

public class MainWindow extends JFrame {
	
	//Para construir la ventana
	private JPanel panelTop;
	private int heightpt = 120;
	private JPanel panelMiddle;
	private int heightpm = 120;
	private JPanel panelBottom;
	private int heightpb = 360;
	private JPanel panelContent;
	private String title;
	private int width;
	private int height;
	
	//Para comunicar con el modelo
	private Controller controller;
	
	
	//-------------------------------------
	//Para construir y modificar la ventana
	//-------------------------------------
	//Constructor
	public MainWindow() {
		super();
		title = new String("BDD Proyecto");
		width = 800;
		height = 600;
		this.setTitle(title);
		this.setSize(new Dimension(width,height));
		this.setLocationRelativeTo(null);
		
		
		panelContent = new JPanel();
		panelContent.setPreferredSize(new Dimension(width, height));
		panelContent.setBackground(Color.white);
		panelContent.setLayout(new BorderLayout());

		
		panelTop = new TopPanel();
		panelTop.setPreferredSize(new Dimension(width,heightpt));
		panelMiddle = new MiddlePanel();
		panelMiddle.setPreferredSize(new Dimension(width,heightpm));
		panelBottom = new BottomPanelString();
		panelBottom.setPreferredSize(new Dimension(width,heightpb));

		panelContent.add(panelTop,BorderLayout.NORTH);
		panelContent.add(panelMiddle,BorderLayout.CENTER);
		panelContent.add(panelBottom,BorderLayout.SOUTH);
		
		
		this.setContentPane(panelContent);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}
	
	//Para cambiar el panel del medio
	public void setMiddlePanel(MiddlePanel panel) {
		
	}
	//Para cambiar el panel del medio
	public void setMiddlePanel(RequestType type) {
		
	}
	
	//Para mostrar un resultado en el panel de abajo
	public void setResult(String result) {
		
	}
	//Para mostrar un resultado en forma de una tablita
	public void setResult(ResultSet result) {
		
	}
	
	//----------------------------
	//Para comunicar con el modelo
	//----------------------------
	public void setController(Controller ctrllr) {
		this.controller = ctrllr;
	}
	
	public void sendRequestToController(String request, RequestType type) {
		
	}
	
}
