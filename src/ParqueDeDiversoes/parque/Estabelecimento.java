package ParqueDeDiversoes.parque;

import java.util.HashMap;

public abstract class Estabelecimento extends Atracoes implements InterfaceAlimentacao {
	private HashMap<String, Float> cardapio;
	public Estabelecimento(int capacidade, HashMap<String, Float> cardapio, String nome, String restricao){
		super(capacidade, nome, restricao);
		this.cardapio = cardapio;
	}

	public HashMap<String, Float> getCardapio() {
		return cardapio;
	}
	@Override
	public void mostrarMenu(){
		System.out.println("a");
	}
}
