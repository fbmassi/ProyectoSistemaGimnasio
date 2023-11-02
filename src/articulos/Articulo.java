package articulos;

public abstract class Articulo {

    protected int estado_desgaste;
    private String tipo;

    public Articulo(String tipo, int estado_desgaste) {
    	this.setTipo(tipo);
    	this.estado_desgaste = estado_desgaste;
    }

    public abstract void calcularDesgaste(int estado_desgaste);

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}    
}