package ParqueDeDiversoes.parque;

public abstract class Atracoes {
	private int capacidade;
	private String nome;
	private String restricao;
	  
	public Atracoes(int capacidade, String nome, String restricao) {
		this.capacidade=capacidade;
		this.nome = nome;
		this.restricao = restricao;
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

	public String getRestricao() {
		return restricao;
	}
	public void setRestricao(String restricao) {
		this.restricao = restricao;
	}
}