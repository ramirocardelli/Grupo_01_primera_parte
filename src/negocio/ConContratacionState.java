package negocio;

import java.io.Serializable;

public class ConContratacionState implements IState,Serializable{
	private PersonaFisica persona;

	public ConContratacionState(PersonaFisica persona) {
		this.persona = persona;
	}

	@Override
	public void contratarServicio(Contratacion contratacion) throws PagoException {
		assert contratacion != null: "Contratacion nula";
		this.persona.addContratacion(contratacion);
	}

	@Override
	public void bajaServicio(Domicilio domicilio) throws PagoException, DomicilioSinContratacionEnAbonadoException {
		if(persona.getCantContrataciones()>1) {
			this.persona.removeContratacion(domicilio);
			}
		else {
			if(!persona.isFacturaPorPagar()) { // si no hay facturas pendientes elimina la ultima contratacion y cambia de estado a sin contratacion
				this.persona.removeContratacion(domicilio);
				this.persona.setEstado(new SinContratacionState(persona));
			}
			else {
				throw new PagoException("El abonado:"+ this.persona.getNombre()+" no puede dar de baja su ultima contratacion ya que no ha pagado sus facturas pendientes");
			}
		}
	}

	@Override
	public IFactura pagaFactura(IFactura factura) throws PagoException {
    	this.persona.pagaFactura(factura);
    	return factura;
	}

	@Override
	public void findeMes(Factura factura) {
		if(persona.isFacturaPorPagar()) { //si ya existe una factura pendiente entonces pasa a estado moroso
			this.persona.setEstado(new MorosoState(persona));
		}
		this.persona.addFactHistorico(factura);
	}
	
}
