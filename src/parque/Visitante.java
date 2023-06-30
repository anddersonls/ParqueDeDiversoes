package parque;

public class Visitante extends Pessoa{
	private float credito;
	private float altura;
	private long idade;

	public Visitante (String nome, long cpf, int idade, float altura, String senha, float credito) {
		super(nome, idade, cpf, senha);
		this.altura = altura;
		this.idade = idade;
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
