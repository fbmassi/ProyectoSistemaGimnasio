package controladores;
import java.util.*;

import sistema.*;

public class Socio extends Usuario {
	
	public SoporteTécnico creador_ST;
	private Clase ultima_clase;
	private String nivel_suscripción;
	private boolean alta;

    public Socio(SoporteTécnico creador_ST, String username, String contraseña) {
        super(username, contraseña);
        this.creador_ST = creador_ST;
        this.alta = true;
    }
	
    public void pedirReseva(String ubicacion, String tipo_disciplina, String dia, String horario) {
    	boolean inscipcion_no_exitosa = true;
    	for (Clase clase: this.creador_ST.getClases()) {
    		int horario_entero = Integer.parseInt(horario);
    		if (clase.getSede().getUbicacion().equals(ubicacion.toUpperCase()) 
				&& clase.getDisciplina().getTipo().equals(tipo_disciplina.toUpperCase())
				&& clase.getDia().equals(dia.toUpperCase())
				&& clase.getHorario() == horario_entero) {
	    			clase.agregarAlumno(this);
	    			inscipcion_no_exitosa = false;
    		}
    	}
    	if (inscipcion_no_exitosa) {
    		System.out.println("NO SE ENCONTRO LA CLASE EN EL DIA Y HORARIO SOLICITADO.");
    	}
    }

	public String getNivelSuscripción() {
		return this.nivel_suscripción;
	}

	public void setNivelSuscripción(String nivel_suscripción) {
		this.nivel_suscripción = nivel_suscripción.toUpperCase();
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