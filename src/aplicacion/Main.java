package aplicacion;
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
		 */
		
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
		//st.crearArticulo(tipo, peso, uso, largo, ancho, descripcion)
		st.crearArticulo("PESA", "8", "DE TOBILLERA", null, null, null);
		st.crearArticulo("PESA", "15", "DE MANO", null, null, null);
		st.crearArticulo("COLCHONETA", null, null, "2", "1", null);
		st.crearArticulo("COLCHONETA", null, null, "2", "1", null);
		for (Articulo art: st.getArticulos()) {
			System.out.println(art.getTipo());
		}
		System.out.println("\n");
		
		
		
		
		/*
		Cliente:
		    - Desde el perfil, reservar lugares para clases presenciales o virtuales según corresponda.
		*/
		
		String nombreUsuarioBuscado = "LEM";
		Socio socioBuscado = null;

		for (Socio socio : st.getClientes()) {
		    if (nombreUsuarioBuscado.equals(socio.getUsername())) {
		        socioBuscado = socio;
		        socioBuscado.setNivelSuscripción("BLACK");
			    socioBuscado.pedirReseva("BELGRANO", "CROSSFIT", "JUEVES", "18");
		    }
		}

		/*
		for (Socio socio: st.getClientes()) {
			System.out.println(socio.getUsername());
		}
		
		for (Socio socio: st.getClientes()) {
			System.out.println(socio.getUsername()=="FBM");
			if (socio.getUsername() == "FBM") {
				socio.setNivelSuscripción("BLACK");
				socio.pedirReseva("CROSSFIT", "JUEVES", "18");
		}
			/*
			if (socio.getUsername() == "LEM") {
				socio.setNivelSuscripción("Gold");
			}
			*/
		
		
		/*
		 Administrativo:
			Agendar clases para sedes asignadas.
			Gestionar estados de clases y agregar nuevos artículos.
			Administrar alta, baja y modificación de clientes.
			Monitorear artículos (disponibilidad, desgaste) y dar de baja anticipada.
			Monitorear clases almacenadas en el sistema de streaming.
		 */
		st.crearAdmin("cgabaglio", "Mora");
		String nombreAdmoinBuscado = "cgabaglio";
		st.crearNuevaClase("fbmassi", "Julieta", "Devoto", "AIRE LIBRE", "yoga", "MIERCOLES", "18", "3");
		Administrador adminBuscado = null;
		for (Administrador admin: st.getAdministradores()) {
		    if (nombreAdmoinBuscado.equals(admin.getUsername())) {
		    	adminBuscado = admin;
		    	adminBuscado.agregarClaseASede("BELGRANO", "CROSSFIT", "JUEVES", "18");
		    }
		}
		
		
		
		
		/*
		PantallaInicio interfazInicio = new PantallaInicio();
        interfazInicio.setVisible(true);
        interfazInicio.setLocationRelativeTo(null);
		 */
        
            
	}

}

