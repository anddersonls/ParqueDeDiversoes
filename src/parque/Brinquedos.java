package parque;

public class Brinquedos extends Atracoes{
	  private float alturaMin;
	  private int idadeMin;
	  
	  public Brinquedos(int capacidade, float alturaMin, int idadeMin, String nome) {
		  super(capacidade, nome);
		  this.alturaMin = alturaMin;
		  this.idadeMin = idadeMin;
	  }
	  
	  public float getAlturaMin() {
		return alturaMin;
	  }
	  
	  public void setAlturaMin(float alturaMin) {
		this.alturaMin = alturaMin;
	  }
	  
	  public int getIdadeMin() {
		  return idadeMin;
	  }
	  
	  public void setIdadeMin(int idadeMin) {
		  this.idadeMin = idadeMin;
	  }
		  
}
