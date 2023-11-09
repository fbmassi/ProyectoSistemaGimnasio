package controladores;
import java.util.*;
import articulos.*;
import sistema.*;

public class SoporteTécnico extends Usuario {

	private ArrayList<Administrador> administradores;
    private ArrayList<Socio> clientes;
    private ArrayList<Profesor> profesores;
    private ArrayList<Sede> sedes;
    private ArrayList<Articulo> articulos;
    private ArrayList<Clase> clases;
    private Grabaciones grabaciones;
    
    public SoporteTécnico(String correo_electronico, String contraseña) {
		super(correo_electronico, contraseña);
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
    
    public void darDeBajaUsuarios(Usuario usuario){
    	try {
    		administradores.removeIf(administrador -> administrador == usuario);
    	} catch (Exception e) {
    		clientes.removeIf(cliente -> cliente == usuario);
    	}
    }
    
    //METODO PARA CREAR SEDES
    public void crearNuevaSede(String nombre, String ubicacion, int nivel_suscripcion) {
    	Sede sede = new Sede (nombre,ubicacion,nivel_suscripcion);
    	sedes.add(sede);
    }
    
    //METODO PARA ASIGNAR LA SEDE A UN ADMINISTRADOR
    public void asignarSede(Sede sede, Administrador administrador) {
 	   administrador.agregarSede(sede);	
 	}
    
    //METODO PARA CREAR CLASES
    public void crearNuevaClase(Profesor profesor, Emplazamiento emplazamiento, Sede sede, Disciplina disciplina, String horario, String dia) {
    	Clase clase = new Clase(profesor, sede, emplazamiento, disciplina, dia, horario);
    	for (Administrador administrador: administradores) {
    		if (administrador.getSedes().contains(sede)) {
    			administrador.agregarAccesoAClase(clase);
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
    
    public void crearProfesor(String nombre, int dni, Disciplina disciplina) {
    	Profesor profesor = new Profesor(nombre, dni, disciplina);
    	profesores.add(profesor);
    }

    public void darDeBajaProfesor(Profesor profesor) {
    	profesores.removeIf(profe -> profe == profesor);
    }
    
	@Override
	public void visualizarClases() {
		//CODIFICAR
	}

}