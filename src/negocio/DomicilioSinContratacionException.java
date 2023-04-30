package negocio;

public class DomicilioSinContratacionException extends Exception {
	private static final long serialVersionUID = 1L;
	private Domicilio domicilio;
	private Abonado abonado;

	public DomicilioSinContratacionException(Domicilio domicilio,Abonado abonado) {
		super();
		this.domicilio = domicilio;
		this.abonado=abonado;
	}

	public Domicilio getDomicilio() {
		return domicilio;
	}

	public Abonado getAbonado() {
		return abonado;
	}
	
	
}
