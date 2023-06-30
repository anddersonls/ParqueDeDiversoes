package parque;

import java.util.HashMap;
import java.util.ArrayList;

public class ParqueDiversoes {
	private HashMap<Brinquedos, Float> brinquedos;
	private ArrayList<Alimentacao> estabelecimentos;
	private ArrayList<Visitante> visitantes;

	public ParqueDiversoes(){
		brinquedos = new HashMap<>();
		estabelecimentos = new ArrayList<>();
		visitantes = new ArrayList<>();
	}

	public float getValorBrinquedo(Brinquedos brinquedo){
		return brinquedos.get(brinquedo);
	}
	
	public void addBrinquedo(Brinquedos brinquedo, float valor) {
		brinquedos.put(brinquedo, valor);
	}
	
	public void addEstabelecimento(Alimentacao estabelecimento) {
		estabelecimentos.add(estabelecimento);
	}
	public ArrayList<Alimentacao> getEstabelecimentos() {
		return estabelecimentos;
	}
	public void addVisitante(Visitante visitante) {
		visitantes.add(visitante);
	}
	public ArrayList<Visitante> getVisitantes() {
		return visitantes;
	}
}
