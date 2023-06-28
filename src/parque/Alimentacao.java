package parque;

import java.util.HashMap;
import java.util.Map;

public abstract class Alimentacao extends Atracoes implements InterfaceAlimentacao{
	private HashMap<String, Float> cardapio;

	
	public Alimentacao(int capacidade, HashMap<String, Float> cardapio, String nome){
		super(capacidade, nome);
		this.cardapio = cardapio;
	}
	
	@Override
	public void mostrarMenu() {
		for (Map.Entry<String, Float> entry : cardapio.entrySet()) {
            String chave = entry.getKey();
            Float valor = entry.getValue();
            System.out.println("Produto: " + chave + ", Valor: " + valor);
        }
	}
}
