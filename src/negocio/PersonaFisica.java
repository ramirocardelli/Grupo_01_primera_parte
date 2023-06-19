package negocio;

/** Clase que representa un abonado que es de tipo persona fisica. Puede aceptar clonacion.
 */
public class PersonaFisica extends Abonado {
	private IState estado;
    /** Constructor de 2 parametros String que crea un abonado de tipo persona fisica.
     * @param nombre : nombre de la persona fisica
     * @param dni : numero de documento de la persona fisica. <br>
     * <b> Pre: </b> nombre y dni no pueden ser null ni " ". 
     * <b> Pre: </b> Se crea una persona fisica.
     */
    public PersonaFisica(String nombre, String dni) {
		super(nombre, dni);
		this.estado= new SinContratacionState(this);
    	assert nombre != null: "Nombre nulo";
    	assert dni != null: "DNI nulo";
	}

    public void setEstado(IState estado) {
    	assert estado != null: "Estado nulo";
		this.estado = estado;
	}
    
	@Override
	public void findeMes(Factura factura) {
		assert factura != null: "Factura nula";
		factura.setPersonaJ(false);
    	this.estado.findeMes(factura);
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
	public IFactura pagaFactura(IFactura factura) throws PagoException {
    	assert factura != null: "Factura nula";
		return this.estado.pagaFactura(factura);
	}
    
	public boolean isFacturaPorPagar() {
		boolean rta=false;
		if(this.facturaPendiente.size()>0) {
			rta=true;
		}
		return rta;
	}
	
	public int getCantContrataciones() {
		return this.contrataciones.size();
	}
	
	public void addContratacion(Contratacion contratacion) {
		assert contratacion != null: "Contratacion nula";
        contrataciones.put(contratacion.getDomicilio(), contratacion);
	}
	
	public void removeContratacion(Domicilio domicilio) throws DomicilioSinContratacionEnAbonadoException {
    	assert domicilio != null: "Domicilio nulo";
    	if(this.contrataciones.remove(domicilio)==null) {
        	throw new DomicilioSinContratacionEnAbonadoException(domicilio, this);
        }
	}
	
	public void addFactHistorico (Factura factura) {
	    assert factura != null: "Factura nula";
	    this.historicoFacturas.put(factura.getMesYAnio(),factura);
	    this.facturaPendiente.removeFirst();
	}
	
	public void abonaFactura() {
		this.facturaPendiente.removeFirst();
	}
	
	@Override
	public String toString() {
		return "Persona Fisica: "+ nombre + ", DNI:" + dni + "\n\tcontrataciones:" + contrataciones+ "\n\t historicoFacturas=" + historicoFacturas + "\n\t facturaPendiente=" + facturaPendiente+"\n";	
	}
	
    //No es necesario sobreescribir el clon() xq no cambia del ya creado en la clase padre (como se extiende ya lo tiene)

}
