package articulos;
import java.time.*;

public class Pesa extends Articulo {

	private int peso;
    private String uso;
    
    public Pesa(String tipo, int peso, String uso, String marca, String amortizacion, String duracionSTR) {
		super(tipo);
		this.peso = peso;
		this.uso = uso.toUpperCase();
		this.marca = marca.toUpperCase();
		this.tipo_amortizacion = amortizacion.toUpperCase();
		int duracion = Integer.parseInt(duracionSTR);
		this.duracion = duracion;
	}
    
	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public String getUso() {
		return this.uso;
	}

	public void setUso(String uso) {
		this.uso = uso.toUpperCase();
	}

	@Override
	public void calcularDesgaste(int estado_desgaste) {
		//CODIFICAR TEMA DESGASTE
	}
	
}