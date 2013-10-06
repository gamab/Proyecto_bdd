import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ListIterator;

public class DatabaseProyectoModel {

	public static void main(String[] args) {
		System.out.println("Proyecto de Base De Datos I y medio");
		System.out.println("Justine Compagnon | Gabriel Mabille");

		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("Driver O.K.");

		} catch ( ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			//-----
			//OPEN 
			//-----
			Connection db;

			String url = "jdbc:postgresql://localhost:5432/proyecto";
			String user = "programm";
			String passwd = "programm";
			db = DriverManager.getConnection(url,user,passwd);
			System.out.println("Conneccion abierta.");

			//-----
			//READ
			//-----
			//Creacion de una consultat
			Statement state = db.createStatement();
			//Creacion de la respuesta de la db
			ResultSet result = null;
			//Execucion de una consulta
			result = state.executeQuery("SELECT * FROM Persona");
			//Mostrar por la pantalla la respuesta
			printSelectResult(result);

			//-----
			//CLOSE
			//-----
			db.close();
			System.out.println("Conneccion cerrada.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//Dar el maximo entre dos enteros
	public static int max(int A, int B) {
		if (A > B)
			return A;
		else
			return B;
	}

	//Mostrar una tabla
	public static void printSelectResult(ResultSet result) throws SQLException {
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
		System.out.println(lineSeparator);
		System.out.println(columnsName);
		System.out.println(columnsType);
		System.out.println(lineSeparator);

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
			System.out.println(lineValor); //mostrar la linea
			System.out.println(lineSeparator); //separar la linea de la siguiente
		}
	}

}
