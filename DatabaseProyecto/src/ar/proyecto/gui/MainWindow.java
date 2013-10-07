package ar.proyecto.gui;

import java.sql.ResultSet;

import javax.swing.JFrame;

import ar.proyecto.controller.Controller;
import ar.proyecto.controller.RequestType;

public class MainWindow extends JFrame {
	
	//Para construir la ventana
	private TopPanel panelTop;
	private MiddlePanel panelMiddle;
	private BottomPanel panelBottom;
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
