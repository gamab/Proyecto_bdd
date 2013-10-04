import java.sql.*;

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
			Connection db;
			
			String url = "jdbc:postgresql://localhost:5432/proyecto";
			String user = "postgres";
			String passwd = "root";
			db = DriverManager.getConnection(url,user,passwd);
			System.out.println("Conneccion abierta.");
			
			db.close();
			System.out.println("Conneccion cerrada.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
