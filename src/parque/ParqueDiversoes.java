package parque;

import java.util.HashMap;

public class ParqueDiversoes {
	private HashMap<String, Float> brinquedos;
	private HashMap<String, Float> estabelecimentos;
	
	public ParqueDiversoes(){}
	
	public float getValorBrinquedo(String brinquedo){
		return brinquedos.get(brinquedo);
	}
	
	public float getValorEstabelecimento(String estabelecimento){
		return estabelecimentos.get(estabelecimento);
	}

	
	public void addBrinquedo(String brinquedo, float valor) {
		brinquedos.put(brinquedo, valor);
	}
	
	public void addEstabelecimento(String estabelecimento, float valor) {
		brinquedos.put(estabelecimento, valor);
	}
}
