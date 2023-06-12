package negocio;

public class ConContratacionState implements IState {
	private PersonaFisica persona;
	
    /** Constructor de 1 parametro PersonaFisica para crear un nuevo estado.
     * @param persona : Persona que tiene el estado correspondiente.
     * <b> Pre: </b> persona no es null <br>
     * <b> Post: </b> se creo el estado con la persona correspondiente.
     */
	public ConContratacionState(PersonaFisica persona) {
		this.persona = persona;
	}
	
	@Override
	public void contratarServicio(Contratacion contratacion) throws PagoException {
		this.persona.addContratacion(contratacion);
		
	}

	@Override
	public void bajaServicio(Domicilio domicilio) throws PagoException, DomicilioSinContratacionEnAbonadoException {
		this.persona.eliminaContratacion(domicilio);
		
	}

	@Override
	public void pagaFactura(IFactura factura) throws PagoException {
		this.persona.abonarFactura(factura);
	}
	
}
