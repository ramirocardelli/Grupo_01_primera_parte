package negocio;

/** Clase que representa una excepcion que se lanza cuando se ingresa un tipo de persona incorrecto.
 */
public class TipoIncorrectoPersonaException extends Exception{
	private static final long serialVersionUID = 1L;
	private String nombre,dni,tipo;

	public TipoIncorrectoPersonaException(String nombre, String dni, String tipo) {
		super();
		this.nombre = nombre;
		this.dni = dni;
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDni() {
		return dni;
	}

	public String getTipo() {
		return tipo;
	}
	
	
}
