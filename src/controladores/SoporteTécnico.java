package controladores;
import java.util.*;
import articulos.*;
import sistema.*;

public class SoporteTécnico {

    private ArrayList<Administrador> administradores;
    private ArrayList<Socio> clientes;
    private ArrayList<Profesor> profesores;
    private ArrayList<Sede> sedes;
    private ArrayList<Articulo> articulos;
    private ArrayList<Clase> clases;
    private ArrayList<Disciplina> disciplinas;
    private ArrayList<Emplazamiento> emplazamientos;
    private Grabaciones grabaciones;
    private String contraseña = "uade_TPO_POO";
    
    public SoporteTécnico(String contraseña) {
    	administradores = new ArrayList<Administrador>();
    	clientes = new ArrayList<Socio>();
    	profesores = new ArrayList<Profesor>();
    	sedes = new ArrayList<Sede>();
    	articulos = new ArrayList<Articulo>();
    	clases = new ArrayList<Clase>();
    	disciplinas = new ArrayList<Disciplina>();
    	emplazamientos = new ArrayList<Emplazamiento>();
    	grabaciones = new Grabaciones();
    }
    
    public boolean iniciarSesion(String contraseña) {
        return contraseña.equals(this.contraseña);
    }
    
    //METODOS DE GESTIÓN DE SEDES
    public void crearNuevaSede(String ubicacion, String nivel_suscripcion) {
    	Sede sede = new Sede (ubicacion,nivel_suscripcion);
    	sedes.add(sede);
    }
    public ArrayList<Sede> getSedes() {
    	return this.sedes;
    }
    
    //METODOS PARA CREAR Y DAR DE BAJA A OTROS USUARIOS
    public void crearAdmin(String username, String contraseña) {
        Administrador admin = new Administrador(this, username, contraseña);
        administradores.add(admin);
    }
    
    public void crearCliente(String username, String contraseña) {
        Socio socio = new Socio(this, username, contraseña);
        clientes.add(socio);
    }
    
    public void darDeBajaUsuarios(String username) {
        administradores.removeIf(administrador -> administrador.getUsername().equals(username.toUpperCase()));
        clientes.removeIf(cliente -> cliente.getUsername().equals(username.toUpperCase()));
    }
    
    public ArrayList<Administrador> getAdministradores() {
    	return this.administradores;
    }
    
    public ArrayList<Socio> getClientes(){
    	return this.clientes;
    }
    
    //METODO PARA ASIGNAR LA SEDE A UN ADMINISTRADOR
    public void asignarSede(String ubicacion_sede, String username_admin) {
    	for (Administrador administrador: administradores) {
    		if (administrador.getUsername().equals(username_admin.toUpperCase())) {
    			for (Sede sede: sedes) {
    	    		if (sede.getUbicacion().equals(ubicacion_sede.toUpperCase())) {
    	    			administrador.agregarSede(sede);
    	    			sede.setAdmin(administrador);
    	    		}
    			}
    		}
    	}
    }
    
    //METODO PARA CREAR CLASES
    public void crearNuevaClase(String username_admin, String nombre_profesor, String ubicacion_sede, String nombre_emplazamiento, String nombre_disciplina, String dia, String horario, String duracion) {
    	Clase clase = new Clase(this, username_admin, nombre_profesor, ubicacion_sede, nombre_emplazamiento, nombre_disciplina, dia, horario, duracion);
    	if (clase.getEstado() != "FALTA OBJETO") {	
	    	for (Administrador administrador: administradores) {
	    		for (Sede sede: administrador.getSedes()) {
		    		if (sede.getUbicacion().equals(ubicacion_sede.toUpperCase())) {
		    			administrador.agregarAccesoAClase(clase);
		    			sede.agregarClase(clase);
		    		}
	    		}
		    }
	    	this.clases.add(clase);
    	} else {
    		System.out.println("NO SE PUDO CREAR LA CLASE PORQUE FALTAN OBJETOS O LOS DATOS SE INGRESARON INCORRECTAMENTE");
    	}
    }
        
    //METODO PARA CREAR ARTICULOS NUEVOS
    public void crearArticulo(String tipo, String peso, String uso, String largo, String ancho, String descripcion) { 
        if (tipo.toUpperCase().equals("PESA")) {
            try {
                int peso_entero = Integer.parseInt(peso);
                Pesa pesa = new Pesa(tipo, peso_entero, uso.toUpperCase());
                articulos.add(pesa);
            } catch (NumberFormatException e) {
                System.out.println("Peso ingresado no válido. No se ha podido crear el objeto.");
            }
        } else if (tipo.toUpperCase().equals("COLCHONETA")) {
            try {
                int largo_entero = Integer.parseInt(largo);
                int ancho_entero = Integer.parseInt(ancho);
                Colchoneta colchoneta = new Colchoneta(tipo, largo_entero, ancho_entero);
                articulos.add(colchoneta);
            } catch (NumberFormatException e) {
                System.out.println("Largo o ancho ingresado no válido. No se ha podido crear el objeto.");
            }
        } else if (tipo.toUpperCase().equals("ARTICULO PERSONALIZADO")) {
            ArticuloPersonalizado artp = new ArticuloPersonalizado(tipo, descripcion);
            articulos.add(artp);
        }
    }
    
    //METODOS PARA CREAR OTROS OBJETOS NECESARIOS
    public void crearProfesor(String nombre, String disciplina) {
    	boolean existe_disciplina = false;
    	if (disciplinas.size() > 0) {
    		for (Disciplina disc: disciplinas) {
                if (disc.getTipo().equals(disciplina.toUpperCase())) {
                	existe_disciplina = true;
                    Profesor profesor = new Profesor(this, nombre, disc);
                    profesores.add(profesor);
                }
    		}
    	}
        if (disciplinas.size() == 0 || !existe_disciplina) {
        	System.out.println("NO SE PUEDE CREAR EL OBJETO PROFESOR SI ANTES NO EXISTE LA DISCIPLINA.");
    	}
    }
    
    public void darDeBajaProfesor(String nombre_profesor) {
    	profesores.removeIf(profe -> profe.getNombre().equals(nombre_profesor));
    }
    
    public void crearDisciplina(String nombre, String virtualidad) {
    	Disciplina disc = new Disciplina(nombre, virtualidad);
    	disciplinas.add(disc);
    }
    
    public void crearEmplazamiento(String tipo, String capacidad, String superficie) {
		Emplazamiento emplazamiento = new Emplazamiento(tipo, capacidad, superficie);
		this.emplazamientos.add(emplazamiento);
	}
    
    //OTROS METODOS
    public ArrayList<Profesor> getProfesores(){
    	return this.profesores;
    }

	public ArrayList<Emplazamiento> getEmplazamientos() {
		return emplazamientos;
	}
	
	public ArrayList<Clase> getClases(){
		return this.clases;
	}
	
	public ArrayList<Disciplina> getDisciplinas() {
		return this.disciplinas;
	}
	public ArrayList<Articulo> getArticulos() {
		return this.articulos;
	}
	
	public void agregarEmplazamientos(Emplazamiento emplazamiento) {
		this.emplazamientos.add(emplazamiento);
	}

	public void agregarSede(Sede sede) {
		this.sedes.add(sede);
	}
	
	public void guardarGrabacion(Clase clase) {
    	grabaciones.agregarClase(clase);
    }
    
    public Grabaciones getGrabaciones() {
    	return this.grabaciones;
    }
}