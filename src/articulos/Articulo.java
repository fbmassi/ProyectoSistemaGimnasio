package articulos;

public abstract class Articulo {

    protected int estado_desgaste;
    private String tipo;

    public Articulo(String tipo) {
    	this.setTipo(tipo);
    	this.estado_desgaste = 100;
    }

    public abstract void calcularDesgaste(int estado_desgaste);

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo.toUpperCase();
	}
	
	public int getEstadoDesgaste() {
		return this.estado_desgaste;
	}
}