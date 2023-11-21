package sistema;
import java.util.*;

import controladores.Administrador;
import controladores.Socio;
import controladores.SoporteTécnico;

public class Clase {
	
	private SoporteTécnico creador_ST;
	private Administrador admin;
    private Profesor profesor;
    private Sede sede;
    private Emplazamiento emplazamiento;
    private Disciplina disciplina;
    private String dia;
    private int horario;
    private int duracion;
    private ArrayList<Socio> alumnos;
    private int cant_inscriptos;
    private String estado;
    
    public Clase(SoporteTécnico creador_ST, String username_administrador, String nombre_profesor, String ubicacion_sede, String nombre_emplazamiento, String nombre_disciplina, String dia, String horario, String duracion) {
        
    	int objNecesarios = 0;
    	this.creador_ST = creador_ST;
    	
    	for (Administrador administrador : creador_ST.getAdministradores()) {
            if (administrador.getUsername().equals(username_administrador.toUpperCase())) {
                this.admin = administrador;
                objNecesarios+=1;
                break;
            }
        }
        
        
        for (Disciplina disc : creador_ST.getDisciplinas()) {
            if (disc.getTipo().equals(nombre_disciplina.toUpperCase())) {
                this.disciplina = disc;
                objNecesarios+=1;
                break;
            }
        }

        for (Profesor prof : creador_ST.getProfesores()) {
            if (prof.getNombre().equals(nombre_profesor.toUpperCase()) && prof.confirmarHorario(dia, horario) && prof.getDisciplina() == disciplina) {
                this.profesor = prof;
                profesor.setUltimaClase(this);
                objNecesarios+=1;
                break;
            }
        }
        
        if (admin != null) {
	        for (Sede sed : admin.getSedes()) {
	            if (sed.getUbicacion().equals(ubicacion_sede.toUpperCase())) {
	                this.sede = sed;
	                objNecesarios+=1;
	                break;
	            }
	        }
        }
        
        for (Emplazamiento emp : creador_ST.getEmplazamientos()) {
            if (emp.getTipo().equals(nombre_emplazamiento.toUpperCase())) {
                this.emplazamiento = emp;
                objNecesarios+=1;
                break;
            }
        }

        if (objNecesarios >= 5) {
            this.dia = dia.toUpperCase();
            int horario_entero = Integer.parseInt(horario);
            this.horario = horario_entero;
            int duracion_entero = Integer.parseInt(duracion);
            this.duracion = duracion_entero;
            this.alumnos = new ArrayList<>();
            this.cant_inscriptos = 0;
            this.estado = "AGENDADA";
        } else {
        	this.estado = "FALTA OBJETO";
        }
    }
    
    public Clase(SoporteTécnico creador_ST, String username_administrador, String nombre_profesor, String nombre_emplazamiento, String nombre_disciplina, String dia, String horario, String duracion) {
    	int objNecesarios = 0;
    	this.creador_ST = creador_ST;
    	
    	for (Administrador administrador : creador_ST.getAdministradores()) {
            if (administrador.getUsername().equals(username_administrador.toUpperCase())) {
                this.admin = administrador;
                objNecesarios+=1;
                break;
            }
        }
        
        
        for (Disciplina disc : creador_ST.getDisciplinas()) {
            if (disc.getTipo().equals(nombre_disciplina.toUpperCase())) {
                this.disciplina = disc;
                objNecesarios+=1;
                break;
            }
        }

        for (Profesor prof : creador_ST.getProfesores()) {
            if (prof.getNombre().equals(nombre_profesor.toUpperCase()) && prof.confirmarHorario(dia, horario) && prof.getDisciplina() == disciplina) {
                this.profesor = prof;
                profesor.setUltimaClase(this);
                objNecesarios+=1;
                break;
            }
        
        for (Emplazamiento emp : creador_ST.getEmplazamientos()) {
            if (emp.getTipo().equals(nombre_emplazamiento.toUpperCase())) {
                this.emplazamiento = emp;
                objNecesarios+=1;
                break;
            }
        }

        if (objNecesarios >= 4) {
            this.dia = dia.toUpperCase();
            int horario_entero = Integer.parseInt(horario);
            this.horario = horario_entero;
            int duracion_entero = Integer.parseInt(duracion);
            this.duracion = duracion_entero;
            this.alumnos = new ArrayList<>();
            this.cant_inscriptos = 0;
            this.estado = "AGENDADA";
        } else {
        	this.estado = "FALTA OBJETO";
        }
    }
    }


    	
    public void setAdmin(Administrador administrador) {
    	this.admin = administrador;
    }
    
    public void setProfesor(Profesor profesor) {
    	if (profesor.getDisciplina() != disciplina || !profesor.confirmarHorario(this.dia, this.horario)) {
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
    	int horario_entero = Integer.parseInt(horario);
    	this.horario = horario_entero;
    }
    
    public int getHorario(){
    	return this.horario;
    }
    
    public void setDia(String dia) {
    	this.dia = dia;
    }
    
    public String getDia(){
    	return this.dia;
    }
    
    public int getDuracion() {
    	return this.duracion;
    }
    public boolean confirmarDisponibilidad() {
        return this.cant_inscriptos < this.emplazamiento.getCapacidad();
    }
    
    public long calcularCostos() {
    	long costos = this.profesor.getSueldo()/90;
    	if (emplazamiento.getTipo() == "AIRE LIBRE") {
    		costos += 500*(emplazamiento.getSuperficie()/this.duracion);
    	}
    	
		return costos;
    }
    
    public void agregarAlumno(Socio alumno) {
    	if (alumno.getNivelSuscripción().equals(this.sede.getNivelSuscripcion())
    			&& this.confirmarDisponibilidad()) {
    		if (alumno.getUltimaClase() != null && !alumno.getUltimaClase().getDia().equals(this.dia))
				alumno.setUltimaClase(this);
	        	alumnos.add(alumno);
	        	this.cant_inscriptos += 1;
	        	System.out.println("SE HIZO EFECTIVA LA INSCRIPCION");
    	} else {
    		System.out.println("NO POSEE EL NIVEL DE SUSCRIPCION NECESARIO PARA LA SEDE.");
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