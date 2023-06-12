package negocio;

public class DecoratorPagoMoroso extends DecoratorPago implements IFactura {
	private static final double factor = 1.3;

    /** Constructor para crear una factura que se abona de forma tardia.
     * @param encapsulado : factura a decorar. <br>
     * <b> Pre: </b>: encapsulado no puede ser null.
     */
    public DecoratorPagoMoroso(IFactura encapsulado) {
        super(encapsulado);
    	assert encapsulado != null : "Encapsulado nulo";
    }

	@Override
	public String toString() {
		return this.encapsulado.toString()+" - moroso";
	}

	@Override
	public double valorConDesc() {
		return encapsulado.valorConDesc()*factor;
	}
}
