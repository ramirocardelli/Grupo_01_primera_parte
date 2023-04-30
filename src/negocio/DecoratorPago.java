package negocio;


/** Clase abstracta que representa el comportamiento de una factura segun su metodo de pago.
 */
public abstract class DecoratorPago implements IFactura{
    protected IFactura encapsulado;

    /** Constructor de un parametro IFactura que crea un nuevo objeto DecoratorPago con una IFactura como encapsulado.
     * @param encapsulado : factura que se quiere decorar. <br>
     * <b> Pre: </b> encapsulado no puede ser null.
     * 
     */
    public DecoratorPago(IFactura encapsulado) {
        super();
        this.encapsulado = encapsulado;
    }

    /** Metodo que se encarga de agregar una contratacion a la factura correspondiente. 
     * @param contratacion : contratacion que se desea agregar a la factura. <br>
     * <b> Pre: </b> contratacion no puede ser null. <br>
     * <b> Post: </b> Se ha agregado a la lista de contrataciones una nueva contratacion.
     */
    public void agregarContratacion(Contratacion contratacion) {
        encapsulado.agregarContratacion(contratacion);
	}

    /** Metodo para calcular el monto total de la factura (con todas sus contrataciones) sin aplicar el descuento por el medio de pago.
     * @return double con el valor total de una factura.
     */
    public double calcularTotalSinDescuento() {
            return encapsulado.calcularTotalSinDescuento();
    }
   
    @Override
	public void eliminarContratacion(Domicilio domicilio) throws DomicilioSinContratacionenAbonadoException {
			this.encapsulado.eliminarContratacion(domicilio);
	}
    
    @Override
	public boolean sinContratacion() {
		return this.encapsulado.sinContratacion();
	}
    
    @Override
	public Abonado getAbonado() {
		return this.encapsulado.getAbonado();
	}

	@Override
	public Contratacion getContratacion(Domicilio domicilio) {
		return this.encapsulado.getContratacion(domicilio);
	}

}
