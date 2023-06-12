package negocio;


public class FactoryDecoradoFactura {
	
	public FactoryDecoradoFactura() {
        super();
    }
	
	 
    /** Metodo para crear la factura correspondiente dependiendo el metodo de pago solicitado.
     * @param abonado : persona que contrata y abona los servicios.<br>
     * @param metodoPago : metodo de pago de la factura.<br>
     * @param tipo : tipo de factura<br>
     * @return IFactura segun el metodo de pago.<br>
     * @throws TipoFacturaIncorrecto cuando el tipo de factura es incorrecto.<br>
     * @throws MetodoDePagoInvalidoException cuando el metodo de pago ingresado no es valido.<br>
     * <b> Pre: </b> abonado no puede ser null, metodoPago no puede ser null ni vacio y tipo no puede ser vacio. <br>
     * <b> Post: </b> Se crea factura segun el tipo, que contiene al abonado, y la decora segun el metodo de pago. 
     */
    public IFactura creaFactura(IFactura factura, String metodoPago) throws MetodoDePagoInvalidoException {
    	if(metodoPago.equalsIgnoreCase("EFECTIVO"))
    		factura = new DecoratorPagoEfectivo(factura);
    	else if (metodoPago.equalsIgnoreCase("TARJETA"))
    		factura = new DecoratorPagoTarjeta(factura);
    	else if (metodoPago.equalsIgnoreCase("CHEQUE"))
	    	factura = new DecoratorPagoCheque(factura);
	    else
	    	throw new MetodoDePagoInvalidoException(factura, metodoPago);
    	
    	return factura;
    }
}
