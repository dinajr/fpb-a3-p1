import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDAO {
	
	public static void main(String [] args) {
		conectaDB();
		
	}
	
	public static Connection conectaDB(){
		
		Connection conexao = null;
		
		try {
			Class.forName ("com.mysql.cj.jdbc.Driver");
			conexao = DriverManager.getConnection("jdbc:mysql://localhost/mydb","turanksu","gt86@sw20");
			System.out.println("Conexão estabelecida!");
		} catch (ClassNotFoundException e) {
			
			System.out.println(" Problema JDBC Driver " + e);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return conexao;
	}

}
