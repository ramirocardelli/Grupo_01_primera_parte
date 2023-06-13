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
    	assert nombre != null: "Nombre nulo";
    	assert dni != null: "DNI nulo";
	}

	@Override
	public void findeMes(Factura factura) {
    	assert factura != null: "Factura nula";
		factura.setPersonaJ(false);
		if(facturaPendiente!=null) {
			//cambiar estado a moroso
		}
		else
			this.facturaPendiente.add(factura);
		
		//DELEGAR AL ESTADO
	}
	
	private void setEstado(IState estado) {
    	assert estado != null: "Estado nulo";
		this.estado = estado;
	}

	@Override
	public void contratarServicio(Contratacion contratacion) throws PagoException{
    	assert contratacion != null: "Contratacion nula";
		this.estado.contratarServicio(contratacion);
		
	}

	@Override
	public void bajaServicio(Domicilio domicilio) throws PagoException, DomicilioSinContratacionEnAbonadoException {
    	assert domicilio != null: "Domicilio nulo";
		this.estado.bajaServicio(domicilio);
		
	}

	@Override
	public void pagaFactura(IFactura factura) throws PagoException {
    	assert factura != null: "Factura nula";
		this.estado.pagaFactura(factura);
		
	}
    
	
	@Override
	public String toString() {
		return "Persona Fisica: "+ nombre + ", DNI:" + dni + "\n\tcontrataciones:" + contrataciones+ "\n\t historicoFacturas=" + historicoFacturas + "\n\t facturaPendiente=" + facturaPendiente+"\n";	
	}
	
    //No es necesario sobreescribir el clon() xq no cambia del ya creado en la clase padre (como se extiende ya lo tiene)

}
