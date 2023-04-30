package negocio;

/** Clase que representa una excepcion que se lanza cuando se ingresa un tipo de factura incorrecto.
 */
public class TipoFacturaIncorrecto extends Exception {
	private static final long serialVersionUID = 1L;
	private Abonado abonado;
	private String tipo;
	
	public TipoFacturaIncorrecto(Abonado abonado, String tipo) {
		super();
		this.abonado = abonado;
		this.tipo = tipo;
	}

	public Abonado getAbonado() {
		return abonado;
	}

	public String getTipo() {
		return tipo;
	}
	
	
}
