package negocio;

public class DecoratorPagoEfectivo extends DecoratorPago {
	private static final double factor = 0.8;
    public DecoratorPagoEfectivo() {
        super();
    }

	public double calcularTotalSinDescuento() {
		return encapsulado.calcularTotalSinDescuento();
	}
	
	public double calcularTotalConDescuento() {
		return encapsulado.calcularTotalSinDescuento() * factor;
	}
}