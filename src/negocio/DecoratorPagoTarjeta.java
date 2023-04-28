package negocio;

public class DecoratorPagoTarjeta extends DecoratorPago {
	private static final double factor = 1.05;
	
    public DecoratorPagoTarjeta() {
        super();
    }

	public double calcularTotalSinDescuento() {
		return encapsulado.calcularTotalSinDescuento();
	}
	
	public double calcularTotalConDescuento() {
		return encapsulado.calcularTotalSinDescuento() * factor;
	}

}
