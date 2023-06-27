package parque;

import java.util.HashMap;
import java.util.ArrayList;

public class ParqueDiversoes {
	private HashMap<String, Float> brinquedos;
	private ArrayList<String> estabelecimentos;
	
	public ParqueDiversoes(){}
	
	public float getValorBrinquedo(String brinquedo){
		return brinquedos.get(brinquedo);
	}
	
	public void addBrinquedo(String brinquedo, float valor) {
		brinquedos.put(brinquedo, valor);
	}
	
	public void addEstabelecimento(String estabelecimento) {
		estabelecimentos.add(estabelecimento);
	}
}
