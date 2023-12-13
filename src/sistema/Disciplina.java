
package sistema;

public class Disciplina {
    
    private String tipo;
    private boolean virtualidad;
    private boolean capaciadad_de_grabación;

    public Disciplina(String tipo, String virtualidad) {
    	this.tipo = tipo.toUpperCase();
    	if (!tipo.equals("YOGA") && !tipo.equals("GIMNASIA POSTURAL")) {
            this.virtualidad = false;
            this.capaciadad_de_grabación = false;
    	} else if (tipo.equals("YOGA")) {
            if (virtualidad.equals("V")) {
                this.capaciadad_de_grabación = true;
                this.virtualidad = true;
                this.tipo += " VIRTUAL";
            } else {
                this.capaciadad_de_grabación = false;
                this.virtualidad = false;
                this.tipo += " PRESENCIAL";
            }
    	} else {
            if (virtualidad.equals("V")) {
                this.capaciadad_de_grabación = true;
                this.virtualidad = true;
                this.tipo += " VIRTUAL";
            } else {
                this.capaciadad_de_grabación = false;
                this.virtualidad = false;
                this.tipo += " PRESENCIAL";
            }
    	}
    }

    public void setNombre(String nombre) {
    	this.tipo = nombre;
    	if (!tipo.equals("YOGA") && !tipo.equals("GIMNASIA POSTURAL")) {
            this.virtualidad = false;
            this.capaciadad_de_grabación = false;
        } 
    }

    public String getTipo() {
        return this.tipo;
    }
    
    public void setVirtualidad(boolean virtualidad) {
    	if (tipo.equals("YOGA")) {
            this.virtualidad = virtualidad;
            if (virtualidad) {
                    this.capaciadad_de_grabación = true;
                    this.tipo += " VIRTUAL";
            }
            else {
                this.capaciadad_de_grabación = false;
                this.tipo += " PRESENCIAL";
        }
        } else if (this.tipo.equals("GIMNASIA POSTURAL")) {
            this.virtualidad = virtualidad;
            if (virtualidad) {
                    this.capaciadad_de_grabación = true;
                    this.tipo += " VIRTUAL";
            }
            else {
            this.capaciadad_de_grabación = false;
            this.tipo += " PRESENCIAL";
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