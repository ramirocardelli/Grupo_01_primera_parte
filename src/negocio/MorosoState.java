package negocio;

/**
 * Clase que implementa IState para modificar el comportamiento del Abonado cuando este es considerado
 * Moroso. 
 * @author 	
 *
 */
public class MorosoState implements IState {
	private PersonaFisica persona;

	public MorosoState(PersonaFisica persona) {
    	assert persona != null: "Persona nula";
		this.persona = persona;
	}
	
	@Override
	public void contratarServicio(Contratacion contratacion) throws PagoException {
		throw new PagoException("El abonado: "+this.persona.getNombre() +" no puede contratar nuevo servicio porque es moroso");
	}

	@Override
	public void bajaServicio(Domicilio domicilio) throws PagoException {
		throw new PagoException("El abonado: "+this.persona.getNombre() +" no puede dar de baja servicio porque es moroso");
	}

	@Override
	public IFactura pagaFactura(IFactura factura) throws PagoException {
    	assert factura != null: "Factura nula";
    	this.persona.abonaFactura(); //retira la factura de facturas pendientes de pago
    	this.persona.setEstado(new ConContratacionState(persona));
		return new DecoratorPagoMoroso(factura);
	}

	@Override
	public void findeMes(Factura factura) {
		// El moroso a fin de mes no debe tramitar nuevas facturas pendientes (se interrumpen sus contrataciones hasta que abone su factura mas antigua)
	}
	
	
}
