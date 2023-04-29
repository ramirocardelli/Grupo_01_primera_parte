package negocio;

/** Clase que crea para un abonado determinado, una factura decorada segun el metodo de pago. Se utiliza el patrón Factory.
 */
public class FactoryPagos {
    public FactoryPagos() {
        super();
    }
    
    
    /**
     * @param abonado
     * @param metodoPago
     * @return
     * @throws Exception
     * <b> Pre: </b> abonado no puede ser null, y metodoPago no puede ser null ni " ". <br>
     * <b> Post: </b> Se crea factura que contiene al abonado, y la decora usando el patrón decorator correspondiente segun el metodo de pago. 
     */
    public static IFactura factoryFactura(Abonado abonado, String metodoPago) throws Exception {
    	IFactura creado = null;
    	Factura factura = new Factura(abonado);
    	
    	if(metodoPago.equalsIgnoreCase("EFECTIVO"))
    		creado = new DecoratorPagoEfectivo(factura);
    	else if (metodoPago.equalsIgnoreCase("TARJETA"))
    		creado = new DecoratorPagoTarjeta(factura);
    	else if (metodoPago.equalsIgnoreCase("CHEQUE"))
    		creado = new DecoratorPagoCheque(factura);
    	
    	// excepcion o contrato?
         /* if(creado == null)
            throw new Exception("metodo de pago erroneo");	// hacer excep
    	*/
    	return creado;
    }
}
