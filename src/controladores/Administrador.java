package controladores;
import java.util.*;
import java.util.Map.Entry;

import articulos.*;
import sistema.*;

public class Administrador extends Usuario {
	
	public SoporteTécnico creador_ST;
	private ArrayList<Sede> sedes_asignadas;
	private ArrayList<Clase> clases_a_administrar;

	public Administrador(SoporteTécnico creador_ST, String username, String contraseña) {
		super(username, contraseña);
		this.creador_ST = creador_ST;
		this.sedes_asignadas = new ArrayList<Sede>();
		this.clases_a_administrar = new ArrayList<Clase>();
	}
	
	public ArrayList<Sede> getSedes() {
		return this.sedes_asignadas;
	}
	
	public ArrayList<Clase> getClases() {
		return this.clases_a_administrar;
	}
	
	public void crearNuevaSede(String ubicacion, String nivel_suscripcion) {
		Sede sede = new Sede(ubicacion, nivel_suscripcion);
		this.sedes_asignadas.add(sede);
		this.creador_ST.agregarSede(sede);
	}
	
	public void agregarSede(Sede sede) {
		this.sedes_asignadas.add(sede);
	}
	
	public void agregarAccesoAClase(Clase clase) {
		clases_a_administrar.add(clase);
	}

	public void crearNuevaClase(String nombre_profesor, String ubicacion_sede, String nombre_emplazamiento, String nombre_disciplina, String dia, String horario, String duracion) {
    	Clase clase = new Clase(this.creador_ST, this.username, nombre_profesor, ubicacion_sede, nombre_emplazamiento, nombre_disciplina, dia, horario, duracion);
    	clases_a_administrar.add(clase);
		if (clase.getDisciplina().getVirtualidad()) {
            creador_ST.guardarGrabacion(clase);
        }
    }
		
    //METODO PARA AGREGAR CLASES A UNA SEDE
    public void agregarClaseASedeAsignada(String nombre_profesor, String nombre_emplazamiento, String nombre_disciplina, String dia, String horario, String duracion, String ubicacion_sede) {
    	Clase clase = new Clase(creador_ST, this.username, nombre_profesor, nombre_emplazamiento, nombre_disciplina, dia, horario, duracion);
    	boolean asignacion_realizada = false;
    	for (Sede sede: sedes_asignadas) {
    		if (sede.getUbicacion().equals(ubicacion_sede.toUpperCase())) {
    			clase.setSede(sede);
    			sede.agregarClase(clase);
    			asignacion_realizada = true;
    			System.out.println("SE CONCRETÓ LA INSCRIPCION.");
    		}
    	}
    	if (!asignacion_realizada) {
    		System.out.println("LA SEDE SOLICITADA NO EXISTE, DEBE SOLICITAR SU CREACION AL SOPORTE TECNICO.");
    	}
    }
    
    //METODO PARA MODIFICAR EL ALTA DEL PERFIL DE CLIENTES
    public void gestionarAltaCliente(String username, String alta) {
    	for (Socio socio: this.creador_ST.getClientes()) {
    		if (socio.getUsername().equals(username.toUpperCase())){
    			if (alta.toUpperCase().equals("ALTA")){
    				socio.setAlta(true);
    			} else if (alta.toUpperCase().equals("BAJA")) {
    				socio.setAlta(false);
    			} else {
    				System.out.println("Lo siento, el estado ingresado no existe.");
    			}
    		}
    	}
    }
    
    //METODO PARA GESTIONAR NIVEL DE SUSCRIPCION
    public void gestionarNivelSuscripcionCliente(String username, String nivel_suscripcion) {
    	for (Socio socio: this.creador_ST.getClientes()) {
    		if (socio.getUsername().equals(username.toUpperCase())){
    			if (nivel_suscripcion.toUpperCase().equals("PLATINUM")) {
    				socio.setNivelSuscripción("Platinum");
    			} else if (nivel_suscripcion.toUpperCase().equals("BLACK")) {
    				socio.setNivelSuscripción("Black");;
    			} else if (nivel_suscripcion.toUpperCase().equals("ORO")) {
    				socio.setNivelSuscripción("Oro");;
    			} else {
    				System.out.println("Lo siento, el nivel de suscripcion deseado no existe.");
    			}
    		}
    	}
    }
    
    //METODO PARA MONITORIEAR LA CANTIDAD DE ARTICULOS EN UNA SEDE Y SU ESTADO DE DESGASTE
  	public void mostrarArticulosSede(String ubicacion_sede) {
  		HashMap<Pesa, ArrayList<LoteDeArticulos>> tipos_de_pesa = new HashMap<>();
  		HashMap<Colchoneta, ArrayList<LoteDeArticulos>> tipos_de_colchoneta = new HashMap<>();
  		HashMap<ArticuloPersonalizado, ArrayList<LoteDeArticulos>> tipos_de_personalizados = new HashMap<>();
  		for (Sede sede: this.creador_ST.getSedes()) {
      		if (sede.getUbicacion().equals(ubicacion_sede.toUpperCase())) {
      			HashMap<Articulo, ArrayList<LoteDeArticulos>> articulos_de_sede = sede.getCantidadStock();
      			for (Entry<Articulo, ArrayList<LoteDeArticulos>> parCV: articulos_de_sede.entrySet()) {
      				if (parCV.getKey() instanceof Pesa) {
      					tipos_de_pesa.put((Pesa) parCV.getKey(), parCV.getValue());
      				} else if (parCV.getKey() instanceof Colchoneta) {
      					tipos_de_colchoneta.put((Colchoneta) parCV.getKey(), parCV.getValue());
      				} else if (parCV.getKey() instanceof ArticuloPersonalizado) {
      					tipos_de_personalizados.put((ArticuloPersonalizado) parCV.getKey(), parCV.getValue());
      				}	
      	        }
      		}
  		}
  		System.out.println("PESAS: ");
  		for (Entry<Pesa, ArrayList<LoteDeArticulos>> parCV: tipos_de_pesa.entrySet() ) {
  			System.out.println("Articulo: " + parCV.getKey().getTipo() + " "
  	        		+ parCV.getKey().getUso()  + " - "
  	        		+ "Peso: " + parCV.getKey().getPeso() + "kg.");
  			ArrayList<LoteDeArticulos> artPesas = parCV.getValue();
  			int numero_de_lote = 1;
  			if (parCV.getKey().getTipoAmortizacion().equals("POR USO")) {
	  			for (LoteDeArticulos lote: artPesas) {
					System.out.println( "Lote nº: " + numero_de_lote + " - "
		  	        		+ "Cantidad disponible: " + lote.getCantidad() + " - " 
		  	        		+ "Duracion Máxima: " + lote.getMaxDuracion() + " - " 
							+ "Estado de desgaste: " + lote.getDesgastePorUso());
					 numero_de_lote += 1;
	  			}
  			} else if (parCV.getKey().getTipoAmortizacion().equals("POR FECHA")) {
  				for (LoteDeArticulos lote: artPesas) {
					System.out.println( "Lote nº: " + numero_de_lote + " - "
		  	        		+ "Cantidad disponible: " + lote.getCantidad() + " - " 
		  	        		+ "Fecha de caducidad: " + lote.getFechaDeVencimiento());
					 numero_de_lote += 1;
	  			}
  			}
  		}
  		System.out.println("COLCHONETAS: ");
  		for (Entry<Colchoneta, ArrayList<LoteDeArticulos>> parCV: tipos_de_colchoneta.entrySet() ) {
  			System.out.println("Articulo: " + parCV.getKey().getTipo() + " - "
  					+ "Dimensiones: "
  	        		+ parCV.getKey().getLargo() + "mts de largo,  "
  	        		+ parCV.getKey().getAncho() + "mts de ancho");
  			ArrayList<LoteDeArticulos> artColchonetas = parCV.getValue();
  			int numero_de_lote = 1;
  			if (parCV.getKey().getTipoAmortizacion().equals("POR USO")) {
	  			for (LoteDeArticulos lote: artColchonetas) {
					System.out.println( "Lote nº: " + numero_de_lote + " - "
		  	        		+ "Cantidad disponible: " + lote.getCantidad() + " - " 
		  	        		+ "Duracion Máxima: " + lote.getMaxDuracion() + " - " 
							+ "Estado de desgaste: " + lote.getDesgastePorUso());
					 numero_de_lote += 1;
	  			}
  			} else if (parCV.getKey().getTipoAmortizacion().equals("POR FECHA")) {
  				for (LoteDeArticulos lote: artColchonetas) {
					System.out.println( "Lote nº: " + numero_de_lote + " - "
		  	        		+ "Cantidad disponible: " + lote.getCantidad() + " - " 
		  	        		+ "Fecha de caducidad: " + lote.getFechaDeVencimiento());
					 numero_de_lote += 1;
	  			}
  			}
  		}
  		System.out.println("PERSONALIZADOS: ");
  		for (Entry<ArticuloPersonalizado, ArrayList<LoteDeArticulos>> parCV: tipos_de_personalizados.entrySet() ) {
  			System.out.println("Articulo: " + parCV.getKey().getTipo() + " - " + parCV.getKey().getDescripcion());
  			ArrayList<LoteDeArticulos> artPersonalizados = parCV.getValue();
  			int numero_de_lote = 1;
  			if (parCV.getKey().getTipoAmortizacion().equals("POR USO")) {
	  			for (LoteDeArticulos lote: artPersonalizados) {
					System.out.println( "Lote nº: " + numero_de_lote + " - "
		  	        		+ "Cantidad disponible: " + lote.getCantidad() + " - " 
		  	        		+ "Duracion Máxima: " + lote.getMaxDuracion() + " - " 
							+ "Estado de desgaste: " + lote.getDesgastePorUso());
					 numero_de_lote += 1;
	  			}
  			} else if (parCV.getKey().getTipoAmortizacion().equals("POR FECHA")) {
  				for (LoteDeArticulos lote: artPersonalizados) {
					System.out.println( "Lote nº: " + numero_de_lote + " - "
		  	        		+ "Cantidad disponible: " + lote.getCantidad() + " - " 
		  	        		+ "Fecha de caducidad: " + lote.getFechaDeVencimiento());
					 numero_de_lote += 1;
	  			}
  			}
  		}
	  	}

	    //METODO PARA AGREGAR MODIFICAR LA CANTIDAD DE ARTICULOS DE UNA SEDE
	    public void agregarArticulos(String ubicacion_sede, String tipo, String tipo_amortizacion, String fecha_de_creacion, String cantidad_a_agregar,
	    		String pesoSTR, String uso, 
	    		String largoSTR, String anchoSTR, 
	    		String descripcion) {
      	
      	int cantidad = Integer.parseInt(cantidad_a_agregar);
      	boolean existe_articulo = false;
      	boolean existe_sede = false;
      	
      	ArrayList<Pesa> tipos_de_pesa = new ArrayList<Pesa>();
      	ArrayList<Colchoneta> tipos_de_colchoneta = new ArrayList<Colchoneta>();
      	ArrayList<ArticuloPersonalizado> tipos_de_personalizados = new ArrayList<ArticuloPersonalizado>();
      	
      	for (Articulo articulo1: this.creador_ST.getArticulos()) {
  			if (articulo1 instanceof Pesa) {
  				tipos_de_pesa.add((Pesa) articulo1);
  			} else if (articulo1 instanceof Colchoneta) {
  				tipos_de_colchoneta.add((Colchoneta) articulo1);
  			} else if (articulo1 instanceof ArticuloPersonalizado && this.creador_ST.getArticulos().contains(articulo1)) {
  				tipos_de_personalizados.add((ArticuloPersonalizado) articulo1);
  			}
      	}
      	
      	for (Sede sede: creador_ST.getSedes()) {
      		if (sede.getUbicacion().equals(ubicacion_sede.toUpperCase())) {
      			existe_sede = true;
      			if (tipo.toUpperCase().equals("PESA")) {
      				int peso = Integer.parseInt(pesoSTR);
      				for (Pesa pesa:tipos_de_pesa) {
          				if(peso == pesa.getPeso() && tipo_amortizacion.equals(pesa.getTipoAmortizacion()) && uso.toUpperCase().equals(pesa.getUso())) {
          					sede.agregarArticulos(pesa, cantidad, fecha_de_creacion);
          					existe_articulo = true;
          				}
      				}
      			} else if (tipo.toUpperCase().equals("COLCHONETA")) {
      				int largo = Integer.parseInt(largoSTR);
      				int ancho = Integer.parseInt(anchoSTR);
      				for (Colchoneta colchoneta:tipos_de_colchoneta) {
          				if(largo == colchoneta.getLargo() && ancho == colchoneta.getAncho()) {
          					sede.agregarArticulos(colchoneta, cantidad, fecha_de_creacion);
          					existe_articulo = true;
          				}
      				}
      			} else if ((tipo.toUpperCase().equals("PERSONALIZADO")))
  					for (ArticuloPersonalizado articulo_personalizado: tipos_de_personalizados) {
  	    				if(descripcion.toUpperCase().equals(articulo_personalizado.getDescripcion())) {
  	    					sede.agregarArticulos(articulo_personalizado, cantidad, fecha_de_creacion);
  	    					existe_articulo = true;
  	    				}
  					}
      		}
      	}
      	
      	if (!existe_articulo && existe_sede) {
      		System.out.println("NO EXISTE EL ARTICULO INGRESADO, PRIMERO DEBE CREARLA EL ST.");
      		
      	} else if (!existe_sede) {
      		System.out.println("NO EXISTE LA SEDE SELECCIONADA, PRIMERO DEBE CREARLA EL ST.");
      	}
      } 	
      
      
      //METODO PARA DAR DE BAJA ARTICULOS
      public void eliminarArticuloDeSede(String ubicacion_sede, String tipo, String pesoSTR, String uso, String largoSTR, String anchoSTR, String descripcion) {

      	boolean existe_articulo = false;
      	boolean existe_sede = false;
      	
      	ArrayList<Pesa> tipos_de_pesa = new ArrayList<Pesa>();
      	ArrayList<Colchoneta> tipos_de_colchoneta = new ArrayList<Colchoneta>();
      	ArrayList<ArticuloPersonalizado> tipos_de_personalizados = new ArrayList<ArticuloPersonalizado>();
      	
      	for (Articulo articulo1: this.creador_ST.getArticulos()) {
  			if (articulo1 instanceof Pesa) {
  				tipos_de_pesa.add((Pesa) articulo1);
  			} else if (articulo1 instanceof Colchoneta) {
  				tipos_de_colchoneta.add((Colchoneta) articulo1);
  			} else if (articulo1 instanceof ArticuloPersonalizado && this.creador_ST.getArticulos().contains(articulo1)) {
  				tipos_de_personalizados.add((ArticuloPersonalizado) articulo1);
  			}
      	}
      	
      	for (Sede sede: creador_ST.getSedes()) {
      		if (sede.getUbicacion().equals(ubicacion_sede.toUpperCase())) {
      			existe_sede = true;
      			if (tipo.toUpperCase().equals("PESA")) {
      				int peso = Integer.parseInt(pesoSTR);
      				for (Pesa pesa:tipos_de_pesa) {
          				if(peso == pesa.getPeso() && uso.toUpperCase().equals(pesa.getUso())) {
          					sede.eliminarArticulo(pesa);
          					existe_articulo = true;
          				}
      				}
      			} else if (tipo.toUpperCase().equals("COLCHONETA")) {
      				int largo = Integer.parseInt(largoSTR);
      				int ancho = Integer.parseInt(anchoSTR);
      				for (Colchoneta colchoneta:tipos_de_colchoneta) {
          				if(largo == colchoneta.getLargo() && ancho == colchoneta.getAncho()) {
          					sede.eliminarArticulo(colchoneta);
          					existe_articulo = true;
          				}
      				}
      			} else if ((tipo.toUpperCase().equals("PERSONALIZADO")))
  					for (ArticuloPersonalizado articulo_personalizado: tipos_de_personalizados) {
  	    				if(descripcion.toUpperCase().equals(articulo_personalizado.getDescripcion())) {
  	    					sede.eliminarArticulo(articulo_personalizado);
  	    					existe_articulo = true;
  	    				}
  					}
      		}
      	}
      	
      	if (!existe_articulo && existe_sede) {
      		System.out.println("NO EXISTE EL ARTICULO INGRESADO.");
      		
      	} else if (!existe_sede) {
      		System.out.println("NO EXISTE LA SEDE SELECCIONADA.");
      	}
  	}
    
	//METODO PARA MOSTRAR CLASES GRABADAS
	public void monitorearGrabaciones() {
		creador_ST.getGrabaciones().mostrarGrabaciones();
	}
		
	//METODO PARA ELIMINAR CLASES GRABADAS
	public void eliminarClases(String cantidad_a_eliminar) {
		try {
			int clases_a_borrar = Integer.parseInt(cantidad_a_eliminar);
			creador_ST.getGrabaciones().eliminarClases(clases_a_borrar);
		} catch (Exception e) {
			System.out.println("INGRESO NO VALIDO");
		}
	}
	
    //METODO PARA TRANSICIONAR ESTADO DE LAS CLASES
    public void transicionarEstadoClase(Clase clase, String estado) {
    	clase.setEstado(estado);
    	this.creador_ST.getGrabaciones().agregarClase(clase);
    }
    
    @Override
    public String visualizarClases() {
            //CODIFICAR VISUALIZACION
        return "";
    }
	
}
