package negocio;

/** Clase que representa una excepcion que se lanza cuando se quiere trabajar con una factura que no existe en el sistema.
 */
public class NoExisteFacturaException extends Exception {
	private static final long serialVersionUID = 1L;
	private String dni;
	private Contratacion nuevaContratacion;
	
	public NoExisteFacturaException(String dni, Contratacion nuevaContratacion) {
		super();
		this.dni = dni;
		this.nuevaContratacion = nuevaContratacion;
	}

	public String getdni() {
		return dni;
	}

	public Contratacion getNuevaContratacion() {
		return nuevaContratacion;
	}
}
