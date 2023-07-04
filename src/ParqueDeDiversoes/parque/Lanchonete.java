package ParqueDeDiversoes.parque;

import java.util.HashMap;

public class Lanchonete extends Estabelecimento {
	public Lanchonete (int capacidade, HashMap<String, Float> cardapio, String nome, String restricao){
		super(capacidade, cardapio, nome, restricao);
	}
}
