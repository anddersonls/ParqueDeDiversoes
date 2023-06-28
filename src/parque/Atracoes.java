package parque;

public abstract class Atracoes {
	private int capacidade;
	private String nome;
	  
	public Atracoes(int capacidade, String nome) {
		this.capacidade=capacidade;
		this.nome = nome;
	}
	  
	public int getCapacidade() {
		return capacidade;
	}
	
	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}