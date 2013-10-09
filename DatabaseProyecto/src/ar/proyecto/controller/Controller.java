package ar.proyecto.controller;

import ar.proyecto.gui.MainWindow;
import ar.proyecto.model.DatabaseConnection;

public class Controller {
	//Para utilizar el modelo
	private DatabaseConnection dcModel;
	//Para modificar la GUI
	private MainWindow gui;
	
	
	//Creador
	public Controller() {
		gui = new MainWindow();
		dcModel = new DatabaseConnection();
	}
	
	//Executar una consulta y modificar la gui
	public void sendRequestToModelAndUpdateGui(String request, RequestType type) {
		String result;
		//si el request type esta select
		if (type.equals("SELECT")){
			// resulta toma el resultat de la execucion de la request en el modelo
			result = dcModel.executeSelect(request);
			// update la gui
			gui.setResult(result);
		}
		//si el request type esta insert
		else if (type.equals("INSERT")){
			dcModel.executeInsert(request);
			result = dcModel.executeSelect(request);
			gui.setResult(result);
		}
		//si el request type esta delete
		else if (type.equals("DELETE")){
			dcModel.executeDelete(request);
			result = dcModel.executeSelect(request);
			gui.setResult(result);
		}
	}

}
