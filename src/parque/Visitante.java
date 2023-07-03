package parque;

import java.util.HashMap;

public class Visitante extends Pessoa{
	private float credito;
	private float altura;
	private long idade;
	private HashMap<Atracoes, Float> historico;

	public Visitante (String nome, long cpf, int idade, float altura, String senha, float credito) {
		super(nome, idade, cpf, senha);
		this.altura = altura;
		this.idade = idade;
		this.credito = credito;
		this.historico = new HashMap<>();
	  }
	
	public float getCredito() {
		return this.credito;
	}
	
	public void setCredito(float credito) {
		this.credito = credito;
	}
	
	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}
	
	public void depositarCredito(float deposito){
		this.credito += deposito;
	}
	
	public boolean descontarCredito(float valor) {
		if (credito - valor >= 0) {
			this.credito -= valor;
			return true;
		}
		return false;

	}

	public HashMap<Atracoes, Float> getHistorico(){
		return historico;
	}

	public void addNoHistorico(Atracoes atracao, float valor){
		if(historico.containsKey(atracao)){
			float valorExistente = historico.get(atracao);
			historico.put(atracao, valorExistente + valor);
		}else{
			historico.put(atracao, valor);
		}
	}
}
