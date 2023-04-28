package negocio;

public class FactoryPagos {
    public FactoryPagos() {
        super();
    }
        
    // Precondicion: abonado != null
    public static IFactura factoryFactura(Abonado abonado, String metodoPago) throws Exception {
    	IFactura creado = null;
    	
    	Factura factura = new Factura(abonado);
    	
    	if(metodoPago.equalsIgnoreCase("EFECTIVO"))
    		creado = new DecoratorPagoEfectivo(factura);
    	else if (metodoPago.equalsIgnoreCase("TARJETA"))
    		creado = new DecoratorPagoTarjeta(factura);
    	else if (metodoPago.equalsIgnoreCase("CHEQUE"))
    		creado = new DecoratorPagoCheque(factura);
    	
    	if(creado == null)
    		throw new Exception("metodo de pago erroneo");	// Mejorar esta excepción
    	
    	return creado;
    }
}
