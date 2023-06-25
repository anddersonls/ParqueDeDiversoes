package parque;

public abstract class Atracoes {
	private int capacidade;

	  
	public Atracoes(int capacidade) {
		this.capacidade=capacidade;
	}
	  
	public int getCapacidade() {
		return capacidade;
	}
	
	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}	
}