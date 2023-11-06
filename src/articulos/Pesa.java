package articulos;

public class Pesa extends Articulo {

	private int peso;
    private String uso;
    
    public Pesa(String tipo, int peso, String uso) {
		super(tipo);
		this.peso = peso;
		this.uso = uso;
	}
    
	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public String getUso() {
		return uso;
	}

	public void setUso(String uso) {
		this.uso = uso;
	}

	public int getEstadDesgaste() {
		return this.estado_desgaste;
	}


	public void setEstadoDesgaste(int estado_desgaste) {
		this.estado_desgaste = estado_desgaste;
	}
	
	@Override
	public void calcularDesgaste(int estado_desgaste) {
		//CODIFICAR TEMA DESGASTE
	}
	
}