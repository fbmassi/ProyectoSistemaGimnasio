package sistema;

public class Profesor {

    private String nombre;
    private int dni;
    private long sueldo; 
    private int horario_clases;
    private Disciplina disciplina;
    private Clase ultima_clase;

    public Profesor(String nombre, int dni, Disciplina disciplina) {
    	this.nombre = nombre;
    	this.dni = dni;
    	this.disciplina = disciplina;
    }

    public void setHorario(int horario_clases) {
    	this.horario_clases = horario_clases;
    }

    public int getHorario() {
        return this.horario_clases;
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

    public void setDNI(int dni) {
    	this.dni = dni;
    }

    public int getDNI() {
        return this.dni;
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