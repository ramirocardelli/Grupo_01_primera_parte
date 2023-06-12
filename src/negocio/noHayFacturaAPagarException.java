package negocio;

public class noHayFacturaAPagarException extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dni;

	public noHayFacturaAPagarException(String dni) {
		super();
		this.dni = dni;
	}

	public String getDni() {
		return dni;
	}
	
	
}
