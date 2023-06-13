package main;
import main.DBPreview;
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

            System.out.println("===== SÉRIES =====");
            while (resultSet.next()) {
                long id = resultSet.getInt("idseries");
                String nome = resultSet.getString("nome");
                int temporadas = resultSet.getInt("temporadas");
                int anoLancamento = resultSet.getInt("anolancamento");
                String plataforma = resultSet.getString("streamingplat");

                System.out.println("ID: " + id);
                System.out.println("Nome: " + nome);
                System.out.println("Temporadas: " + temporadas);
                System.out.println("Ano de Lançamento: " + anoLancamento);
                System.out.println("Plataforma: " + plataforma);
                System.out.println("------------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	 protected static void editarSerie() {
	        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA);
	             PreparedStatement preparedStatement = connection.prepareStatement(
	                     "UPDATE series SET nome = ?, temporadas = ?, anolancamento = ?, streamingplat = ? WHERE idseries = ?")) {

	            Scanner scanner = new Scanner(System.in);

	            System.out.print("Digite o ID da série que deseja editar: ");
	            long id = scanner.nextLong();
	            scanner.nextLine(); 
	            DBPreview editPreview = new DBPreview();
	            System.out.print("Digite o novo nome da série: ");
	            String nome = scanner.nextLine(); editPreview.setNome(nome);

	            System.out.print("Digite o novo número de temporadas: ");
	            int temporadas = scanner.nextInt(); editPreview.setTemporadas(temporadas);

	            System.out.print("Digite o novo ano de lançamento: ");
	            int anoLancamento = scanner.nextInt(); editPreview.setAnoLancamento(anoLancamento);
	            scanner.nextLine(); 
	            System.out.print("Digite a nova plataforma de streaming: ");
	            String plataforma = scanner.nextLine(); editPreview.setPlataforma(plataforma);

	            preparedStatement.setString(1, nome);
	            preparedStatement.setInt(2, temporadas);
	            preparedStatement.setInt(3, anoLancamento);
	            preparedStatement.setString(4, plataforma);
	            preparedStatement.setLong(5, id);
	            boolean confirmation = editPreview.previewSeries();
	            if (confirmation) {
	            int linhasAfetadas = preparedStatement.executeUpdate();
	            if (linhasAfetadas > 0) {
	                System.out.println("Série atualizada com sucesso!");
	            } else {
	                System.out.println("Falha ao atualizar série. Verifique o ID informado.");
	            }
	            }
	            else {
	            	preparedStatement.cancel();
	            	System.out.println("Mudança cancelada!");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	    }
	 protected static void adicionarSerie() {
	        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA);
	             PreparedStatement preparedStatement = connection.prepareStatement(
	                     "INSERT INTO series (nome, temporadas, anolancamento, streamingplat) VALUES (?, ?, ?, ?)")) {

	            scanner = new Scanner(System.in);
	            DBPreview addPreview = new DBPreview();
	            System.out.print("Digite o nome da série: ");
	            String nome = scanner.nextLine(); addPreview.setNome(nome);

	            System.out.print("Digite o número de temporadas: ");
	            int temporadas = scanner.nextInt(); addPreview.setTemporadas(temporadas);

	            System.out.print("Digite o ano de lançamento: ");
	            int anoLancamento = scanner.nextInt(); addPreview.setAnoLancamento(anoLancamento);
	            scanner.nextLine(); 

	            System.out.print("Digite a plataforma de streaming: ");
	            String plataforma = scanner.nextLine(); addPreview.setPlataforma(plataforma);

	            preparedStatement.setString(1, nome);
	            preparedStatement.setInt(2, temporadas);
	            preparedStatement.setInt(3, anoLancamento);
	            preparedStatement.setString(4, plataforma);
	            boolean confirmation = addPreview.previewSeries();
	            if (confirmation) {
	            int linhasAfetadas = preparedStatement.executeUpdate();
	            	if (linhasAfetadas > 0) {
	            		System.out.println("Série adicionada com sucesso!");
	            	} else {
	            		System.out.println("Falha ao adicionar série.");
	            	}
	            }
	            else {
	            	preparedStatement.cancel();
	            	System.out.println("Adição cancelada!");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 protected static void excluirSerie() {
	        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA);
	             PreparedStatement preparedStatement = connection.prepareStatement(
	                     "DELETE FROM series WHERE idseries = ?")) {

	            Scanner scanner = new Scanner(System.in);

	            System.out.print("Digite o ID da série que deseja excluir: ");
	            int id = scanner.nextInt();

	            preparedStatement.setInt(1, id);

	            int linhasAfetadas = preparedStatement.executeUpdate();
	            if (linhasAfetadas > 0) {
	                System.out.println("Série excluída com sucesso!");
	            } else {
	                System.out.println("Falha ao excluir série. Verifique o ID informado.");
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
}

