package negocio;

import java.io.Serializable;

/** Clase que representa una excepcion que se lanza cuando el abonado que se quiere cargar a la lista ya existe.
 */
public class AbonadoYaCargadoException extends Exception implements Serializable{
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String dni;
	
	public AbonadoYaCargadoException(String nombre,String dni) {
		this.nombre=nombre;
		this.dni=dni;
    }

	public String getNombre() {
		return nombre;
	}

	public String getDni() {
		return dni;
	}
}
