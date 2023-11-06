package controladores;
import java.util.*;
import articulos.Articulo;
import sistema.*;

public class Administrador extends Usuario {
	
	private ArrayList<Sede> sedes_asignadas;
	private ArrayList<Clase> clases_a_administrar;

	public Administrador(String correo_electronico, String contraseña) {
		super(correo_electronico, contraseña);
	}
	
	public ArrayList<Sede> getSedes() {
		return this.sedes_asignadas;
	}

	public void agregarSede(Sede sede) {
		this.sedes_asignadas.add(sede);
	}
	
	public void agregarAccesoAClase(Clase clase) {
		clases_a_administrar.add(clase);
	}
	//METODO PARA AGREGAR MODIFICAR LA CANTIDAD DE ARTICULOS DE UNA SEDE
    public void agregarArticulos(Sede sede, Articulo articulo, int cantidad) {
    	sede.agregar_articulo(articulo, cantidad);
    }
    
    //METODO PARA DAR DE BAJA ARTICULOS
    public void eliminarArticuloDeSede(Sede sede, Articulo articulo_a_eliminar) {
    	sede.eliminarArticulo(articulo_a_eliminar);
    }
    
    //METODO PARA TRANSICIONAR ESTADO DE LAS CLASES
    public void transicionarEstadoClase(Clase clase, String estado) {
    	clase.setEstado(estado);
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