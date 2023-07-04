package ParqueDeDiversoes.parque;

import java.util.HashMap;

public class Restaurante extends Estabelecimento {
	public Restaurante (int capacidade, HashMap<String, Float> cardapio, String nome,  String restricao){
		super(capacidade, cardapio, nome, restricao);
	}
	
}
