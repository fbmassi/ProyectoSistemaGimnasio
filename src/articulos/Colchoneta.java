package articulos;

public class Colchoneta extends Articulo {

	private String largo;
    private String ancho;
    
    public Colchoneta(String tipo, int estado_desgaste) {
		super(tipo, estado_desgaste);
	}
    

	public String getLargo() {
		return this.largo;
	}

	public void setLargo(String largo) {
		this.largo = largo;
	}

	public String getAncho() {
		return this.ancho;
	}

	public void setAncho(String ancho) {
		this.ancho = ancho;
	}
	
	@Override
	public void calcularDesgaste(int estado_desgaste) {
		//CODIFICAR DESGASTE
	}

}