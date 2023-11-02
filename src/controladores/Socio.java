package controladores;
import sistema.Clase;

public class Socio extends Usuario {

    public Socio(String correo_electronico, String contraseña, String rol) {
        super(correo_electronico, contraseña);
    }
    
    private int nivel_suscripción;
	
    public void pedirReseva(Clase clase) {
    	clase.agregarAlumno(this);
    }

	public int getNivelSuscripción() {
		return this.nivel_suscripción;
	}

	public void setNivelSuscripción(int nivel_suscripción) {
		this.nivel_suscripción = nivel_suscripción;
	}
	
	@Override
	public void visualizarClases() {
		// CODIFICAR VISUALIZACION DE CLASES
	}

}