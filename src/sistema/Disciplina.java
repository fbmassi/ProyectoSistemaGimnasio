package sistema;

public class Disciplina {
    
    private String nombre;
    private boolean virtualidad;
    private boolean capaciadad_de_grabación;

    public Disciplina(String nombre, String virtualidad) {
    	this.nombre = nombre;
    	if (nombre != "YOGA" && nombre != "GIMNASIA POSTURAL") {
            this.virtualidad = false;
            this.capaciadad_de_grabación = false;
    	} else if (nombre == "YOGA") {
            if (virtualidad == "V") {
                this.capaciadad_de_grabación = true;
                this.virtualidad = true;
            } else {
                this.capaciadad_de_grabación = false;
                this.virtualidad = false;
            }
    	} else {
            if (virtualidad == "V") {
                this.capaciadad_de_grabación = true;
                this.virtualidad = true;
            } else {
                this.capaciadad_de_grabación = false;
                this.virtualidad = false;
            }
    	}
    }

    public void setNombre(String nombre) {
    	this.nombre = nombre;
    	if (nombre != "YOGA" && nombre != "GIMNASIA POSTURAL") {
            this.virtualidad = false;
            this.capaciadad_de_grabación = false;
        } 
    }

    public String getNombre() {
        return this.nombre;
    }
    
    public void setVirtualidad(boolean virtualidad) {
    	if (this.nombre == "YOGA") {
            this.virtualidad = virtualidad;
            if (virtualidad) {
                    this.capaciadad_de_grabación = true;
            }
            else {
            this.capaciadad_de_grabación = false;
        }
        } else if (this.nombre == "GIMNASIA POSTURAL") {
            this.virtualidad = virtualidad;
            if (virtualidad) {
                    this.capaciadad_de_grabación = true;
            }
            else {
            this.capaciadad_de_grabación = false;
        }
        } else {
            this.virtualidad = false;
            this.capaciadad_de_grabación = false;
        }
    }
    
    public boolean getVirtualidad() {
        return this.virtualidad;
    }
    
    public boolean getCapacidadDeGrabacion() {
        return this.capaciadad_de_grabación;
    }
}