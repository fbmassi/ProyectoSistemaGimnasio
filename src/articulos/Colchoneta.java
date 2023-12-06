package articulos;
import java.time.*;

public class Colchoneta extends Articulo {

	private int largo;
    private int ancho;
    
    public Colchoneta(String tipo, int largo, int ancho, String marca, String amortizacion, String duracionSTR) {
		super(tipo);
		this.largo = largo;
		this.ancho = ancho;
		this.marca = marca.toUpperCase();
		this.tipo_amortizacion = amortizacion.toUpperCase();
		int duracion = Integer.parseInt(duracionSTR);
		this.duracion = duracion;
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