package negocio;

/** Clase que representa un abonado que es de tipo persona juridica. No puede aceptar clonacion.
 */
public class PersonaJuridica extends Abonado {
	
    /** Constructor de 2 parametros String que crea un abonado de tipo persona juridica.
     * @param nombre : nombre de la persona juridica
     * @param dni : numero de documento de la persona jurdica. <br>
     * <b> Pre: </b> nombre y dni no pueden ser null ni " ". 
     * <b> Pre: </b> Se crea una persona juridica.
     */
    public PersonaJuridica(String nombre, String dni) {
		super(nombre, dni);
	}


    /** Implementacion del metodo clone() para clonar una persona juridica.
     * @throws CloneNotSupportedException siempre, la persona juridica no admite clonacion.
     */
    public Object clon() throws CloneNotSupportedException{
        throw new CloneNotSupportedException("Metodo clon no permitido para un tipo de persona juridica");
    }

	@Override
	public void findeMes(Factura factura) {
		factura.setPersonaJ(true);
		this.facturaPendiente.add(factura); //no cambia de estado y no pasa a moroso (no posee estados)
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
	public void contratarServicio(Contratacion contratacion) {
		// TODO Auto-generated method stub
		this.addContratacion(contratacion);
		
	}

	/**
    * Da de baja un servicio, eliminando la contratación de la lista de contrataciones. 
    * En caso de que no pueda eliminar la contratación, lanza excepción de pago o de domicilio erróneo
    * @param domicilio: Domicilio correspondiente a la contratación
    * @throws PagoException, DomicilioSinContratacionEnAbonadoException
    * <b> Pre: </b> El domicilio no debe ser nulo <br>
    * <b> Post: </b> Se elimina la contratacion de la lista <br>
    */
	@Override
	public void bajaServicio(Domicilio domicilio) throws DomicilioSinContratacionEnAbonadoException {
		this.eliminaContratacion(domicilio);
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
	public void pagaFactura(IFactura factura) {
		// TODO Auto-generated method stub
		this.abonarFactura(factura);
		
	}
}
