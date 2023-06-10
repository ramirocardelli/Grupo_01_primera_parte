package negocio;

public class SinContratacionState implements IState{
	private PersonaFisica persona;
		
	public SinContratacionState(PersonaFisica persona) {
		this.persona = persona;
	}
	
	@Override
	public void ContratarServicio() {
		
		
	}

	@Override
	public void BajaServicio() {
		//controlador.getVista().muestra("No puede dar de baja porque no tiene ningun servicio.");
	}

	@Override
	public void PagaFactura() {
		// no puede
	}
	

}
