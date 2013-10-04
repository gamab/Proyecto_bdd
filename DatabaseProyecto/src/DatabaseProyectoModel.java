import java.sql.*;

public class DatabaseProyectoModel {

	public static void main(String[] args) {
		System.out.println("Proyecto de Base De Datos I y medio");
		System.out.println("Justine Compagnon | Gabriel Mabille");
		
		Connection connection;
		try {
			Class.forName("org.postgresql.Driver");
			
			System.out.print("Driver O.K.");
			
		} catch ( ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
