package sistema;
import controladores.*;

public class Profesor {
	
	private SoporteTécnico creador_ST;
    private String nombre;
    private long sueldo; 
    private Disciplina disciplina;
    private Clase ultima_clase;

    public Profesor(SoporteTécnico creador_ST, String nombre, Disciplina disciplina) {
    	this.creador_ST = creador_ST;
    	this.nombre = nombre.toUpperCase();
    	this.disciplina = disciplina;
    }

    public void setUltimaClase(Clase clase) {
    	this.ultima_clase = clase;
    }

    public Clase getUltimaClase() {
        return this.ultima_clase;
    }

    public void setNombre(String nombre) {
    	this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }
    
    public long getSueldo() {
		return this.sueldo;
	}

	public void setSueldo(long sueldo) {
		this.sueldo = sueldo;
	}
    
    public void setDisciplina(Disciplina disciplina) {
    	this.disciplina = disciplina;
    }

    public Disciplina getDisciplina() {
        return this.disciplina;
    }

    public boolean confirmarHorario(String dia, String horario) {
    	boolean disponibilidad = true;
    	if (ultima_clase != null && dia == ultima_clase.getDia()) {
    		int horario_entero = Integer.parseInt(horario);
    		if (horario_entero >= ultima_clase.getHorario()) {
	    		int diferencia_horaria = horario_entero - ultima_clase.getHorario() + ultima_clase.getDuracion();
	    		if (diferencia_horaria <= 3) {
	    			disponibilidad = false;
	    		}
    		}
    	}
        return disponibilidad;
    }
    
    public boolean confirmarHorario(String dia, int horario) {
    	boolean disponibilidad = true;
    	if (ultima_clase != null && dia == ultima_clase.getDia()) {
    		if (horario >= ultima_clase.getHorario()) {
	    		int diferencia_horaria = horario - ultima_clase.getHorario() + ultima_clase.getDuracion();
	    		if (diferencia_horaria <= 3) {
	    			disponibilidad = false;
	    		}
    		}
    	}
        return disponibilidad;
    }

}