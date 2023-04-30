package negocio;

public class DomicilioSinContratacionenAbonadoException extends DomicilioSinContratacionException{
	private static final long serialVersionUID = 1L;
	private Abonado abonado;
	
	public DomicilioSinContratacionenAbonadoException(Domicilio domicilio, Abonado abonado) {
		super(domicilio);
		this.abonado = abonado;
	}

	public Abonado getAbonado() {
		return abonado;
	}
	
}
