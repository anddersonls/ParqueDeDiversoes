package parque;

import java.util.HashMap;
import java.util.Map;

public abstract class Alimentacao extends Atracoes implements InterfaceAlimentacao{
	private HashMap<String, Float> cardapio;

	
	public Alimentacao(int capacidade, HashMap<String, Float> cardapio, String nome){
		super(capacidade, nome);
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
