package sistema;
import java.util.*;
import controladores.Socio;

public class Clase {

    private Profesor profesor;
    private Emplazamiento emplazamiento;
    private Disciplina disciplina;
    private String dia;
    private String horario;
    private ArrayList<Socio> alumnos;
    private int cant_inscriptos;
    private String estado;

    public Clase(Profesor profesor, Emplazamiento emplazamiento, Disciplina disciplina, String dia, String horario) {
    	
    	if (profesor.getDisciplina() != disciplina || !profesor.confirmarHorario()) {
    		System.out.println("EL PROFESOR NO PUEDE SER ASIGNADO A ESTA CLASE: " 
    				+ profesor.getDisciplina() 
    				+ "DEBE ASIGNAR OTRO PROFFESOR MANUALMENTE.");
    	} else {
    		this.profesor = profesor;
    		profesor.setUltimaClase(this);
    	}
    	
    	this.emplazamiento = emplazamiento;
    	this.disciplina = disciplina;
    	this.dia = dia;
    	this.horario = horario;
    	this.alumnos = new ArrayList<>();
    	this.cant_inscriptos = 0;
    	this.estado = "AGENDADA";
    }

    public void setProfesor(Profesor profesor) {
    	if (profesor.getDisciplina() != disciplina || !profesor.confirmarHorario()) {
    		System.out.println("EL PROFESOR NO PUEDE SER ASIGNADO A ESTA CLASE: " 
    				+ profesor.getDisciplina() 
    				+ "DEBE ASIGNAR OTRO PROFFESOR MANUALMENTE.");
    	} else {
    		this.profesor = profesor;
    		profesor.setUltimaClase(this);
    	}
    }

    public Profesor getProfesor() {
        return this.profesor;
    }

    public void setEmplazamiento(Emplazamiento emplazamiento) {
    	this.emplazamiento = emplazamiento;
    }

    public Emplazamiento getEmplazamiento() {
        return this.emplazamiento;
    }

    public void setDisciplina(Disciplina disciplina) {
    	this.disciplina = disciplina;
    }

    public Disciplina getDisciplina() {
        return this.disciplina;
    }

    public void agregarAlumno(Socio alumno) {
    	if (this.confirmarDisponibilidad()) {
        	alumnos.add(alumno);
        	this.cant_inscriptos += 1;
    	} else {
    		System.out.println("LA CLASE ESTA LLENA, NO SE ACEPTAN MÁS INSCRIPCIONES.");
    	}
    }

    public void sacarAlumno(Socio alumno) {
    	alumnos.removeIf(socio -> socio == alumno);
    	this.cant_inscriptos -= 1;
    }

    public int getCantInscriptos() {
        return this.cant_inscriptos;
    }

    public ArrayList<Socio> getListaInscriptos() {
        return this.alumnos;
    }

    public void setEstado(String estado) {
    	this.estado = estado;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setHorario(String horario) {
    	this.horario = horario;
    }
    
    public String getHorario(){
    	return this.horario;
    }
    
    public void setDia(String dia) {
    	this.dia = dia;
    }
    
    public String getDia(){
    	return this.dia;
    }
    
    public void grabarClase(int capacidad_de_grabación) {
    	// CODIFICAR TEMA GRABACION
    }

    public boolean confirmarDisponibilidad() {
        return this.cant_inscriptos < this.emplazamiento.getCapacidad();
    }

}