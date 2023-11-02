package controladores;
import java.util.*;

public class Socio extends Usuario {

    public Socio(String correo_electronico, String contraseña, String rol) {
        super(correo_electronico, contraseña, rol);
    }
    
    private int nivel_suscripción;
	
    public void pedirReseva() {
    }

}