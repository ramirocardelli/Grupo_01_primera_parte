package negocio;

/** Clase que crea para un abonado determinado, una factura decorada segun el metodo de pago. Se utiliza el patrón Factory.
 */
public class FactoryPagos {
    public FactoryPagos() {
        super();
    }
    
    
    /** Metodo para crear la factura correspondiente dependiendo el metodo de pago solicitado.
     * @param abonado : persona que contrata y abona los servicios.
     * @param metodoPago : metodo de pago de la factura.
     * @return IFactura segun el metodo de pago.
     * @throws Exception cuando el metodo de pago ingresado no es valido.
     * <b> Pre: </b> abonado no puede ser null, y metodoPago no puede ser null ni " ". <br>
     * <b> Post: </b> Se crea factura que contiene al abonado, y la decora segun el metodo de pago. 
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
