package negocio;

/**
 * Clase que implementa IState para modificar el comportamiento del Abonado cuando este es considerado
 * Moroso. 
 * @author Joaquin
 *
 */
public class MorosoState implements IState {
	private PersonaFisica persona;

	public MorosoState(PersonaFisica persona) {
		this.persona = persona;
	}
	
	@Override
	public void contratarServicio(Contratacion contratacion) throws PagoException {
		throw new PagoException("No puede contratar nuevo servicio porque es moroso");
	}

	@Override
	public void bajaServicio(Domicilio domicilio) throws PagoException {
		throw new PagoException("No puede dar de baja servicio porque es moroso");
	}

	@Override
	public void pagaFactura(IFactura factura) throws PagoException {
		//puede y 30% recargo
		this.persona.abonarFactura(new DecoratorPagoMoroso(factura));
	}
	
	
}
