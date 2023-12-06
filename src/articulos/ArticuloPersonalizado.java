package articulos;

public class ArticuloPersonalizado extends Articulo {
	
    private String descripcion;
    
    public ArticuloPersonalizado(String tipo, String descripcion, String marca, String amortizacion, String duracionSTR) {
		super(tipo);
		this.setDescripcion(descripcion);
		this.tipo_amortizacion = amortizacion;
                this.marca = marca.toUpperCase();
		if (amortizacion.toUpperCase().equals("POR USO")) {
			int duracion = Integer.parseInt(duracionSTR);
			this.duracion = duracion;
		} else {
			duracion = 0;
		}
	}
    
    public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion.toUpperCase();
	}
	
	@Override
	public void calcularDesgaste(int estado_desgaste) {
		//DESGASTE PERSONALZADO
	}
}