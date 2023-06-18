package main;

import java.sql.Connection;
import DAO.ConnectionFactory;
import javax.swing.*;
import main.DBManager;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

	public class SerieManager {
		
		
	public static void main(String[] args) {
		//Scanner scanner = new Scanner(System.in);
		//Verifica a conexão.
		ConnectionFactory.connectTest();
        String opcao;
        //Menu de opções;
        do {
        	opcao = JOptionPane.showInputDialog(null, "<html>===== MENU =====<br>"
        			+ "1. Exibir séries<br>"
        			+ "2. Adicionar série<br>"
        			+ "3. Editar série<br>"
        			+"4. Excluir série<br>"
        			+"0. Sair");

        	
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
                   JOptionPane.showMessageDialog(null, "Encerrando o programa...");
                    break;
                default:
                	JOptionPane.showMessageDialog(null, "Opção inválida!");
                    break;
            }
        } while (!opcao.equals("0"));

        
    }

    

}


