package negocio;

public class MetodoDePagoInvalidoException extends Throwable {
	private static final long serialVersionUID = 1L;
	private IFactura factura;
	private String metododePago;
	
	public MetodoDePagoInvalidoException(IFactura factura,String metododePago) {
		super();
		this.factura=factura;
		this.metododePago=metododePago;
    }

	public IFactura getFactura() {
		return factura;
	}

	public String getMetododePago() {
		return metododePago;
	}
}
