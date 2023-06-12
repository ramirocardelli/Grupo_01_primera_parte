package negocio;

/** Clase que representa una excepcion que se lanza cuando se ingresa un metodo de pago invalido.
 */
public class MetodoDePagoInvalidoException extends Throwable {
	private static final long serialVersionUID = 1L;
	private IFactura factura;
	private String metodoDePago;
	
	/**
	* Constructor con 2 parametros (factura y metodoDePago) para crear una nueva excepción.
	* @param factura. <br>
	* @param metodoDePago. <br>
	* <b> Pre: </b> abonado y contratacion distinto de null.<br>
	* <b> Post: </b> Se crea una nueva excepcion.
	*/
	public MetodoDePagoInvalidoException(IFactura factura,String metodoDePago) {
		super();
		this.factura=factura;
		this.metodoDePago=metodoDePago;
    }

	public IFactura getFactura() {
		return factura;
	}

	public String getMetodoDePago() {
		return metodoDePago;
	}
}
