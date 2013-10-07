import ar.proyecto.controller.Controller;
import ar.proyecto.model.DatabaseConnection;

public class Main {

	public static void main(String[] args) {
		System.out.println("Proyecto de Base De Datos I y medio");
		System.out.println("Justine Compagnon | Gabriel Mabille");

		//quicklyTestModelo();
		Controller ctrl = new Controller();

	}
	
	public static void quicklyTestModelo () {
		DatabaseConnection Dc = new DatabaseConnection();

		String select = "Select * FROM Persona";
		System.out.println("Execute " + select);
		System.out.println(Dc.executeSelect(select));
		String insert = "INSERT INTO Vehiculo VALUES ('42YA58','Moto','FORD','Fiesta',2007)";
		System.out.println("Execute " + insert);
		System.out.println(Dc.executeInsert(insert));


		Dc.closeConnection();
	}
}