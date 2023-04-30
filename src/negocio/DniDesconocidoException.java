package negocio;

/**Clase que representa una excepcion que se lanza cuando se quiere acceder a un abonado utilizando un dni que no se encuentra en el sistema.
 */
public class DniDesconocidoException extends Exception{
	private static final long serialVersionUID = 1L;
	private String dni;
	
	public DniDesconocidoException(String dni) {
		super();
		this.dni = dni;
	}

	public String getDni() {
		return dni;
	}
	
	

}
