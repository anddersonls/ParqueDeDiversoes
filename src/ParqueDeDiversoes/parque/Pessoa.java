package ParqueDeDiversoes.parque;

public abstract class Pessoa {
	  private String nome;
	  private int idade;
	  private long cpf;
	  private String senha;

	  public Pessoa (String nome, int idade, long cpf , String senha) {
		this.nome=nome;
		this.idade=idade;
		this.cpf=cpf;
		this.senha=senha;
	  }


	  public String getNome() {
	    return nome;
	  }

	  public void setNome(String nome) {
	    this.nome = nome;
	  }

	  public int getIdade() {
	    return idade;
	  }

	  public void setIdade(int idade) {
	    this.idade = idade;
	  }

	  public long getCpf() {
	    return cpf;
	  }

	  public void setCpf(int cpf) {
	    this.cpf = cpf;
	  }
	  public String getSenha() {
		return senha;
	}

	  public void setSenha(String senha) {
		this.senha = senha;
	}
	}