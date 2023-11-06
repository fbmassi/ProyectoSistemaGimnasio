package controladores;
import sistema.Clase;

public class Socio extends Usuario {
	
	private Clase ultima_clase;
	private int nivel_suscripción;

    public Socio(String correo_electronico, String contraseña) {
        super(correo_electronico, contraseña);
    }
	
    public void pedirReseva(Clase clase) {
    		clase.agregarAlumno(this);
    }

	public int getNivelSuscripción() {
		return this.nivel_suscripción;
	}

	public void setNivelSuscripción(int nivel_suscripción) {
			this.nivel_suscripción = nivel_suscripción;
	}
	
	public Clase getUltimaClase() {
		return this.ultima_clase;
	}

	public void setUltimaClase(Clase ultima_clase) {
			this.ultima_clase = ultima_clase;
	}
	
	@Override
	public void visualizarClases() {
		// CODIFICAR VISUALIZACION DE CLASES
	}
	
}