package negocio;

public class SinContratacionState implements IState{
	private PersonaFisica persona;
		
	public SinContratacionState(PersonaFisica persona) {
    	assert persona != null: "Persona nula";
		this.persona = persona;
	}
	
	@Override
	public void contratarServicio(Contratacion contratacion) throws PagoException {
    	assert contratacion != null: "Contratacion nula";
		this.persona.addContratacion(contratacion);
		
	}

	@Override
	public void bajaServicio(Domicilio domicilio) throws PagoException {
		throw new PagoException("No puede dar de baja porque no tiene ningun servicio");
	}

	@Override
	public void pagaFactura(IFactura factura) throws PagoException {
		throw new PagoException("No puede pagar factura si no tiene contrataciones");
	}
	

}
