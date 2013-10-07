package ar.proyecto.controller;

import ar.proyecto.gui.MainWindow;
import ar.proyecto.model.DatabaseConnection;

public class Controller {
	//Para utilizar el modelo
	DatabaseConnection model;
	//Para modificar la GUI
	MainWindow gui;
	
	
	//Creador
	public Controller() {
		
	}
	
	//Executar una consulta y modificar la gui
	public void sendRequestToModelAndUpdateGui(String request, RequestType type) {
		
	}
}
