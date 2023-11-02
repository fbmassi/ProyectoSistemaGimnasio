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
    
    public SoporteTécnico(String correo_electronico, String contraseña) {
		super(correo_electronico, contraseña);
	}

    public void crearUsuario(String correo_electronico, String contraseña) {
    	Administrador admin = new Administrador(correo_electronico, contraseña);
    	administradores.add(admin);
    	//DUDA: VARIABLE... ES EL MISMO OBJETO?
    }

    public void darDeBaja(Usuario usuario){
    	try {
    		administradores.removeIf(administrador -> administrador == usuario);
    	} catch (Exception e) {
    		clientes.removeIf(cliente -> cliente == usuario);
    	}
    }

    public void crearSede(String nombre, String ubicacion, int nivel_suscripcion) {
    	Sede sede = new Sede(nombre,ubicacion,nivel_suscripcion);
    	sedes.add(sede);
    }

    public void crearProfesor(String nombre, int dni, Disciplina disciplina) {
    	Profesor profesor = new Profesor(nombre, dni, disciplina);
    	profesores.add(profesor);
    }

    public void darDeBaja(Profesor profesor) {
    	profesores.removeIf(profe -> profe == profesor);
    }

    public void crearArticulo(String tipo, String descripcion, int estado_desgaste) {
    	ArticuloPersonalizado artp = new ArticuloPersonalizado(tipo, descripcion, estado_desgaste);
    	articulos.add(artp);
    	//PREGUNTAR TEMA DE PESAS Y COLCHONETAS
    }

	@Override
	public void visualizarClases() {
		// TODO Auto-generated method stub
	}
	
	 public void asignarSede(Sede sede, Administrador administrador) {
	    //VER QUE ONDA...	
	 }

}