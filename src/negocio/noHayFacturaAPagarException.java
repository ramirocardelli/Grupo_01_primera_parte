package negocio;

public class noHayFacturaAPagarException extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dni;
	
	/**
	* Constructor con 1 parametro (dni) para crear una nueva excepción.
	* @param dni. <br>
	* <b> Pre: </b> dni distinto de null.<br>
	* <b> Post: </b> Se crea una nueva excepcion.
	*/
	public noHayFacturaAPagarException(String dni) {
		super();
		this.dni = dni;
	}

	public String getDni() {
		return dni;
	}
	
	
}
