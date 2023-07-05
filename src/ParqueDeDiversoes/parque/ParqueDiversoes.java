package ParqueDeDiversoes.parque;

import java.util.HashMap;
import java.util.ArrayList;


public class ParqueDiversoes {
	private HashMap<Brinquedos, Float> brinquedos;
	private ArrayList<Estabelecimento> estabelecimentos;
	private ArrayList<Cliente> clientes;

	public ParqueDiversoes(){
		brinquedos = new HashMap<>();
		estabelecimentos = new ArrayList<>();
		clientes = new ArrayList<>();

	}

	public float getValorBrinquedo(Brinquedos brinquedo){
		return brinquedos.get(brinquedo);
	}
	
	public void addBrinquedo(Brinquedos brinquedo, float valor) {
		brinquedos.put(brinquedo, valor);
	}
	
	public void addEstabelecimento(Estabelecimento estabelecimento) {
		estabelecimentos.add(estabelecimento);
	}
	public ArrayList<Estabelecimento> getEstabelecimentos() {
		return estabelecimentos;
	}
	public void addCliente(Cliente cliente) {
		clientes.add(cliente);
	}
	public ArrayList<Cliente> getVisitantes() {
		return clientes;
	}
	public HashMap<Brinquedos, Float> getBrinquedos() {
		return brinquedos;
	}

}
