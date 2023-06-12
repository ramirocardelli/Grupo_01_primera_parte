package negocio;

/** Clase que representa un abonado que es de tipo persona fisica. Puede aceptar clonacion.
 */
public class PersonaFisica extends Abonado {
	private IState estado = new SinContratacionState(this);
    /** Constructor de 2 parametros String que crea un abonado de tipo persona fisica.
     * @param nombre : nombre de la persona fisica
     * @param dni : numero de documento de la persona fisica. <br>
     * <b> Pre: </b> nombre y dni no pueden ser null ni " ". 
     * <b> Pre: </b> Se crea una persona fisica.
     */
    public PersonaFisica(String nombre, String dni) {
		super(nombre, dni);
	}

	@Override
	public void findeMes(Factura factura) {
		factura.setPersonaJ(false);
		if(facturaPendiente!=null) {
			//cambiar estado a moroso
		}
		else
			this.facturaPendiente.add(factura);
		
		//DELEGAR AL ESTADO
	}
	
	private void setEstado(IState estado) {
		this.estado = estado;
	}

	/**
    * Agrega una contratación a la lista de contrataciones.
    * En caso de que no pueda agregar la contratacion, lanza una excepción.
    * @param contratacion: Contratacion a agregar
    * @throws PagoException
    * <b> Pre: </b> la contratacion no debe ser nula <br>
    * <b> Post: </b> Se agrega la contratacion a la lista <br>
    */
	@Override
	public void contratarServicio(Contratacion contratacion) throws PagoException {
		// TODO Auto-generated method stub
		this.estado.contratarServicio(contratacion);
		
	}
	
	/** Da de baja un servicio, eliminando la contratación de la lista de contrataciones. 
    * En caso de que no pueda eliminar la contratación, lanza excepción de pago o de domicilio erróneo
    * @param domicilio: Domicilio correspondiente a la contratación
    * @throws PagoException, DomicilioSinContratacionEnAbonadoException
    * <b> Pre: </b> El domicilio no debe ser nulo <br>
    * <b> Post: </b> Se elimina la contratacion de la lista <br>
    */
	@Override
	public void bajaServicio(Domicilio domicilio) throws PagoException, DomicilioSinContratacionEnAbonadoException {
		// TODO Auto-generated method stub
		this.estado.bajaServicio(domicilio);
		
	}
	
    /**
    * Paga la primera factura pendiente, se pasa a la lista de facturas históricas.
    * En caso de que se pueda pagar la factura, lanza excepción de pago.
    * En caso de que sea persona física, delega al estado.
    * @param factura: IFactura ya decorada con el método de pago correspondiente.
    * @throws PagoException
    * <b> Pre: </b> La factura no debe ser nula <br>
    * <b> Post: </b> Se elimina la primera factura de la lista <br>
    */
	@Override
	public void pagaFactura(IFactura factura) throws PagoException {
		// TODO Auto-generated method stub
		this.estado.pagaFactura(factura);
		
	}
    
    //No es necesario sobreescribir el clon() xq no cambia del ya creado en la clase padre (como se extiende ya lo tiene)

}
