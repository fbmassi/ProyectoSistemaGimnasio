package controladores;
import java.util.*;


public abstract class Usuario {

    protected String contraseña;
    protected String username;
    private boolean inicio_sesion;

    public Usuario(String username,String contraseña) {
    	this.username = username.toUpperCase();
    	this.contraseña = contraseña.toUpperCase();
    }

    public String getUsername() {
        return this.username;
    }
    
    public boolean iniciarSesion(String contraseña) {
        contraseña = contraseña.toUpperCase();
        if (contraseña.equals(this.contraseña)) {
            this.inicio_sesion = true;
        }
        return contraseña.equals(this.contraseña);
    }

    public void cerrarSesion() {
    	this.inicio_sesion = false;
    }

    public abstract String visualizarClases();
    
}
