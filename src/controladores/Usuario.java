package controladores;
import java.util.*;
public abstract class Usuario {

    private String nombre;
    private String apellido;
    private int dni;
    protected String contraseña;
    protected String correo_electronico;
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
    
    public String getCorreoElectronico() {
		return this.correo_electronico;
    	
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
    
    
    public void gestionarPerfil() {
        try (Scanner scanner = new Scanner(System.in)) {        	
            if (this.inicio_sesion) {
                System.out.println("Seleccione qué desea editar"
                		+ "\n\tN para nombre "
                		+ "\\n\\tA para apellido"
                		+ "\\n\\tD para DNI"
                		+ "\\n\\tE para correo"
                		+ "\\n\\tC para contraseña"
                		+ "\\nIngrese cualquier otro caracter en caso de no querer modificar nada.");
                String editar = scanner.nextLine();
                if (editar.equals("N")) {
                    System.out.print("Ingrese su nuevo nombre: ");
                    String nombre = scanner.nextLine();
                    this.nombre = nombre;
                } else if (editar.equals("A")) {
                    System.out.print("Ingrese su nuevo apellido: ");
                    String apellido = scanner.nextLine();
                    this.apellido = apellido;
                } else if (editar.equals("D")) {
                    System.out.print("Ingrese su nuevo DNI: ");
                    String dniStr = scanner.nextLine();
                    try {
                        int dni = Integer.parseInt(dniStr);
                        this.dni = dni;
                    } catch (NumberFormatException e) {
                        System.out.println("DNI no válido. No se ha actualizado.");
                    }
                } else if (editar.equals("E")) {
                    System.out.print("Ingrese su nuevo correo electrónico: ");
                    String correo_electronico = scanner.nextLine();
                    this.correo_electronico = correo_electronico;
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
