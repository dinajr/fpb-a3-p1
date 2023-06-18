package main;
import main.DBPreview;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public abstract class DBManager {
    private static String USUARIO = "root";
    private static String SENHA = "root";
    private static String URL = "jdbc:mysql://localhost:3306/mydb";
    private static Scanner scanner;
    static void connectTest() {
		 try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA)) {
		     System.out.println("Database connected!");
		 } catch (SQLException e) {
		     System.out.println("Conexão com o banco falhou!");
         }
   }
	//Abaixo fica os métodos de manipulação do banco SQL:
	protected static void exibirSeries() {
        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM series")) {

            String exibir = "<html>===== SÉRIES =====<br>";
            while (resultSet.next()) {
                long id = resultSet.getLong("idseries");
                String nome = resultSet.getString("nome");
                int temporadas = resultSet.getInt("temporadas");
                int anoLancamento = resultSet.getInt("anolancamento");
                String plataforma = resultSet.getString("streamingplat");

                exibir += "ID: " + id + "<br>";
                exibir += "Nome: " + nome + "<br>";
                exibir += "Temporadas: " + temporadas + "<br>";
                exibir += "Ano de Lançamento: " + anoLancamento + "<br>";
                exibir += "Plataforma: " + plataforma + "<br>";
                exibir += "------------------------------" + "<br>";
            }
            JOptionPane.showMessageDialog(null, exibir);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	 protected static void editarSerie() {
	        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA);
	             PreparedStatement preparedStatement = connection.prepareStatement(
	                     "UPDATE series SET nome = ?, temporadas = ?, anolancamento = ?, streamingplat = ? WHERE idseries = ?")) {

	          

	            long id = Long.parseLong(JOptionPane.showInputDialog("Digite o ID da série que deseja editar: ")); 
	            DBPreview editPreview = new DBPreview();
	            String nome = JOptionPane.showInputDialog("Digite o novo nome da série: ");
	            editPreview.setNome(nome);

	            int temporadas = Integer.parseInt(JOptionPane.showInputDialog("Digite o novo número de temporadas: "));
	            editPreview.setTemporadas(temporadas);
	            	
	           int anoLancamento = Integer.parseInt(JOptionPane.showInputDialog("Digite o novo ano de lançamento: "));
	           editPreview.setAnoLancamento(anoLancamento);
	           
	           String plataforma = JOptionPane.showInputDialog("Digite a nova plataforma de streaming: ");
	           editPreview.setPlataforma(plataforma);

	            preparedStatement.setString(1, nome);
	            preparedStatement.setInt(2, temporadas);
	            preparedStatement.setInt(3, anoLancamento);
	            preparedStatement.setString(4, plataforma);
	            preparedStatement.setLong(5, id);
	            boolean confirmation = editPreview.previewSeries();
	            if (confirmation) {
	            int linhasAfetadas = preparedStatement.executeUpdate();
	            if (linhasAfetadas > 0) {
	                JOptionPane.showMessageDialog(null, "Série atualizada com sucesso!");
	            } else {
	                JOptionPane.showMessageDialog(null, "Falha ao atualizar série. Verifique o ID informado.");
	            }
	            }
	            else {
	            	preparedStatement.cancel();
	            	JOptionPane.showMessageDialog(null, "Mudança cancelada!");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	    }
	 protected static void adicionarSerie() {
	        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA);
	             PreparedStatement preparedStatement = connection.prepareStatement(
	                     "INSERT INTO series (nome, temporadas, anolancamento, streamingplat) VALUES (?, ?, ?, ?)")) {

	           
	            DBPreview addPreview = new DBPreview();
	            String nome = JOptionPane.showInputDialog("Digite o nome da série: ");
	            addPreview.setNome(nome);

	            int temporadas = Integer.parseInt(JOptionPane.showInputDialog("Digite o número de temporadas: "));
	            addPreview.setTemporadas(temporadas);

	            int anoLancamento = Integer.parseInt(JOptionPane.showInputDialog("Digite o ano de lançamento: "));
	            addPreview.setAnoLancamento(anoLancamento);
	             

	            String plataforma =  JOptionPane.showInputDialog("Digite a plataforma de streaming: ");
	            addPreview.setPlataforma(plataforma);

	            preparedStatement.setString(1, nome);
	            preparedStatement.setInt(2, temporadas);
	            preparedStatement.setInt(3, anoLancamento);
	            preparedStatement.setString(4, plataforma);
	            boolean confirmation = addPreview.previewSeries();
	            if (confirmation) {
	            int linhasAfetadas = preparedStatement.executeUpdate();
	            	if (linhasAfetadas > 0) {
	            		JOptionPane.showMessageDialog(null, "Série adicionada com sucesso!");
	            	} else {
	            		System.out.println("Falha ao adicionar série.");
	            	}
	            }
	            else {
	            	preparedStatement.cancel();
	            	JOptionPane.showMessageDialog(null, "Adição cancelada!");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 protected static void excluirSerie() {
	        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA);
	             PreparedStatement preparedStatement = connection.prepareStatement(
	                     "DELETE FROM series WHERE idseries = ?")) {


	            long id = Long.parseLong(JOptionPane.showInputDialog("Digite o ID da série que deseja excluir: "));
	            scanner.nextLong();

	            preparedStatement.setLong(1, id);

	            int linhasAfetadas = preparedStatement.executeUpdate();
	            if (linhasAfetadas > 0) {
	            	JOptionPane.showMessageDialog(null, "Série excluída com sucesso!");
	            } else {
	            	JOptionPane.showMessageDialog(null, "Falha ao excluir série. Verifique o ID informado.");
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
}

