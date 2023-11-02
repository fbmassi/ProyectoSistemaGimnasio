package controladores;

import java.util.*;
import articulos.Articulo;
import sistema.*;

public class Administrador extends Usuario {
	
	private ArrayList<Clase> clases;
	private ArrayList<Sede> sedes;

	public Administrador(String correo_electronico, String contraseña) {
		super(correo_electronico, contraseña);
	}
	
	public ArrayList<Sede> getSedes() {
		return this.sedes;
	}

	public void setSedes(ArrayList<Sede> sedes) {
		this.sedes = sedes;
	}
    
    public void crearNuevaClase(Profesor profesor, Emplazamiento emplazamiento, Sede sede, Disciplina disciplina, String horario, String dia) {
    	Clase clase = new Clase(profesor, sede, emplazamiento, disciplina, dia, horario);
    	clases.add(clase);
    	//DUDA: VARIABLE... ES EL MISMO OBJETO?
    }
    
    public void crearNuevaSede(String nombre, String ubicacion, int nivel_suscripcion) {
    	Sede sede = new Sede (nombre,ubicacion,nivel_suscripcion);
    	sedes.add(sede);
    	//DUDA: VARIABLE... ES EL MISMO OBJETO?
    }

    public void agregarArticulos(Sede sede, Articulo articulo, int cantidad) {
    	sede.getCantidadStock().put(articulo, cantidad);
    }
    
    public void transicionarEstadoClase(Clase clase) {
    	//CODIFICAR TRANSICION DE ESTADO 
    }

    public boolean calcularRentabilidad(Clase clase) {
    	//CODIFICAR CALCULO DE RENTABILIDAD
        return false;
    }
    
	@Override
	public void visualizarClases() {
		//CODIFICAR VISUALIZACION
	}
	
	public void gestionarSede(Sede sede) {
		//VER QUE ONDA
    }
	
	public void solicitarAdministracion(Sede sede) {
		//VER QUE ONDA
    }

}