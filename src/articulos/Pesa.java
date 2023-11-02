package articulos;

public class Pesa extends Articulo {

	private int peso;
    private String uso;
    
    public Pesa(String tipo, int peso) {
		super(tipo, peso);
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
    
	@Override
	public void calcularDesgaste(int estado_desgaste) {
		//CODIFICAR TEMA DESGASTE
		
	}

}