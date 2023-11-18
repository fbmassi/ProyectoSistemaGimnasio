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
    }
    
    public boolean iniciarSesion(String contraseña) {
        return contraseña.equals(this.contraseña);
    }
    
    //METODOS PARA CREAR Y DAR DE BAJA A OTROS USUARIOS
    public void crearAdmin(String correo_electronico, String contraseña) {
        Administrador admin = new Administrador(correo_electronico, contraseña);
        administradores.add(admin);
    }
    
    public void crearCliente(String correo_electronico, String contraseña) {
        Socio socio = new Socio(correo_electronico, contraseña);
        clientes.add(socio);
    }
    
    public void darDeBajaUsuarios(String username) {
        administradores.removeIf(administrador -> administrador.getUsername().equals(username));
        clientes.removeIf(cliente -> cliente.getUsername().equals(username));
    }
    
    //METODO PARA CREAR SEDES
    public void crearNuevaSede(String nombre, String ubicacion, String nivel_suscripcion) {
    	Sede sede = new Sede (nombre,ubicacion,nivel_suscripcion);
    	sedes.add(sede);
    }
    
    //METODO PARA ASIGNAR LA SEDE A UN ADMINISTRADOR
    public void asignarSede(String nombre_sede, String username_admin) {
    	for (Administrador administrador: administradores) {
    		if (administrador.getUsername().equals(username_admin)) {
    			for (Sede sede: sedes) {
    	    		if (sede.getNombre().equals(nombre_sede)) {
    	    			administrador.agregarSede(sede);	
    	    		}
    			}
    		}
    	}
    }
    
    //METODO PARA CREAR CLASES
    public void crearNuevaClase(String nombre_profesor, String nombre_sede, String nombre_emplazamiento, String nombre_disciplina, String dia, String horario) {
    	Clase clase = new Clase(this, null, nombre_profesor, nombre_sede, nombre_emplazamiento, nombre_disciplina, dia, horario);
    	for (Administrador administrador: administradores) {
    		for (Sede sede: administrador.getSedes()) {
	    		if (sede.getNombre().equals(nombre_sede)) {
	    			administrador.agregarAccesoAClase(clase);
	    		}
    		}
    	}
    	clases.add(clase);
    }
        
    //METODO PARA CREAR ARTICULOS NUEVOS
    public void crearArticulo() { 
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el tipo de artículo que desea crear: "
                + "\n\t PESA"
                + "\n\t COLCHONETA"
                + "\n\t ARTICULO PERSONALIZADO");
        String tipo = scanner.nextLine();
        if (tipo.toUpperCase().equals("PESA")) {
            System.out.println("Ingrese el peso: ");
            String pesoStr = scanner.nextLine();
            try {
                int peso = Integer.parseInt(pesoStr);
                System.out.println("Ingrese el uso que tendrá la pesa: "
                        + "\n\t DE MANO "
                        + "\n\t TOBILLERA"
                        + "\n\t DISCO");
                String uso = scanner.nextLine();
                Pesa pesa = new Pesa(tipo, peso, uso.toUpperCase());
                articulos.add(pesa);
            } catch (NumberFormatException e) {
                System.out.println("Peso ingresado no válido. No se ha podido crear el objeto.");
            }
        } else if (tipo.toUpperCase().equals("COLCHONETA")) {
            System.out.println("Ingrese el largo: ");
            String largoStr = scanner.nextLine();
            System.out.println("Ingrese el ancho: ");
            String anchoStr = scanner.nextLine();
            try {
                int largo = Integer.parseInt(largoStr);
                int ancho = Integer.parseInt(anchoStr);
                Colchoneta colchoneta = new Colchoneta(tipo, largo, ancho);
                articulos.add(colchoneta);
            } catch (NumberFormatException e) {
                System.out.println("Largo o ancho ingresado no válido. No se ha podido crear el objeto.");
            }
        } else if (tipo.toUpperCase().equals("ARTICULO PERSONALIZADO")) {
            System.out.println("Ingrese la descripcion del artículo: ");
            String descripcion = scanner.nextLine();
            ArticuloPersonalizado artp = new ArticuloPersonalizado(tipo, descripcion);
            articulos.add(artp);
        }
    }
    
    public void crearProfesor(String nombre, String disciplina) {
        for (Disciplina disc: disciplinas) {
            if (disc.getNombre().equals(disciplina)) {
                Profesor profesor = new Profesor(nombre, disc);
                profesores.add(profesor);
            } else {
                System.out.println("NO EXISTE LA DISCIPLINA INGRESADA.");
            }
        }
    }
    
    
     
    
    //AGRUPAR LOS SIGUIENTES METODOS
    public void darDeBajaProfesor(String nombre_profesor) {
    	profesores.removeIf(profe -> profe.getNombre().equals(nombre_profesor));
    }
    
    public ArrayList<Profesor> getProfesores(){
    	return this.profesores;
    }

	public ArrayList<Emplazamiento> getEmplazamientos() {
		return emplazamientos;
	}
	
	public ArrayList<Disciplina> getDisciplinas() {
		return this.disciplinas;
	}
	
	public void crearEmplazamiento(int capacidad, int superficie, String nombre) {
		Emplazamiento emplazamiento = new Emplazamiento(capacidad, superficie, nombre);
		this.emplazamientos.add(emplazamiento);
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