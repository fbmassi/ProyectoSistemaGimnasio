package controladores;
import java.util.*;

import sistema.*;

public class Socio extends Usuario {
	
	private SoporteTécnico creador_ST;
	private Clase ultima_clase;
	private String nivel_suscripción;
	private boolean alta;

    public Socio(String correo_electronico, String contraseña) {
        super(correo_electronico, contraseña);
    }
	
    public void pedirReseva(Clase clase) {
    	clase.agregarAlumno(this);
    }

	public String getNivelSuscripción() {
		return this.nivel_suscripción;
	}

	public void setNivelSuscripción(String nivel_suscripción) {
			this.nivel_suscripción = nivel_suscripción;
	}
	
	public Clase getUltimaClase() {
		return this.ultima_clase;
	}

	public void setUltimaClase(Clase ultima_clase) {
			this.ultima_clase = ultima_clase;
	}
	
	public boolean isAlta() {
		return alta;
	}

	public void setAlta(boolean alta) {
		this.alta = alta;
	}
	
	public void verGrabaciones() {
		//CODIFICAR VISUALIZACION DE CLASES GRABADAS
	}
	
	@Override
	public void visualizarClases(){
		// CODIFICAR VISUALIZACION DE CLASES
		}
	
}