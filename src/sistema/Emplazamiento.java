package sistema;

public class Emplazamiento {

    private int capacidad;
    private int superficie;
    private String nombre;
    
    public Emplazamiento(int capacidad, int superficie, String nombre) {
    	this.setCapacidad(capacidad);
    	this.setNombre(nombre);
    	this.setSuperficie(superficie);
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

    public void setNombre(String nombre) {
    	this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }
    
}