package parque;

public class Gerente extends Pessoa{
	private int senha;
	
	public Gerente (String nome, int idade, int cpf , int senha) {
		super(nome, idade, cpf);
		this.senha = senha;
	  }
	
	public int getSenha() {
		return this.senha;
	}
	
	public void setSenha(int senha) {
		this.senha = senha;
	}
}
