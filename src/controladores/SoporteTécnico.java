package controladores;
import java.util.*;
import articulos.*;
import interfaces.*;
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
    private String contraseña;
    
    public SoporteTécnico(String contraseña) {
        this.contraseña = contraseña;
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
    
    public void crearCliente(String username, String contraseña, String nivel_suscripcion) {
    	nivel_suscripcion = nivel_suscripcion.toUpperCase();
        if (!nivel_suscripcion.equals("BLACK") && !nivel_suscripcion.equals("PLATINUM") && !nivel_suscripcion.equals("GOLD")){
            IngresoErroneo ingresoErroneo = new IngresoErroneo();
            ingresoErroneo.setVisible(true);
            ingresoErroneo.setLocationRelativeTo(null);
        } else {
            Socio socio = new Socio(this, username, contraseña, nivel_suscripcion);
            clientes.add(socio);
        }
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
    	IngresoErroneo ingresoErroneo = new IngresoErroneo();
        Clase clase = new Clase(this, username_admin, nombre_profesor, ubicacion_sede, nombre_emplazamiento, nombre_disciplina, dia, horario, duracion);
    	if (clase.getEstado().equals("FALTA OBJETO")){	
            ingresoErroneo.setVisible(true);
            ingresoErroneo.setLocationRelativeTo(null);
        } else {	
            for (Administrador administrador: administradores) {
                for (Sede sede: administrador.getSedes()) {
                    if (sede.getUbicacion().equals(ubicacion_sede.toUpperCase())) {
                        administrador.agregarAccesoAClase(clase);
                        sede.agregarClase(clase);
                    }
                }
            }
            this.clases.add(clase);
        }
    }
        
    //METODO PARA CREAR ARTICULOS NUEVOS
    public void crearArticulo(String tipo, String marca, String amortizacion, String duracionSTR, String uso, String peso, String largo, String ancho, String descripcion) { 
        IngresoErroneo ingresoErroneo = new IngresoErroneo();
        if (tipo.toUpperCase().equals("PESA")) {
            try {
                int peso_entero = Integer.parseInt(peso);
                Pesa pesa = new Pesa(tipo, peso_entero, uso.toUpperCase(), marca, amortizacion, duracionSTR);
                articulos.add(pesa);
            } catch (NumberFormatException e) {
                ingresoErroneo.setVisible(true);
                ingresoErroneo.setLocationRelativeTo(null);
            }
        } else if (tipo.toUpperCase().equals("COLCHONETA")) {
            try {
                int largo_entero = Integer.parseInt(largo);
                int ancho_entero = Integer.parseInt(ancho);
                Colchoneta colchoneta = new Colchoneta(tipo, largo_entero, ancho_entero, marca, amortizacion, duracionSTR);
                articulos.add(colchoneta);
            } catch (NumberFormatException e) {
                ingresoErroneo.setVisible(true);
                ingresoErroneo.setLocationRelativeTo(null);
            }
        } else if (tipo.toUpperCase().equals("ARTICULO PERSONALIZADO")) {
            ArticuloPersonalizado artp = new ArticuloPersonalizado(tipo, descripcion, marca, amortizacion, duracionSTR);
            articulos.add(artp);
        } else {
                ingresoErroneo.setVisible(true);
                ingresoErroneo.setLocationRelativeTo(null);
        }
    }
    
    //METODOS PARA CREAR OTROS OBJETOS NECESARIOS
    public void crearProfesor(String nombre, String disciplina, String sueldoSTR) {
    	IngresoErroneo ingresoErroneo = new IngresoErroneo();
        NoExisteSede nes = new NoExisteSede();
        boolean existe_disciplina = false;
    	if (!disciplinas.isEmpty()) {
    		for (Disciplina disc: disciplinas) {
                    if (disc.getTipo().equals(disciplina.toUpperCase())) {
                            existe_disciplina = true;
                            Profesor profesor = new Profesor(this, nombre, disc);
                            try {
                                Long sueldo = Long.parseLong(sueldoSTR);
                                profesor.setSueldo(sueldo);
                                profesores.add(profesor);
                            } catch (NumberFormatException e) {
                                ingresoErroneo.setVisible(true);
                                ingresoErroneo.setLocationRelativeTo(null);
                            }
                            
                        }
                }
    	}
        if (disciplinas.isEmpty() || !existe_disciplina) {
            nes.setVisible(true);
            nes.setLocationRelativeTo(null);
    	}
    }
    
    public void darDeBajaProfesor(String nombre_profesor) {
    	profesores.removeIf(profe -> profe.getNombre().equals(nombre_profesor));
    }
    
    public void crearDisciplina(String nombre, String virtualidad) {
    	Disciplina disc = new Disciplina(nombre, virtualidad);
    	disciplinas.add(disc);
    }
    
    public void crearEmplazamiento(String tipo, String superficie, String capacidad) {
        Emplazamiento emplazamiento = new Emplazamiento(tipo, superficie, capacidad);
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
    
    public void agregarClase(Clase clase) {
        this.clases.add(clase);
    }
}