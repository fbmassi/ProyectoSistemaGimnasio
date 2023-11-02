package controladores;
import java.util.*;

import articulos.Articulo;
import sistema.*;

public class SoporteTécnico extends Usuario {

	private ArrayList<Administrador> administradores;
    private ArrayList<Socio> clientes;
    private ArrayList<Profesor> profesores;
    private ArrayList<Sede> sedes;
    private ArrayList<Articulo> articulos;
    
    public SoporteTécnico(String correo_electronico, String contraseña, String rol) {
		super(correo_electronico, contraseña, rol);
	}

    public void crearUsuario(String correo_electronico, String contraseña, String rol) {
    }

    public void darDeBaja(Usuario usuario) {
    }

    public void crearSede(String nombre, String ubicacion, int nivel_suscripcion) {
    }

    public void asignarSede(Sede sede, Administrador administrador) {
    }

    public void crearProfesor(String nombre, int dni, Disciplina disciplina) {
    }

    public void darDeBaja(Profesor profesor) {
    }

    public void crearArticulo(Articulo articulo) {
    }

}