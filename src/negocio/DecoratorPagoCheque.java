package negocio;

public class DecoratorPagoCheque extends DecoratorPago{
	private static final double factor = 1.1;
	
    public DecoratorPagoCheque(IFactura encapsulado) {
        super(encapsulado);
    }

	public double calcularTotalSinDescuento() {
		return encapsulado.calcularTotalSinDescuento();
	}
	
	public double calcularTotalConDescuento() {
		return encapsulado.calcularTotalConDescuento() * factor;
	}

}
