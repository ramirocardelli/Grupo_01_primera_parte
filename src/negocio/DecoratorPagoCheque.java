package negocio;


/** Clase que representa una factura cuyo metodo de pago es cheque.
 */
public class DecoratorPagoCheque extends DecoratorPago {
	private static final double factor = 1.1;

    /** Constructor para crear una factura que se abonara con cheque.
     * @param encapsulado : factura a decorar. <br>
     * <b> Pre: </b>: encapsulado no puede ser null.
     */
    public DecoratorPagoCheque(IFactura encapsulado) {
        super(encapsulado);
    	assert encapsulado != null : "Encapsulado nulo";
    }

	@Override
	public String toString() {
		return this.encapsulado.toString()+"\t Metodo de pago: cheque\n";
	}

	@Override
	public double valorConDesc() {
		return encapsulado.valorConDesc()*factor;
	}
}
