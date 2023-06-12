package negocio;

/** Clase que representa una factura cuyo metodo de pago es tarjeta.
 */
public class DecoratorPagoTarjeta extends DecoratorPago {
	private static final double factor = 1.05;
	
    /** Constructor para crear una factura que se abonara con tarjeta.
     * @param encapsulado : factura a decorar. <br>
     * <b> Pre: </b>: encapsulado no puede ser null.
     */
    public DecoratorPagoTarjeta(IFactura encapsulado) {
        super(encapsulado);
    	assert encapsulado != null : "Encapsulado nulo";
    }
    
    @Override
	public double valorConDesc() {
		return encapsulado.valorConDesc()*factor;
	}
    
    @Override
	public String toString() {
		return this.encapsulado.toString()+"\t Metodo de pago: tarjeta\n";
	}
}
