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
    }
    
    /** Metodo para calcular el monto total a pagar de una factura aplicando el descuento del 20% por abonar con efectivo.
     * @return double con el valor del monto total a abonar.
     */	
    public double calcularTotalConDescuento() {
        return encapsulado.calcularTotalSinDescuento() * factor;
    }
    
    @Override
	public String toString() {
		return this.encapsulado.toString()+"\t Metodo de pago: efectivo\n";
	}
}