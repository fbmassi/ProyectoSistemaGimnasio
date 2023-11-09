package sistema;

import java.util.*;

public class Grabaciones {
	
	private ArrayList<Clase> clases_grabadas;
	
	public void agregarClase(Clase clase){
		clases_grabadas.add(clase);
		if (clases_grabadas.size() > 10) {
			clases_grabadas.remove(0);
		}
	}

	
}
