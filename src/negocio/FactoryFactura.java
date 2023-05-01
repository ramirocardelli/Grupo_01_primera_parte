package negocio;

/** Clase que crea para un abonado determinado, una factura decorada segun el metodo de pago. Se utiliza el patron Factory.
 */
public class FactoryFactura {
	
    public FactoryFactura() {
        super();
    }

    /** Metodo que crea una factura si el tipo ingresado es correcto, de otra forma lanza excepcion.
     * @param abonado : abonado de quien se quire crear la factura<br>
     * @param tipo : tipo de factura<br>
     * @return IFactura creada<br>
     * @throws TipoFacturaIncorrecto excepcion que se lanza cuando el tipo de factura ingresado es incorrecto. <br>
     * <b> Pre: </b> abonado no debe ser null, y tipo no debe ser vacio<br>
     * <b> Post: </b> Crea una factura segun el tipo recibido. 
     */
    protected IFactura factoryTipoFactura(Abonado abonado, String tipo) throws TipoFacturaIncorrecto {
    	IFactura creado=null;
    	if(tipo == null)
    		creado=new Factura(abonado);
    	else
    		throw new TipoFacturaIncorrecto(abonado,tipo);
    	return creado;
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
    public IFactura creaFactura(Abonado abonado, String metodoPago,String tipo) throws MetodoDePagoInvalidoException, TipoFacturaIncorrecto {
    	IFactura creado = factoryTipoFactura(abonado,tipo);
    	
    	if(metodoPago.equalsIgnoreCase("EFECTIVO"))
    		creado = new DecoratorPagoEfectivo(creado);
    	else if (metodoPago.equalsIgnoreCase("TARJETA"))
    		creado = new DecoratorPagoTarjeta(creado);
    	else if (metodoPago.equalsIgnoreCase("CHEQUE"))
	    	creado = new DecoratorPagoCheque(creado);
	    else
	    	throw new MetodoDePagoInvalidoException(creado, metodoPago);
    	
    	return creado;
    }
}
