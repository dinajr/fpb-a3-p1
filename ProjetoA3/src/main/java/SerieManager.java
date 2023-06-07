import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

	public class SerieManager {
	    private static final String URL = "jdbc:mysql://localhost:3306/mydb?user_turanksu&password=gt86@sw20";
	    private static final String USUARIO = "turanksu";
	    private static final String SENHA = "gt86@sw20";
		private static Scanner scanner;

		ConexaoDAO dao = new ConexaoDAO();
		Connection conexao = dao.conectaDB();
		

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
        String opcao;

        do {
            System.out.println("===== MENU =====");
            System.out.println("1. Exibir séries");
            System.out.println("2. Adicionar série");
            System.out.println("3. Editar série");
            System.out.println("4. Excluir série");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    exibirSeries();
                    break;
                case "2":
                    adicionarSerie();
                    break;
                case "3":
                    editarSerie();
                    break;
                case "4":
                    excluirSerie();
                    break;
                case "0":
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (!opcao.equals("0"));

        scanner.close();
    }
	private static void exibirSeries() {
        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM series")) {

            System.out.println("===== SÉRIES =====");
            while (resultSet.next()) {
                int id = resultSet.getInt("idseries");
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
	 private static void editarSerie() {
	        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA);
	             PreparedStatement preparedStatement = connection.prepareStatement(
	                     "UPDATE series SET nome = ?, temporadas = ?, anolancamento = ?, streamingplat = ? WHERE idseries = ?")) {

	            Scanner scanner = new Scanner(System.in);

	            System.out.print("Digite o ID da série que deseja editar: ");
	            int id = scanner.nextInt();
	            scanner.nextLine(); 

	            System.out.print("Digite o novo nome da série: ");
	            String nome = scanner.nextLine();

	            System.out.print("Digite o novo número de temporadas: ");
	            int temporadas = scanner.nextInt();

	            System.out.print("Digite o novo ano de lançamento: ");
	            int anoLancamento = scanner.nextInt();
	            scanner.nextLine(); 
	            System.out.print("Digite a nova plataforma de streaming: ");
	            String plataforma = scanner.nextLine();

	            preparedStatement.setString(1, nome);
	            preparedStatement.setInt(2, temporadas);
	            preparedStatement.setInt(3, anoLancamento);
	            preparedStatement.setString(4, plataforma);
	            preparedStatement.setInt(5, id);

	            int linhasAfetadas = preparedStatement.executeUpdate();
	            if (linhasAfetadas > 0) {
	                System.out.println("Série atualizada com sucesso!");
	            } else {
	                System.out.println("Falha ao atualizar série. Verifique o ID informado.");
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 private static void adicionarSerie() {
	        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA);
	             PreparedStatement preparedStatement = connection.prepareStatement(
	                     "INSERT INTO series (nome, temporadas, anolancamento, streamingplat) VALUES (?, ?, ?, ?)")) {

	            scanner = new Scanner(System.in);

	            System.out.print("Digite o nome da série: ");
	            String nome = scanner.nextLine();

	            System.out.print("Digite o número de temporadas: ");
	            int temporadas = scanner.nextInt();

	            System.out.print("Digite o ano de lançamento: ");
	            int anoLancamento = scanner.nextInt();
	            scanner.nextLine(); 

	            System.out.print("Digite a plataforma de streaming: ");
	            String plataforma = scanner.nextLine();

	            preparedStatement.setString(1, nome);
	            preparedStatement.setInt(2, temporadas);
	            preparedStatement.setInt(3, anoLancamento);
	            preparedStatement.setString(4, plataforma);

	            int linhasAfetadas = preparedStatement.executeUpdate();
	            if (linhasAfetadas > 0) {
	                System.out.println("Série adicionada com sucesso!");
	            } else {
	                System.out.println("Falha ao adicionar série.");
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 private static void excluirSerie() {
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


