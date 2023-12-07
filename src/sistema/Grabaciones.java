package sistema;

import java.util.*;

public class Grabaciones {
	
	private ArrayList<Clase> yoga_virtual = new ArrayList<>();
	private ArrayList<Clase> gimnasia_virtual = new ArrayList<>();
	
	public void agregarClase(Clase clase){
		if (clase.getDisciplina().getTipo() == "YOGA") {
			if (yoga_virtual.size() >= 10) {
				yoga_virtual.remove(0);
				}
			yoga_virtual.add(clase);
			} else if (clase.getDisciplina().getTipo() == "GIMNASIA POSTURAL") {
			if (gimnasia_virtual.size() >= 15) {
				gimnasia_virtual.remove(0);
				} 
			gimnasia_virtual.add(clase);
			}
		}

	public void mostrarGrabaciones() {
		for (Clase clase: yoga_virtual) {
			System.out.println("Clase: " + clase.getDisciplina().getTipo() + ", Dia" + clase.getDia() + ", " + clase.getHorario());
		}
		for (Clase clase: gimnasia_virtual) {
			System.out.println("Clase: " + clase.getDisciplina().getTipo() + ", Dia" + clase.getDia() + ", " + clase.getHorario());
		}
	}

	public void eliminarClases(int cantidad_clases_a_eliminar) {
		for (int i = 0; i < cantidad_clases_a_eliminar; i++) {
			yoga_virtual.remove(0);
			gimnasia_virtual.remove(0);
		}
	}
	}
