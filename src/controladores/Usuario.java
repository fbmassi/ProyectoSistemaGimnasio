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
    
    public void iniciarSesion(String username, String contraseña) {
    	if (this.username.equals(username) && this.contraseña.equals(contraseña)) {
    		this.inicio_sesion = true;
    	} else {
    		this.inicio_sesion = false;
    	}
    }

    public void cerrarSesion() {
    	this.inicio_sesion = false;
    }

    public abstract void visualizarClases();
    
    
    public void gestionarPerfil() {
        try (Scanner scanner = new Scanner(System.in)) {        	
            if (this.inicio_sesion) {
                System.out.println("Seleccione qué desea editar"
                		+ "\\n\\tE para nombre de usuario"
                		+ "\\n\\tC para contraseña"
                		+ "\\nIngrese cualquier otro caracter en caso de no querer modificar nada.");
                String editar = scanner.nextLine();
                if (editar.equals("E")) {
                    System.out.print("Ingrese su nuevo nombre de usuario: ");
                    String username = scanner.nextLine();
                    this.username = username;
                } else if (editar.equals("C")) {
                    System.out.print("Ingrese su nueva contraseña: ");
                    String contraseña = scanner.nextLine();
                    this.contraseña = contraseña;
                } else {
                    System.out.println("El perfil no fue modificado.");
                }
            }
        }
    }
    
}
