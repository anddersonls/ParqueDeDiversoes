package ParqueDeDiversoes.parque;

import java.util.HashMap;

public class Quiosque extends Estabelecimento {
	public Quiosque (int capacidade, HashMap<String, Float> cardapio, String nome, String restricao){
		super(capacidade, cardapio, nome, restricao);
	}
}
