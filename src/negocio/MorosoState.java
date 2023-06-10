package negocio;

public class MorosoState implements IState {
	private PersonaFisica persona;

	public MorosoState(PersonaFisica persona) {
		this.persona = persona;
	}
	
	
	@Override
	public void ContratarServicio() {
		// no puede
	}

	@Override
	public void BajaServicio() {
		//no puede
	}

	@Override
	public void PagaFactura() {
		//puede y 30% recargo
	}
	
	
}
