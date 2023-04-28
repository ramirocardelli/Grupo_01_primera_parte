package negocio;

public class DecoratorPagoEfectivo extends DecoratorPago {
	private static final double factor = 0.8;
    public DecoratorPagoEfectivo(IFactura encapsulado) {
        super(encapsulado);
    }

	public double calcularTotalSinDescuento() {
		return encapsulado.calcularTotalSinDescuento();
	}
	
	public double calcularTotalConDescuento() {
		return encapsulado.calcularTotalSinDescuento() * factor;
	}
}