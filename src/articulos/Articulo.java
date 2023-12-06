package articulos;

public abstract class Articulo {
	
    protected String tipo;
    protected String marca;
	protected String tipo_amortizacion;
	protected int duracion;

    public Articulo(String tipo) {
    	this.setTipo(tipo);
    }

    public abstract void calcularDesgaste(int estado_desgaste);

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo.toUpperCase();
	}
	
	public String getMarca() {
		return marca;
		
	}
	
	public int getDuracion() {
		return this.duracion;
	}
	
	public String getTipoAmortizacion() {
		return this.tipo_amortizacion;
	}
}