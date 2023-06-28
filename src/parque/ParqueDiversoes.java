package parque;

import java.util.HashMap;
import java.util.ArrayList;

public class ParqueDiversoes {
	private HashMap<Brinquedos, Float> brinquedos;
	private ArrayList<Alimentacao> estabelecimentos;
	
	public ParqueDiversoes(){}

	public float getValorBrinquedo(Brinquedos brinquedo){
		return brinquedos.get(brinquedo);
	}
	
	public void addBrinquedo(Brinquedos brinquedo, float valor) {
		brinquedos.put(brinquedo, valor);
	}
	
	public void addEstabelecimento(Alimentacao estabelecimento) {
		estabelecimentos.add(estabelecimento);
	}
}
