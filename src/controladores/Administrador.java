package controladores;
import java.util.*;
import articulos.Articulo;
import sistema.*;

public class Administrador extends Usuario {
	
	private ArrayList<Sede> sedes_asignadas;
	private ArrayList<Clase> clases_a_administrar;
	private Grabaciones grabaciones;

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

	public void setAccesoAGrabaciones(Grabaciones clases_grabadas) {
		this.grabaciones = clases_grabadas;
	}
	
	public void crearNuevaClase(Profesor profesor, Emplazamiento emplazamiento, Sede sede, Disciplina disciplina, String horario, String dia) {
    	Clase clase = new Clase(profesor, sede, emplazamiento, disciplina, dia, horario);
    	clases_a_administrar.add(clase);
		if (clase.getDisciplina().getVirtualidad()) {
            grabaciones.agregarClase(clase);
        }
    }

	//METODO PARA MOSTRAR CLASES GRABADAS
	public void verClasesGrabadaas(Grabaciones grabaciones) {
		grabaciones.mostrarGrabaciones();
	}

	//METODO PARA ELIMINAR CLASES GRABADAS
	public void eliminarClases(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese la cantidad de clases de Yoga y Gimnasia que quiere borrar" +
															"o '0' si no quiere borrar ninguna: ");
		String cant_clases_a_borrar = scanner.nextLine();
		try {
			int clases_a_borrar = Integer.parseInt(cant_clases_a_borrar);
			grabaciones.eliminarClases(clases_a_borrar);
		} catch (Exception e) {
			System.out.println("INGRESO NO VALIDO");
		}
	}

	//METODO PARA GESTIONAR LAS GRABACIONES DE CLASES
	public void gestionarGrabaciones() {
		verClasesGrabadaas(grabaciones);
		Scanner scanner = new Scanner(System.in);
		System.out.println("Desea eliminar algunas grabaciones? (S/N): ");
		String borrar = scanner.nextLine();
		if (borrar.toUpperCase() == "S") {
			eliminarClases();
		}
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