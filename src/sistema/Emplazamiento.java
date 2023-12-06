package sistema;

public class Emplazamiento {

    private int capacidad;
    private int superficie;
    private String tipo;
    
    public Emplazamiento(String tipo, String superficie, String capacidad) {
    	this.setTipo(tipo.toUpperCase());
        int superficie_entero = Integer.parseInt(superficie);
    	this.superficie = superficie_entero;
    	int capacidad_entero = Integer.parseInt(capacidad);
    	this.setCapacidad(capacidad_entero);
    	
    }

    public void setCapacidad(int capacidad) {
        int capacidad_maxima = this.superficie/2;
        if (capacidad > capacidad_maxima) {
            this.capacidad = capacidad_maxima;
        } else{
            this.capacidad = capacidad;
        }
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