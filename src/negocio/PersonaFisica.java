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

	@Override
	public void contratarServicio(Contratacion contratacion) throws PagoException {
		// TODO Auto-generated method stub
		this.estado.contratarServicio(contratacion);
		
	}

	@Override
	public void bajaServicio(Domicilio domicilio) throws PagoException, DomicilioSinContratacionEnAbonadoException {
		// TODO Auto-generated method stub
		this.estado.bajaServicio(domicilio);
		
	}

	@Override
	public void pagaFactura(IFactura factura) throws PagoException {
		// TODO Auto-generated method stub
		this.estado.pagaFactura(factura);
		
	}
    
    //No es necesario sobreescribir el clon() xq no cambia del ya creado en la clase padre (como se extiende ya lo tiene)

}
