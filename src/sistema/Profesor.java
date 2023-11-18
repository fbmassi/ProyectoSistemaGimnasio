package sistema;

public class Profesor {

    private String nombre;
    private long sueldo; 
    private Disciplina disciplina;
    private Clase ultima_clase;

    public Profesor(String nombre, Disciplina disciplina) {
    	this.nombre = nombre;
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

    public boolean confirmarHorario() {
    	//CODIFICAR TEMA HORARIO
        return false;
    }

}