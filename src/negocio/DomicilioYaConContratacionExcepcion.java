package negocio;

public class DomicilioYaConContratacionExcepcion extends Exception{
	private static final long serialVersionUID = 1L;
	private Domicilio domicilio;
	private Contratacion nuevacontratacion,contratacionexistente;
	
	public DomicilioYaConContratacionExcepcion(Domicilio domicilio,Contratacion nuevaContratacion, Contratacion contratacionexistente) {
		this.domicilio=domicilio;
		this.contratacionexistente=contratacionexistente;
		this.nuevacontratacion=nuevaContratacion;
	}

	public Domicilio getDomicilio() {
		return domicilio;
	}

	public Contratacion getNuevacontratacion() {
		return nuevacontratacion;
	}

	public Contratacion getContratacionexistente() {
		return contratacionexistente;
	}
	
	
}