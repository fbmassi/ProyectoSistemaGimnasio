package sistema;
import articulos.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

public class LoteDeArticulos {
	
    private Articulo articulo;
    private ArrayList<Articulo> lote;
    private LocalDate fecha_de_creacion;
    private LocalDate fecha_de_vencimiento;
    private int desgaste_por_uso;
    private int max_duracion;
    private int cantidad;
    
    public LoteDeArticulos(Articulo articulo, int cantidad, String fecha_de_creacion) {
    	lote = new ArrayList<Articulo>();
    	this.articulo = articulo;
    	this.setFechaDeCreacion(stringToDate(fecha_de_creacion));
    	this.llenarLote(articulo, cantidad);
    	setDesgastePorUso(0);
    	if (this.getArticulo().getTipoAmortizacion().equals("POR USO")) {
    		this.max_duracion = articulo.getDuracion();
    	} else if (this.getArticulo().getTipoAmortizacion().equals("POR FECHA")) {
    		this.fecha_de_vencimiento = this.fecha_de_creacion.plusDays(this.getArticulo().getDuracion());
    	}
    	this.cantidad = cantidad;
    }
    
	public LocalDate getFechaDeCreacion() {
		return fecha_de_creacion;
	}

	public void setFechaDeCreacion(LocalDate fecha_de_creacion) {
		this.fecha_de_creacion = fecha_de_creacion;
	}

	public int getDesgastePorUso() {
		return desgaste_por_uso;
	}
        
        public void aumentarDesgaste(){
            desgaste_por_uso += 1;
        }
        
	public void setDesgastePorUso(int desgaste_por_uso) {
		this.desgaste_por_uso = desgaste_por_uso;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public ArrayList<Articulo> getLote() {
		return lote;
	}

	public void agregarAlLote(Articulo articulo) {
		lote.add(articulo);
	}

	public void llenarLote(Articulo articulo, int cantidad) {
		if (articulo instanceof Pesa) {
			for (int i = 0; i<cantidad; i++) {
				Articulo articulo_nuevo = articulo;
				agregarAlLote(articulo_nuevo);
			}
		}
	}
	
	private LocalDate stringToDate(String cadena) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate fecha = null;
	    try {
	        fecha = LocalDate.parse(cadena, formatter);
	        System.out.println("Fecha parseada: " + fecha);
	    } catch (Exception e) {
	        System.out.println("Error al parsear la cadena a fecha: " + e.getMessage());  
	    }
		return fecha;
	}

	public int getMaxDuracion() {
		return max_duracion;
	}

	public void setMaxDuracion(int max_duracion) {
		this.max_duracion = max_duracion;
	}

	public LocalDate getFechaDeVencimiento() {
		return fecha_de_vencimiento;
	}

	public Articulo getArticulo() {
		return articulo;
	}
}