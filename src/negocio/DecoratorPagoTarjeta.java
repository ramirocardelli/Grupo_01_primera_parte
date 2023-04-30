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
    }
    
    /** Metodo para calcular el monto total a pagar de una factura aplicando el incremento del 5% por pagar con tarjeta.
     * @return double con el valor del monto total a abonar.
     */	
    public double calcularTotalConDescuento() {
            return encapsulado.calcularTotalSinDescuento() * factor;
    }
    
    @Override
	public String toString() {
		return this.encapsulado.toString()+"\t Metodo de pago: tarjeta\n";
	}
}
