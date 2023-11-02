package controladores;

public abstract class Usuario {

    private String nombre;
    private String apellido;
    private int dni;
    private String contraseña;
    private String correo_electronico;
    private boolean inicio_sesion;

    public Usuario(String correo_electronico,String contraseña) {
    	this.correo_electronico = correo_electronico;
    	this.contraseña = contraseña;
    }

    public void setNombre(String nombre) {
    	if (inicio_sesion) {
    		this.nombre = nombre;
    	}
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setApellido(String apellido) {
    	if (inicio_sesion) {
    	this.apellido = apellido;
    	}
    }

    public String getApellido() {
        return this.apellido ;
    }

    public void setDNI(int DNI) {
    	if (inicio_sesion) {
    		this.dni = DNI;
    	}
    }

    public int getDNI() {
        return this.dni;
    }

    public void iniciarSesion(String correo_electronico, String contraseña) {
    	if (this.correo_electronico ==  correo_electronico && this.contraseña == contraseña) {
    		this.inicio_sesion = true;
    	} else {
    		this.inicio_sesion = false;
    	}
    }

    public void cerrarSesion() {
    	this.inicio_sesion = false;
    }

    public abstract void visualizarClases();
    
    public void gestionarPerfil(boolean inicio_sesion) {
    	//VER QUE ONDA
    }

}