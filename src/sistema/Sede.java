package sistema;
import java.util.*;
import articulos.Articulo;
import controladores.Administrador;

public class Sede {
	
	private Administrador admin;
    private String ubicacion;
    private String nivel_suscripcion;
    private ArrayList<Clase> clases_en_sede;
    private HashMap<Articulo, Integer> cantidad_stock;

    public Sede(String ubicacion, String nivel_suscripcion) {
    	this.ubicacion = ubicacion.toUpperCase();
    	this.nivel_suscripcion = nivel_suscripcion.toUpperCase();
    	this.clases_en_sede = new ArrayList<Clase>();
    	this.cantidad_stock = new HashMap<>();
    }
    
    public Administrador getAdmin() {
		return admin;
	}

	public void setAdmin(Administrador admin) {
		this.admin = admin;
	}
	
	public String getNivelSuscripcion() {
		return this.nivel_suscripcion;
	}

	public void setNivelSuscripcion(String nivel_suscripcion2) {
		this.nivel_suscripcion = nivel_suscripcion2;
	}

	public String getUbicacion() {
		return this.ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public HashMap<Articulo, Integer> getCantidadStock() {
		return this.cantidad_stock;
	}
	
	public void agregar_articulo(Articulo articulo, int cantidad) {
		if (this.cantidad_stock.containsKey(articulo)) {
    		this.cantidad_stock.put(articulo, this.cantidad_stock.get(articulo) + cantidad);
    	} else {
    		this.cantidad_stock.put(articulo, cantidad);
    	}
	}
	
	public void eliminarArticulo(Articulo articulo) {
		if (cantidad_stock.containsKey(articulo)) {
			cantidad_stock.remove(articulo);
		}
	}
	
	public void agregarClase(Clase clase) {
		this.clases_en_sede.add(clase);
	}
	
	public void removerClasesFinalizadas() {
		for (Clase clase: clases_en_sede) {
			if (clase.getEstado()=="FINALIZADA") {
				this.clases_en_sede.remove(clase);
			}
		}
	}
	
	public void setCantidadStock(HashMap<Articulo, Integer> cantidad_stock) {
		//CODIFICAR TEMA CANTIDAD DE STOCK
	}
    
    public void alertarBajoStock(int CantidadStock, int cant_pesas, int cant_colchonetas) {
    	//CODIFICAR ALERTA DE BAJO STOCK
    }
    
    public void sacarStock(Articulo articulo, int cantidad_stock) {
    }

}