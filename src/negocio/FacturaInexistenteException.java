package negocio;

/** Clase que representa una excepcion que se lanza cuando la factura que se ingreso no existe en la base de datos.
 */
public class FacturaInexistenteException extends Exception{
	private static final long serialVersionUID = 1L;
	private Abonado abonado;
	private Contratacion contratacion;
	
	
	public FacturaInexistenteException(Abonado abonado, Contratacion contratacion) {
		this.abonado=abonado;
		this.contratacion= contratacion;
	}

	public Abonado getAbonado() {
		return abonado;
	}

	public Contratacion getContratacion() {
		return contratacion;
	}

}
