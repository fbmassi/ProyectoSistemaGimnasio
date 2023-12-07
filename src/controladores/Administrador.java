package controladores;
import java.util.*;
import java.util.Map.Entry;

import articulos.*;
import interfaces.IngresoErroneo;
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

	public Clase crearNuevaClase(String nombre_profesor, String ubicacion_sede, String nombre_emplazamiento, String nombre_disciplina, String dia, String horario, String duracion) {
            Clase clase = new Clase(this.creador_ST, this.username, nombre_profesor, ubicacion_sede, nombre_emplazamiento, nombre_disciplina, dia, horario, duracion);
        return clase;       
    }
		
    //METODO PARA AGREGAR CLASES A UNA SEDE
    public void agregarClaseASedeAsignada(String nombre_profesor, String nombre_emplazamiento, String nombre_disciplina, String dia, String horario, String duracion, String ubicacion_sede) {
    	Clase clase = this.crearNuevaClase(nombre_profesor, ubicacion_sede, nombre_emplazamiento, nombre_disciplina, dia, horario, duracion);
        for (Sede sede : this.getSedes()){
            if (sede.getUbicacion().equals(ubicacion_sede.toUpperCase())) {
                sede.agregarClase(clase);
                this.creador_ST.agregarClase(clase);
            }
        }
        /*
        Clase clase = new Clase(creador_ST, this.username, nombre_profesor, nombre_emplazamiento, nombre_disciplina, dia, horario, duracion);
    	this.creador_ST.crearNuevaClase(username, nombre_profesor, ubicacion_sede, nombre_emplazamiento, nombre_disciplina, dia, horario, duracion);
        */
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
    				IngresoErroneo ingresoErroneo = new IngresoErroneo();
                                ingresoErroneo.setVisible(true);
                                ingresoErroneo.setLocationRelativeTo(null);
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
    				socio.setNivelSuscripción("Black");
    			} else if (nivel_suscripcion.toUpperCase().equals("GOLD")) {
    				socio.setNivelSuscripción("Gold");
    			} else {
    				System.out.println("Lo siento, el nivel de suscripcion deseado no existe.");
    			}
    		}
    	}
    }
    
    //METODO PARA MONITORIEAR LA CANTIDAD DE ARTICULOS EN UNA SEDE Y SU ESTADO DE DESGASTE
  	public String mostrarArticulosSede(String ubicacion_sede) {
  		String devolver = "";
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
  		devolver += "<p>PESAS: </p>";
  		for (Entry<Pesa, ArrayList<LoteDeArticulos>> parCV: tipos_de_pesa.entrySet() ) {
  			devolver += "<p>Articulo: " + parCV.getKey().getTipo() + " "
  	        		+ parCV.getKey().getMarca() + " - " + parCV.getKey().getUso()  + " - "
  	        		+ "Peso: " + parCV.getKey().getPeso() + "kg.</p>";
  			ArrayList<LoteDeArticulos> artPesas = parCV.getValue();
  			int numero_de_lote = 1;
  			if (parCV.getKey().getTipoAmortizacion().equals("POR USO")) {
	  			for (LoteDeArticulos lote: artPesas) {
	  				devolver += "<p>Lote nº: " + numero_de_lote + " - "
		  	        		+ "Cantidad disponible: " + lote.getCantidad() + " - " 
		  	        		+ "Duracion Máxima: " + lote.getMaxDuracion() + " - " 
							+ "Estado de desgaste: " + lote.getDesgastePorUso() + ".</p>";
					 numero_de_lote += 1;
	  			}
  			} else if (parCV.getKey().getTipoAmortizacion().equals("POR FECHA")) {
  				for (LoteDeArticulos lote: artPesas) {
  					devolver += "<p>Lote nº: " + numero_de_lote + " - "
		  	        		+ "Cantidad disponible: " + lote.getCantidad() + " - " 
		  	        		+ "Fecha de caducidad: " + lote.getFechaDeVencimiento()+ ".</p>";
					 numero_de_lote += 1;
	  			}
  			}
  		}
  		devolver += "<p>COLCHONETAS: </p>\"";
  		for (Entry<Colchoneta, ArrayList<LoteDeArticulos>> parCV: tipos_de_colchoneta.entrySet() ) {
  			devolver += "<p>Articulo: " + parCV.getKey().getTipo() + " "
  					+ parCV.getKey().getMarca() + " - "
  					+ "Dimensiones: "
  	        		+ parCV.getKey().getLargo() + "mts de largo,  "
  	        		+ parCV.getKey().getAncho() + "mts de ancho </p>";
  			ArrayList<LoteDeArticulos> artColchonetas = parCV.getValue();
  			int numero_de_lote = 1;
  			if (parCV.getKey().getTipoAmortizacion().equals("POR USO")) {
	  			for (LoteDeArticulos lote: artColchonetas) {
	  				devolver += "<p>Lote nº: " + numero_de_lote + " - "
		  	        		+ "Cantidad disponible: " + lote.getCantidad() + " - " 
		  	        		+ "Duracion Máxima: " + lote.getMaxDuracion() + " - " 
							+ "Estado de desgaste: " + lote.getDesgastePorUso() + ".</p>";
					 numero_de_lote += 1;
	  			}
  			} else if (parCV.getKey().getTipoAmortizacion().equals("POR FECHA")) {
  				for (LoteDeArticulos lote: artColchonetas) {
  					devolver += "<p>Lote nº: " + numero_de_lote + " - "
		  	        		+ "Cantidad disponible: " + lote.getCantidad() + " - " 
		  	        		+ "Fecha de caducidad: " + lote.getFechaDeVencimiento()+ ".</p>";
					 numero_de_lote += 1;
	  			}
  			}
  		}
  		devolver += "<p>PERSONALIZADOS: </p>\"";
  		for (Entry<ArticuloPersonalizado, ArrayList<LoteDeArticulos>> parCV: tipos_de_personalizados.entrySet() ) {
  			devolver += "<p>Articulo: "  + parCV.getKey().getTipo() + " " + parCV.getKey().getMarca() 
  					+ " - " + parCV.getKey().getDescripcion() + ".</p>";
  			ArrayList<LoteDeArticulos> artPersonalizados = parCV.getValue();
  			int numero_de_lote = 1;
  			if (parCV.getKey().getTipoAmortizacion().equals("POR USO")) {
	  			for (LoteDeArticulos lote: artPersonalizados) {
	  				devolver += "<p>Lote nº: "  + numero_de_lote + " - "
		  	        		+ "Cantidad disponible: " + lote.getCantidad() + " - " 
		  	        		+ "Duracion Máxima: " + lote.getMaxDuracion() + " - " 
							+ "Estado de desgaste: " + lote.getDesgastePorUso() + ".</p>";
					 numero_de_lote += 1;
	  			}
  			} else if (parCV.getKey().getTipoAmortizacion().equals("POR FECHA")) {
  				for (LoteDeArticulos lote: artPersonalizados) {
  					devolver += "<p>Lote nº: " + numero_de_lote + " - "
		  	        		+ "Cantidad disponible: " + lote.getCantidad() + " - " 
		  	        		+ "Fecha de caducidad: " + lote.getFechaDeVencimiento()  + ".</p>";
					 numero_de_lote += 1;
	  			}
  			}
  		}
  		devolver = strHTML(devolver);
  		return devolver;
	  	}

        //METODO PARA AGREGAR MODIFICAR LA CANTIDAD DE ARTICULOS DE UNA SEDE
        public void agregarArticulos(String ubicacion_sede, String tipo, String marca, String tipo_amortizacion, String fecha_de_creacion, String cantidad_a_agregar,
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
          				if(peso == pesa.getPeso() && tipo_amortizacion.toUpperCase().equals(pesa.getTipoAmortizacion())
          						&& uso.toUpperCase().equals(pesa.getUso().toUpperCase())
          						&& pesa.getMarca().equals(marca.toUpperCase())) {
          					sede.agregarArticulos(pesa, cantidad, fecha_de_creacion);
          					existe_articulo = true;
          				}
      				}
      			} else if (tipo.toUpperCase().equals("COLCHONETA")) {
      				int largo = Integer.parseInt(largoSTR);
      				int ancho = Integer.parseInt(anchoSTR);
      				for (Colchoneta colchoneta:tipos_de_colchoneta) {
          				if(colchoneta.getMarca().equals(marca.toUpperCase())
          						&& colchoneta.getTipoAmortizacion().equals(tipo_amortizacion.toUpperCase())
          						&& largo == colchoneta.getLargo() 
          						&& ancho == colchoneta.getAncho()) {
          					sede.agregarArticulos(colchoneta, cantidad, fecha_de_creacion);
          					existe_articulo = true;
          				}
      				}
      			} else if ((tipo.toUpperCase().equals("PERSONALIZADO")))
  					for (ArticuloPersonalizado articulo_personalizado: tipos_de_personalizados) {
  	    				if(articulo_personalizado.getMarca().equals(marca.toUpperCase())
  	    						&& tipo_amortizacion.toUpperCase().equals(articulo_personalizado.getTipoAmortizacion())
  	    						&& descripcion.toUpperCase().equals(articulo_personalizado.getDescripcion())) {
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
      public void eliminarArticuloDeSede(String ubicacion_sede, String tipo, String marca, String tipo_amortizacion,
	    		String pesoSTR, String uso, 
	    		String largoSTR, String anchoSTR, 
	    		String descripcion) {

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
          				if(peso == pesa.getPeso() && tipo_amortizacion.toUpperCase().equals(pesa.getTipoAmortizacion())
          						&& uso.toUpperCase().equals(pesa.getUso().toUpperCase())
          						&& pesa.getMarca().equals(marca.toUpperCase())) {
          					sede.eliminarArticulo(pesa);
          					existe_articulo = true;
          				}
      				}
      			} else if (tipo.toUpperCase().equals("COLCHONETA")) {
      				int largo = Integer.parseInt(largoSTR);
      				int ancho = Integer.parseInt(anchoSTR);
      				for (Colchoneta colchoneta:tipos_de_colchoneta) {
          				if(colchoneta.getMarca().equals(marca.toUpperCase())
          						&& colchoneta.getTipoAmortizacion().equals(tipo_amortizacion.toUpperCase())
          						&& largo == colchoneta.getLargo() 
          						&& ancho == colchoneta.getAncho()) {
          					sede.eliminarArticulo(colchoneta);
          					existe_articulo = true;
          				}
      				}
      			} else if ((tipo.toUpperCase().equals("PERSONALIZADO")))
  					for (ArticuloPersonalizado articulo_personalizado: tipos_de_personalizados) {
  	    				if(articulo_personalizado.getMarca().equals(marca.toUpperCase())
  	    						&& tipo_amortizacion.toUpperCase().equals(articulo_personalizado.getTipoAmortizacion())
  	    						&& descripcion.toUpperCase().equals(articulo_personalizado.getDescripcion())) {
  	    					sede.eliminarArticulo(articulo_personalizado);
  	    					existe_articulo = true;
  	    				}
  					}
      		}
      	}
      	
      	if (!existe_articulo && existe_sede) {
      		IngresoErroneo ingresoErroneo = new IngresoErroneo();
      		ingresoErroneo.setVisible(true);
                ingresoErroneo.setLocationRelativeTo(null);
      	} else if (!existe_sede) {
      		IngresoErroneo ingresoErroneo = new IngresoErroneo();
      		ingresoErroneo.setVisible(true);
                ingresoErroneo.setLocationRelativeTo(null);
      	}
  	}
    
	//METODO PARA MOSTRAR CLASES GRABADAS
	public String monitorearGrabaciones() {
            return creador_ST.getGrabaciones().mostrarGrabaciones();
	}
		
	//METODO PARA ELIMINAR CLASES GRABADAS
	public void eliminarClases(String cantidad_a_eliminar) {
		try {
			int clases_a_borrar = Integer.parseInt(cantidad_a_eliminar);
			creador_ST.getGrabaciones().eliminarClases(clases_a_borrar);
		} catch (NumberFormatException e) {
			IngresoErroneo ingresoErroneo = new IngresoErroneo();
                        ingresoErroneo.setVisible(true);
                        ingresoErroneo.setLocationRelativeTo(null);
		}
	}
	
    //METODO PARA TRANSICIONAR ESTADO DE LAS CLASES
    public void transicionarEstadoClase(String ubicacion, String tipo_disciplina, String dia, String horario, String estado) {
        boolean inscipcion_no_exitosa = true;
    	for (Clase clase: this.creador_ST.getClases()) {
    		int horario_entero = Integer.parseInt(horario);
    		if (clase.getSede().getUbicacion().equals(ubicacion.toUpperCase()) 
				&& clase.getDisciplina().getTipo().equals(tipo_disciplina.toUpperCase())
				&& clase.getDia().equals(dia.toUpperCase())
				&& clase.getHorario() == horario_entero) {
	    			clase.setEstado(estado);
	    			inscipcion_no_exitosa = false;
    		}
    	}
    	if (inscipcion_no_exitosa) {
    		IngresoErroneo ingresoErroneo = new IngresoErroneo();
    		ingresoErroneo.setVisible(true);
                ingresoErroneo.setLocationRelativeTo(null);
    	}
    }
    
    @Override
    public String visualizarClases() {
    	String devolver = "";
        for (Clase clase: this.getClases()) {
            if (!clase.getEstado().equals("FINALIZADA"))
                devolver += "<p>CLASE: " + clase.getSede().getUbicacion() + " - " + clase.getDisciplina().getTipo() 
                    + " - "  + clase.getDia() + " " + clase.getHorario() + "hs. - ESTADO: " + clase.getEstado() +".</p>";
            }
        devolver = strHTML(devolver);
        return devolver;
    }
    
    private String strHTML(String texto) {
        return "<html>" + texto + "</html>";
    }
	
}
