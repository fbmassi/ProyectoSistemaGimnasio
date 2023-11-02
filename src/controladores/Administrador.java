package controladores;

import java.util.*;

import articulos.Articulo;
import sistema.*;


/**
 * 
 */
public class Administrador extends Usuario {

    public Administrador(String correo_electronico, String contraseña, String rol) {
		super(correo_electronico, contraseña, rol);
	}

	public void gestionarSede(Sede sede) {

    }

    public void solicitarAdministracion(Sede sede) {
    }
    
    public void crearNuevaClase(Sede sede, String horiario, Disciplina disciplina, Profesor profesor) {
    }

    public void transicionarEstadoClase(Clase clase) {
    }

    public boolean calcularRentabilidad(Clase clase) {
        return false;
    }

    public int agregarArticulos(Articulo articulo, int cantidad) {
        return 0;
    }

}