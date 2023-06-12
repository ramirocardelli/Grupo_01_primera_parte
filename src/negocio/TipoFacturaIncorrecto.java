package negocio;

/** Clase que representa una excepcion que se lanza cuando se ingresa un tipo de factura incorrecto.
 */
public class TipoFacturaIncorrecto extends Exception {
	private static final long serialVersionUID = 1L;
	private String tipo;
	
	public TipoFacturaIncorrecto( String tipo) {
		super();
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}
	
	
}
