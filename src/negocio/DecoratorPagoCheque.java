package negocio;

/** Clase que representa una factura cuyo metodo de pago es cheque.
 */
public class DecoratorPagoCheque extends DecoratorPago{
	private static final double factor = 1.1;

    /** Constructor para crear una factura que se abonara con cheque.
     * @param encapsulado : factura a decorar. <br>
     * <b> Pre: </b>: encapsulado no puede ser null.
     */
    public DecoratorPagoCheque(IFactura encapsulado) {
        super(encapsulado);
    }

    /** Metodo para calcular el monto total a pagar de una factura aplicando el incremento del 10% por pagar con cheque.
     * @return double con el valor del monto total a abonar.
     */
    public double calcularTotalConDescuento() {
            return encapsulado.calcularTotalConDescuento() * factor;
    }

}
