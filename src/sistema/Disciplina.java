package sistema;

public class Disciplina {
    
    private String tipo;
    private boolean virtualidad;
    private boolean capaciadad_de_grabación;

    public Disciplina(String tipo, String virtualidad) {
    	this.tipo = tipo.toUpperCase();
    	if (tipo != "YOGA" && tipo != "GIMNASIA POSTURAL") {
            this.virtualidad = false;
            this.capaciadad_de_grabación = false;
    	} else if (tipo == "YOGA") {
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
    	this.tipo = nombre;
    	if (nombre != "YOGA" && nombre != "GIMNASIA POSTURAL") {
            this.virtualidad = false;
            this.capaciadad_de_grabación = false;
        } 
    }

    public String getTipo() {
        return this.tipo;
    }
    
    public void setVirtualidad(boolean virtualidad) {
    	if (this.tipo == "YOGA") {
            this.virtualidad = virtualidad;
            if (virtualidad) {
                    this.capaciadad_de_grabación = true;
            }
            else {
            this.capaciadad_de_grabación = false;
        }
        } else if (this.tipo == "GIMNASIA POSTURAL") {
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