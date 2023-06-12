package negocio;

import java.io.Serializable;

public class ConContratacionState implements IState,Serializable{
	private PersonaFisica persona;

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
