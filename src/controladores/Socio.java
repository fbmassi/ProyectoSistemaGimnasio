package controladores;
import interfaces.IngresoErroneo;
import java.util.*;

import sistema.*;

public class Socio extends Usuario {
	
	public SoporteTécnico creador_ST;
	private Clase ultima_clase;
	private String nivel_suscripción;
	private boolean alta;

    public Socio(SoporteTécnico creador_ST, String username, String contraseña, String n_suscripcion) {
        super(username, contraseña);
        this.creador_ST = creador_ST;
        this.alta = true;
        this.nivel_suscripción = "";
        this.setNivelSuscripción(n_suscripcion);
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
    		IngresoErroneo ingresoErroneo = new IngresoErroneo();
    		ingresoErroneo.setVisible(true);
            ingresoErroneo.setLocationRelativeTo(null);
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
            return this.alta;
    }

    public void setAlta(boolean alta) {
            this.alta = alta;
    }

    public void verGrabaciones() {
            //CODIFICAR VISUALIZACION DE CLASES GRABADAS
    }

    @Override
    public String visualizarClases(){
        String devolver = "";
        for (Clase clase: this.creador_ST.getClases()) {
            if (clase.getSede().getNivelSuscripcion().equals(this.getNivelSuscripción()) && !clase.getEstado().equals("FINALIZADA") && this.isAlta())
                devolver += "<p>CLASE: " + clase.getSede().getUbicacion() + " - " + clase.getDisciplina().getTipo() 
                    + " - "  + clase.getDia() + " " + clase.getHorario() + "hs. - ESTADO: " + clase.getEstado() +".</p>";
            }
        devolver = strHTML(devolver);
        return devolver;
    }
    
    private String strHTML(String texto) {
        return "<html>" + texto + "</html>";
    }
	
}