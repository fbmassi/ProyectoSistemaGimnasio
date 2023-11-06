package articulos;

public class Colchoneta extends Articulo {

	private int largo;
    private int ancho;
    
    public Colchoneta(String tipo, int largo, int ancho) {
		super(tipo);
		this.largo = largo;
		this.ancho = ancho;
	}
    

	public int getLargo() {
		return this.largo;
	}

	public void setLargo(int largo) {
		this.largo = largo;
	}

	public int getAncho() {
		return this.ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}
	
	@Override
	public void calcularDesgaste(int estado_desgaste) {
		//CODIFICAR DESGASTE
	}

}