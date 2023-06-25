package parque;

public class Visitante extends Pessoa{
	private float credito;
	private float altura;
	
	public Visitante (String nome, int idade, int cpf, float altura, float credito) {
		super(nome, idade, cpf);
		this.altura = altura;
		this.credito = credito;
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
	
	public void descontarCredito() {
		this.credito -= credito;
	}
}
