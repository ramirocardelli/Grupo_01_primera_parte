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
    	assert nombre != null: "Nombre nulo";
    	assert nombre != "": "Nombre vacio";
    	assert dni != null: "DNI nulo";
    	assert dni != "": "DNI vacio";
	}


    /** Implementacion del metodo clone() para clonar una persona juridica.
     * @throws CloneNotSupportedException siempre, la persona juridica no admite clonacion.
     */
    public Object clon() throws CloneNotSupportedException{
        throw new CloneNotSupportedException("Metodo clon no permitido para un tipo de persona juridica");
    }

	@Override
	public void findeMes(Factura factura) {
    	assert factura != null: "Factura nula";
		factura.setPersonaJ(true);
		if(this.contrataciones.size()!=0) { //no agrega facturas si no hay contrataciones
			this.facturaPendiente.add(factura);
		}
	}


	@Override
	public void contratarServicio(Contratacion contratacion) {
    	assert contratacion != null: "Contratacion nula";
        contrataciones.put(contratacion.getDomicilio(), contratacion);
	}


	@Override
	public void bajaServicio(Domicilio domicilio) throws DomicilioSinContratacionEnAbonadoException {
    	assert domicilio != null: "Domicilio nulo";
    	if(this.contrataciones.remove(domicilio)==null) {
        	throw new DomicilioSinContratacionEnAbonadoException(domicilio, this);
        }
	}


	@Override
	public IFactura pagaFactura(IFactura factura) {
    	assert factura != null: "Factura nula";
    	this.historicoFacturas.put(factura.getMesYAnio(),factura);
    	this.facturaPendiente.removeFirst();
    	return factura ;
	}
	
	@Override
	public String toString() {
		return "Persona Fisica: "+ nombre + ", DNI:" + dni + "\n\tcontrataciones:" + contrataciones+ "\n\t historicoFacturas=" + historicoFacturas + "\n\t facturaPendiente=" + facturaPendiente+"\n";	
	}
}
