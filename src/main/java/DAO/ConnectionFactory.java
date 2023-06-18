package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static String USUARIO = "root";
    private static String SENHA = "root";
    private static String URL = "jdbc:mysql://localhost:3306/mydb";
	   public static void connectTest() {
			 try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA)) {
			     System.out.println("Database connected!");
			 } catch (SQLException e) {
			     System.out.println("Conexão com o banco falhou!");
	         }
}
}