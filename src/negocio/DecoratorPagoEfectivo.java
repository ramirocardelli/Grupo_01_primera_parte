package negocio;

/** Clase que representa una factura cuyo metodo de pago es efectivo.
 */
public class DecoratorPagoEfectivo extends DecoratorPago {
	private static final double factor = 0.8;
   
    /** Constructor para crear una factura que se abonara con efectivo.
     * @param encapsulado : factura a decorar. <br>
     * <b> Pre: </b>: encapsulado no puede ser null.
     */
    public DecoratorPagoEfectivo(IFactura encapsulado) {
        super(encapsulado);
    	assert encapsulado != null : "Encapsulado nulo";
    }
    
    @Override
	public double valorConDesc() {
		return encapsulado.valorConDesc()*factor;
	}
    
    @Override
	public String toString() {
		return this.encapsulado.toString()+"\t Metodo de pago: efectivo\n";
	}
}