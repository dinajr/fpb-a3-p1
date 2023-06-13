package main;

import java.util.Scanner;

public class DBPreview {

    private String nome;
    private int temporadas;
    private int anoLancamento;
    private String plataforma;
    public boolean accept;
    
    public boolean previewSeries() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Demonstração dos dados alterados: ");
    	System.out.println("------------------------------");
        System.out.println("Nome: " + nome);
        System.out.println("Temporadas: " + temporadas);
        System.out.println("Ano de Lançamento: " + anoLancamento);
        System.out.println("Plataforma: " + plataforma);
        System.out.println("------------------------------");
        System.out.println("Deseja salvar alterações? (s/N)");
        String answer = scanner.nextLine();
        switch (answer) {
        case "S":
        	accept = true;
        	break;
        case "s":
        	accept = true;
        	break;
        default:
        	accept = false;
        }
        return accept;
    }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getTemporadas() {
		return temporadas;
	}

	public void setTemporadas(int temporadas) {
		this.temporadas = temporadas;
	}

	public int getAnoLancamento() {
		return anoLancamento;
	}

	public void setAnoLancamento(int anoLancamento) {
		this.anoLancamento = anoLancamento;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

}
