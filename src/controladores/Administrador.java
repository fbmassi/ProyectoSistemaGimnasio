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
	
	//METODO PARA MONITORIEAR LA CANTIDAD DE ARTICULOS EN UNA SEDE Y SU ESTADO DE DESGASTE
	public void mostrarArticulosSede(Sede sede) {
		HashMap<Articulo, Integer> articulos_de_sede = sede.getCantidadStock();
		for (Map.Entry<Articulo, Integer > parCV: articulos_de_sede.entrySet() ) {
            System.out.println("Articulo: " + parCV.getKey().getTipo()
            		+ ", Estado de desgaste: " + parCV.getKey().getEstadoDesgaste()
            		+ ", Cantidad disponible: " + parCV.getValue());
        }
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
    
    //METODO PARA AGREGAR CLASES A UNA SEDE
    public void agregarClaseASede(Sede sede, Clase clase) {
    	sede.agregarClase(clase);
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