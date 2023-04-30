package negocio;

/** Clase que representa una excepcion que se lanza cuando no se encuentra ninguna contratacion para el domicilio ingresado.
 */
public class DomicilioSinContratacionException extends Exception {
	private static final long serialVersionUID = 1L;
	private Domicilio domicilio;

	public DomicilioSinContratacionException(Domicilio domicilio) {
		super();
		this.domicilio = domicilio;
	}

	public Domicilio getDomicilio() {
		return domicilio;
	}
	
}
