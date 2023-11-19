package sistema;

public class Emplazamiento {

    private int capacidad;
    private int superficie;
    private String tipo;
    
    public Emplazamiento(String tipo, String capacidad, String superficie) {
    	this.setTipo(tipo.toUpperCase());
    	int capacidad_entero = Integer.parseInt(capacidad);
    	this.capacidad = capacidad_entero;
    	int superficie_entero = Integer.parseInt(superficie);
    	this.superficie = superficie_entero;
    }

    public void setCapacidad(int capacidad) {
    	this.capacidad = capacidad;
    }

    public int getCapacidad() {
        return this.capacidad;
    }

    public void setSuperficie(int superficie) {
    	this.superficie = superficie;
    }

    public int getSuperficie() {
        return this.superficie;
    }

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
    
}