package negocio;

public class ConContratacionState implements IState {
	private PersonaFisica persona;

	public ConContratacionState(PersonaFisica persona) {
		this.persona = persona;
	}
	
	@Override
	public void ContratarServicio() {
		//puede
		
	}

	@Override
	public void BajaServicio() {
		//puede
		
	}

	@Override
	public void PagaFactura() {
		//puede
	}
	
	
}
