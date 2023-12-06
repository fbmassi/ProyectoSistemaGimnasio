package interfazPrincipal;
import interfaces.*;
import controladores.*;
import sistema.*;
import articulos.*;

public class Main {
	
	public static void main(String[] args) {
		/*
                
            
		  Soporte Técnico:
	    	- Crear sedes.
	    	- Crear usuarios con perfiles 
	    	- Asignar sedes a administrativos.
	    	- Cargar tipos de clases.
	    	- Crear tipos de artículos.
		
            
		
                SoporteTécnico st = new SoporteTécnico("uade_TPO_POO");
		
		//MÉTODOS PARA CREAR SEDES
		st.crearNuevaSede("Belgrano", "Black");
		st.crearNuevaSede("Devoto", "Gold");
		st.crearNuevaSede("Palermo", "Gold");
		System.out.println("SEDES CREADAS");
		for (Sede sede: st.getSedes()) {
			System.out.println(sede.getUbicacion());
		}
		System.out.println("\n");
		
		//MÉTODOS PARA CREAR Y GESTIONAR ADMINISTRADORES Y USUARIOS
		System.out.println("ADMINISTRADORES CREADOS");
		st.crearAdmin("fbmassi", "Isaac");
		st.crearAdmin("lemassi", "Nala");
		st.crearAdmin("cgabaglio", "Mora");
		for (Administrador admin: st.getAdministradores()) {
			System.out.println(admin.getUsername());
		}
		System.out.println("\n");
		
		System.out.println("USUARIOS CREADOS");
		st.crearCliente("fbm", "Isa");
		st.crearCliente("lem", "Nal");
		st.crearCliente("cga", "Mor");
		for (Socio socio: st.getClientes()) {
			System.out.println(socio.getUsername());
		}
		System.out.println("\n");
		
		
		System.out.println("DAMOS DE BAJA LOS USUARIOS CGABAGBLIO Y CGA");
		st.darDeBajaUsuarios("cgabaglio");
		st.darDeBajaUsuarios("cga");
		System.out.println("ADMINISTRADORES");
		for (Administrador admin: st.getAdministradores()) {
			System.out.println(admin.getUsername());
		}
		System.out.println("CLIENTES");
		for (Socio socio: st.getClientes()) {
			System.out.println(socio.getUsername());
		}
		System.out.println("\n");
		
		
		//MÉTODOS PARA ASIGNAR SEDES A ADMINISTRADORES
		System.out.println("ASIGNAMOS SEDES A ADMINISTRADORES");
		st.asignarSede("Belgrano", "fbmassi");
		st.asignarSede("Devoto", "fbmassi");
		st.asignarSede("Palermo", "lemassi");
		for (Administrador admin: st.getAdministradores()) {
			for (Sede sede: admin.getSedes()) {
				System.out.println(admin.getUsername() + sede.getUbicacion());
			}
		}
		for (Sede sede: st.getSedes()) {
				System.out.println(sede.getUbicacion() + sede.getAdmin().getUsername());
			}
		System.out.println("\n");
		
		//CREO OBJETOS NECESARIOS
		st.crearDisciplina("crossfit", null);
		st.crearProfesor("Javier", "CROSSFIT");
		st.crearEmplazamiento("SALón", "25", "50");
		st.crearDisciplina("YOGA", "V");
		st.crearProfesor("Julieta", "YOGA");
		st.crearEmplazamiento("AIRE LIBRE", "15", "50");
		
		
		//MÉTODOS PARA CREAR CLASES
		System.out.println("CREAMOS CLASES");
		st.crearNuevaClase("fbmassi", "Javier", "Belgrano", "SALÓN", "CROSSFIT", "JUEVES", "18", "3");
		st.crearNuevaClase("fbmassi", "Julieta", "Devoto", "AIRE LIBRE", "yoga", "MIERCOLES", "18", "3");
		st.crearNuevaClase("lemassi", "Julieta", "Palermo", "SALÓN", "yoga", "VIERNES", "18", "3");
		st.crearNuevaClase("lemassi", "Julieta", "Palermo", "SALÓN", "yoga", "VIERNES", "11", "3");
		for (Clase clase: st.getClases()) {
			System.out.println(clase.getDisciplina().getTipo() + clase.getDia() + clase.getHorario());
		}
		for (Administrador admin: st.getAdministradores()) {
			for (Clase clase: admin.getClases()) {
				System.out.println(admin.getUsername() + clase.getDisciplina().getTipo() + clase.getDia() + clase.getHorario());
			}
		}
		System.out.println("\n");
		
		//MÉTODOS PARA CREAR ARTICULOS
		System.out.println("CREAMOS ARTICULOS");
		//crearArticulo(tipo, marca, amortizacion, duracionSTR, uso, peso, largo, ancho, String descripcion)
		st.crearArticulo("Pesa", "Gadnic", "por uso", "20", "de tobillera", "3", null, null, null);
		st.crearArticulo("Pesa", "IronMan", "por fecha", "200", "disco", "8", null, null, null);
		st.crearArticulo("Colchoneta", "SofTech", "por fecha", "400", null, null, "2", "1", null);
		st.crearArticulo("Colchoneta", "Pampero", "por uso", "25", null, null, "3", "1", null);
		for (Articulo art: st.getArticulos()) {
			System.out.println("Articulo: " + art.getTipo() + " - " + art.getMarca() + " - " + art.getTipoAmortizacion() + " - " + art.getDuracion());
		}
		System.out.println("\n");
		
		
		
		
		/*
		Cliente:
		    - Desde el perfil, reservar lugares para clases presenciales o virtuales según corresponda.
		
		System.out.println("NOS INSCRIBIMOS A UNA CLASE");
		String nombreUsuarioBuscado = "LEM";
		Socio socioBuscado = null;
		for (Socio socio : st.getClientes()) {
		    if (nombreUsuarioBuscado.equals(socio.getUsername())) {
		        socioBuscado = socio;
		        socioBuscado.setNivelSuscripción("BLACK");
			    socioBuscado.pedirReseva("BELGRANO", "CROSSFIT", "JUEVES", "18");
		    }
		}
		System.out.println("\n");
		
		/*
		 Administrativo:
			Agendar clases para sedes.
			Gestionar estados de clases y agregar nuevos artículos.
			Administrar alta, baja y modificación de clientes.
			Monitorear artículos (disponibilidad, desgaste) y dar de baja anticipada.
			Monitorear clases almacenadas en el sistema de streaming.
		
		
		//METODO PARA AGENDAR CLASES PARA SEDES.
		st.crearAdmin("cgabaglio", "Mora");
		st.crearNuevaSede("Recoleta", "Black");
		st.asignarSede("Recoleta", "cgabaglio");
		String nombreAdmoinBuscado = "cgabaglio";
		st.crearNuevaClase("cgabaglio", "Julieta", "Recoleta", "AIRE LIBRE", "yoga", "Lunes", "18", "3");
		for (Administrador admin: st.getAdministradores()) {
		    if (nombreAdmoinBuscado.toUpperCase().equals(admin.getUsername())) {
		    	System.out.println("ASIGNAMOS UNA CLASE A UNA SEDE");
		    	admin.agregarClaseASedeAsignada("julieta", "AIRE LIBRE", "yoga", "Lunes", "18", "3", "Recoleta");
		    	System.out.println(admin.getSedes().get(0).getClases().get(0).getSede().getUbicacion());
		    	
		    	
		    	System.out.println("\n");
		    	System.out.println("CAMBIAMOS EL ALTA Y EL NIVEL DE SUSCRICION");
		    	admin.gestionarAltaCliente("lem", "baja");
		    	admin.gestionarNivelSuscripcionCliente("fbm", "platinum");
		    	admin.gestionarAltaCliente("fbm", "baja");
		    	admin.gestionarNivelSuscripcionCliente("lem", "platinum");
				for (Socio socio : st.getClientes()) {
					System.out.println(socio.getUsername() + " " + socio.getNivelSuscripción() + " " + socio.isAlta());
				}
				admin.gestionarAltaCliente("lem", "alta");
		    	admin.gestionarNivelSuscripcionCliente("fbm", "oro");
		    	admin.gestionarAltaCliente("fbm", "alta");
		    	admin.gestionarNivelSuscripcionCliente("lem", "black");
		    	for (Socio socio : st.getClientes()) {
					System.out.println(socio.getUsername() + " " + socio.getNivelSuscripción() + " " + socio.isAlta());
				}
		    	
		    	
		    	System.out.println("\n");
		    	System.out.println("AGREGAR ARTICULOS A SEDES:");
		    	admin.agregarArticulos("Recoleta", "PESA", "8", "DE TOBILLERA", null, null, null, "1");
		    	admin.agregarArticulos("Recoleta", "COLCHONETA", null, null, "4", "1", null, "6");
		    	admin.agregarArticulos("Recoleta", "PESA", "8", "DE TOBILLERA", null, null, null, "10");
		    	admin.agregarArticulos("Recoleta", "PESA", "15", "DE MANO", null, null, null, "1");
		    	for (Sede sede: st.getSedes()) {
		    		if (sede.getUbicacion().equals("RECOLETA")) {
		    			admin.mostrarArticulosSede("RECOLETA");
		    		}
		    	}
		    	
		    	System.out.println("\n");
		    	System.out.println("ELIMINAR ARTICULOS A SEDES:");
		    	admin.eliminarArticuloDeSede("Recoleta", "PESA", "8", "DE TOBILLERA", null, null, null);
		    	for (Sede sede: st.getSedes()) {
		    		if (sede.getUbicacion().equals("RECOLETA")) {
		    			admin.mostrarArticulosSede("RECOLETA");
		    		}
		    	}
		    }
		    
		}
		
		*/
	
                
		SoporteTécnico soporteTécnico = new SoporteTécnico("uade_TPO_POO");
                
		PantallaInicio interfazInicio = new PantallaInicio();
                interfazInicio.setST(soporteTécnico);
                interfazInicio.setVisible(true);
                interfazInicio.setLocationRelativeTo(null);
		
	}
		
}

