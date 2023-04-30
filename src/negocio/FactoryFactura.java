package negocio;

/** Clase que crea para un abonado determinado, una factura decorada segun el metodo de pago. Se utiliza el patr�n Factory.
 */
public class FactoryFactura {
	
    public FactoryFactura() {
        super();
    }
    
    private IFactura factoryTipoFactura(Abonado abonado,String tipo) throws TipoFacturaIncorrecto {
    	IFactura creado=null;
    	if(tipo==null) {
    		creado=new Factura(abonado);
    	}
    	else
    		throw new TipoFacturaIncorrecto(abonado,tipo);
    	return creado;
    }
    
    /** Metodo para crear la factura correspondiente dependiendo el metodo de pago solicitado.
     * @param abonado : persona que contrata y abona los servicios.
     * @param metodoPago : metodo de pago de la factura.
     * @return IFactura segun el metodo de pago.
     * @throws TipoFacturaIncorrecto 
     * @throws Exception cuando el metodo de pago ingresado no es valido.
     * <b> Pre: </b> abonado no puede ser null, y metodoPago no puede ser null ni " ". <br>
     * <b> Post: </b> Se crea factura que contiene al abonado, y la decora segun el metodo de pago. 
     */
    public IFactura creaFactura(Abonado abonado, String metodoPago,String tipo) throws MetodoDePagoInvalidoException, TipoFacturaIncorrecto {
    	IFactura creado = factoryTipoFactura(abonado,tipo);
    	if(metodoPago.equalsIgnoreCase("EFECTIVO"))
    		creado = new DecoratorPagoEfectivo(creado);
    	else 
    		if (metodoPago.equalsIgnoreCase("TARJETA"))
    			creado = new DecoratorPagoTarjeta(creado);
    		else 
	    		if (metodoPago.equalsIgnoreCase("CHEQUE"))
	    			creado = new DecoratorPagoCheque(creado);
	    		else
	    			throw new MetodoDePagoInvalidoException(creado, metodoPago);
    	return creado;
    }
}
