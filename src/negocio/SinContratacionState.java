package negocio;

public class SinContratacionState implements IState{
	private PersonaFisica persona;
		
	public SinContratacionState(PersonaFisica persona) {
    	assert persona != null: "Persona nula";
		this.persona = persona;
	}
	
	@Override
	public void contratarServicio(Contratacion contratacion) throws PagoException{
    	assert contratacion != null: "Contratacion nula";
		this.persona.addContratacion(contratacion);
		this.persona.setEstado(new ConContratacionState(persona));
	}

	@Override
	public void bajaServicio(Domicilio domicilio) throws PagoException {
		throw new PagoException("El abonado: "+this.persona.getNombre() +" no puede dar de baja contratacion porque no cuenta con ningun servicio");
	}

	@Override
	public IFactura pagaFactura(IFactura factura) throws PagoException {
		throw new PagoException("El abonado: "+this.persona.getNombre() +" no puede pagar factura ya que se encuentra sin contrataciones");
	}

	@Override
	public void findeMes(Factura factura) {
		//un abonado sin contratacion no debe procesar nuevas facturas
	}
}
