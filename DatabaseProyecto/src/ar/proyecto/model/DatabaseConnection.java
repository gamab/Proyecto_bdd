package ar.proyecto.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ListIterator;

public class DatabaseConnection {

	private Connection db;

	//Construction opening the connection
	public DatabaseConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("Driver O.K.");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			//-----
			//OPEN 
			//-----

			String url = "jdbc:postgresql://localhost:5432/proyecto";
			String user = "programm";
			String passwd = "programm";
			db = DriverManager.getConnection(url,user,passwd);
			System.out.println("Conneccion abierta.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//Need to be called to close the connection
	public void closeConnection() {
		try {
			//-----
			//CLOSE
			//-----
			db.close();
			System.out.println("Conneccion cerrada.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//---------------------
	//----  EXECUTION  ----
	//---------------------

	//#### SELECT ####
	//Hacer un Select y devolver la tabla en un String
	public String executeSelect(String select) {
		System.out.println("In DatabaseConnection : received a select " + select);
		String stringResult = new String();
		try {
			//Creacion de una consultat
			Statement state = db.createStatement();
			//Execucion de una consulta Select
			ResultSet result = state.executeQuery(select);
			//Eviar la respuesta		
			stringResult = getSelectResult(result);	
		} catch (SQLException e) {
			stringResult = e.getMessage();
		}
		//Eviar la respuesta		
		return stringResult;		
	}

	//Hacer un Select y devolver el resultado sin tratamiento
	public ResultSet executeSelectBasic(String select) throws SQLException {
		//Creacion de una consultat
		Statement state = db.createStatement();
		//Eviar la respuesta		
		return state.executeQuery(select);		
	}

	//#### DELETE ####
	//Hacer un Delete y devolver la tabla en un String
	public String executeDelete(String delete) {
		System.out.println("In DatabaseConnection : received a delete " + delete);
		String stringResult = new String();
		try {
			//Creacion de una consultat
			Statement state = db.createStatement();
			//Execucion de una consulta Delete
			int result = state.executeUpdate(delete);
			//Leemos la respuesta
			stringResult = getDeleteResult(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			stringResult = e.getMessage();
		}
		//Eviar la respuesta		
		return stringResult;		
	}

	//#### INSERT ####
	public String executeInsert(String insert) {
		System.out.println("In DatabaseConnection : received an insert " + insert);
		String stringResult = new String();
		try {
			//Creacion de una consultat
			Statement state = db.createStatement();
			//Execucion de una consulta Insert
			int result = state.executeUpdate(insert);
			//Leemos la respuesta
			stringResult = getInsertResult(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			stringResult = e.getMessage();
		}
		//Eviar la respuesta		
		return stringResult;
	}

	//-----------------------------
	//----  RESULT CONVERSION  ----
	//-----------------------------


	//#### SELECT ####
	//Dar el maximo entre dos enteros
	private static int max(int A, int B) {
		if (A > B)
			return A;
		else
			return B;
	}

	//Cambiar la table de un Select en un String
	private String getSelectResult(ResultSet result) throws SQLException {
		String resultString = new String();
		//Recuperacion de los metadatas
		ResultSetMetaData resultMeta = result.getMetaData();
		String currentColName = new String();
		String currentColType = new String();
		String columnsName = new String(); //linea con todos los nombres de las colonas
		String columnsType = new String(); //linea con todos los typos de las colonas
		String lineSeparator = new String(); //linea que separa cada linea
		int maxWidth = 0; //contiene el ancho maximo entre typo y titulo colona
		int nbSpace = 8; //contiene el numero de espacio que vamos a agregar al titulo o al typo
		int currentSize = 0;//contiene el ancho de la colona actual
		String halfSpace = new String();
		//creacion de halfSpace
		for (int i=1; i<=nbSpace/2; i++) {
			halfSpace += ' ';
		}
		ArrayList<Integer> columnsWidth = new ArrayList<Integer>(); //contiene el ancho de cada columna

		//-------------------------------------------
		// Mostrar los titulos y los typos de data
		//-------------------------------------------
		for (int i=1; i <= resultMeta.getColumnCount(); i ++) {
			//leer el typo y el titulo
			currentColName = resultMeta.getColumnName(i);
			currentColType = resultMeta.getColumnTypeName(i);
			//buscar el ancho maximo entre los dos
			maxWidth = max(currentColType.length(),currentColName.length());
			//guardar el tamano de la celda actual (agregamos nbSpace porque empeza y acaba con este numero de espacios)
			columnsWidth.add(maxWidth + nbSpace);
			columnsName = columnsName + "*" + halfSpace + currentColName;
			//ponemos ' ' al titulo hasta que sea tan grande como el ancho maximo entre typo y titulo
			for (int j=(currentColName.length()); j<maxWidth; j++) {
				columnsName += " ";
			}
			columnsName += halfSpace + "*";

			columnsType = columnsType + "*" + halfSpace + currentColType.toUpperCase();
			//ponemos ' ' al typo hasta que sea tan grande como el ancho maximo entre typo y titulo
			for (int j=(currentColType.length()); j<maxWidth; j++) {
				columnsType += " ";
			}
			columnsType += halfSpace + "*";
		}
		//creamos el separator entre las lineas
		//por eso necessitamos un iterator
		ListIterator<Integer> it = columnsWidth.listIterator();
		while (it.hasNext()) {
			currentSize = it.next();
			for (int s=0; s<currentSize+2;s++) {
				lineSeparator += "*";
			}
		}
		//Mostrar titulo y typo
		resultString += lineSeparator + "\n";
		resultString += columnsName + "\n";
		resultString += columnsType + "\n";
		resultString += lineSeparator + "\n";

		//-------------------------------------------
		//Mostrar los valores ahora
		//-------------------------------------------
		//cambiar el separatorDeLineas
		lineSeparator = lineSeparator.replace('*', '-');
		String currentValue = new String();
		String lineValor = new String();
		//hasta que no haya mas lineas leer las valores y ponerlas en currentValue
		while (result.next()) {
			it = columnsWidth.listIterator(); //reinicializar el iterator de la lista para leerlo de nuevo
			lineValor = ""; //reinicializar la linea que vamos a leer
			for (int i=1; i <= resultMeta.getColumnCount(); i ++) {
				currentValue = result.getString(i); //leer la valor de la celda
				lineValor += "|";
				if (it.hasNext()) {
					currentSize = it.next(); //leer el ancho de la celda
					//si el ancho de la valor es menor al ancho de la celda completar con ' '
					if (currentValue.length() <= currentSize) {
						lineValor += currentValue;
						for (int j=(currentValue.length()); j<currentSize; j++) {
							lineValor += " ";
						}
						//sino cortar la valor
					} else {
						lineValor += currentValue.substring(0,currentSize);
					}
					lineValor += "|";
				}
			}
			resultString += lineValor + "\n"; //mostrar la linea
			resultString += lineSeparator + "\n"; //separar la linea de la siguiente
		}

		return resultString;
	}

	//#### DELETE ####
	//Convert el resultado de un delete en un String
	private String getDeleteResult(int result) {
		if (result==0) {
			return "Row doesn't exist";
		}
		else if (result==1) {
			return "Row deleted";
		}
		else {
			return "Erreur " + result + " during delete";
		}
	}


	//#### INSERT ####
	//Convert el resultado de un delete en un String
	private String getInsertResult(int result) {
		if (result==0) {
			return "Could not make insertion";
		}
		else if (result==1) {
			return "Insertion has been made";
		}
		else {
			return "Erreur " + result + " during insert";
		}
	}
}
