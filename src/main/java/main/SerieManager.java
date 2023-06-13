package main;

import java.sql.Connection;
import main.DBManager;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

	public class SerieManager {
		
		
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		//Verifica a conexão.
		DBManager.connectTest();
        String opcao;
        //Menu de opções;
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
                    DBManager.exibirSeries();
                    break;
                case "2":
                    DBManager.adicionarSerie();
                    break;
                case "3":
                    DBManager.editarSerie();
                    break;
                case "4":
                    DBManager.excluirSerie();
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

    

}


