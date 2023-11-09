package sistema;
import java.util.*;
import controladores.Socio;

public class Clase {

    private Profesor profesor;
    private Sede sede;
    private Emplazamiento emplazamiento;
    private Disciplina disciplina;
    private String dia;
    private String horario;
    private int duracion;
    private ArrayList<Socio> alumnos;
    private int cant_inscriptos;
    private String estado;

    public Clase(Profesor profesor, Sede sede, Emplazamiento emplazamiento, Disciplina disciplina, String dia, String horario) {
    	
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
    
    public Sede getSede() {
		return this.sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
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
    
    public boolean confirmarDisponibilidad() {
        return this.cant_inscriptos < this.emplazamiento.getCapacidad();
    }
    
    public long calcularCostos() {
    	long costos = this.profesor.getSueldo()/90;
    	if (emplazamiento.getNombre() == "AIRE LIBRE") {
    		costos += 500*(emplazamiento.getSuperficie()/this.duracion);
    	}
    	
		return costos;
    	
    }
    public void agregarAlumno(Socio alumno) {
    	if (alumno.getNivelSuscripción() == this.sede.getNivelSuscripcion() 
    			&& alumno.getUltimaClase().getDia() != this.dia 
    			&& this.confirmarDisponibilidad()) {
    				alumno.setUltimaClase(this);
		        	alumnos.add(alumno);
		        	this.cant_inscriptos += 1;
    	} else {
    		System.out.println("NO SE PUEDE CONCRETAR LA INSCRIPCION.");
    	}
    }

    public void sacarAlumno(Socio alumno) { 
    	alumno.setUltimaClase(null);
	    alumnos.removeIf(socio -> socio == alumno);
	    this.cant_inscriptos -= 1;
    }
    
    public void grabarClase() {
    	//CODIFICAR TEMA 
    }
}