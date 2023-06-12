package negocio;

public class DomicilioYaConContratacionExcepcion extends Exception{
	private static final long serialVersionUID = 1L;
	private Domicilio domicilio;
	private Contratacion nuevacontratacion,contratacionexistente;
	
	/**
	* Constructor con 2 parametros (nuevaContratacion y contratacionexistente) para crear una nueva excepción.
	* @param nuevaContratacion
	* @param contratacionexistente <br>
	* <b> Pre: </b> nuevaContratacion y contratacionexistente distintos de null.<br>
	* <b> Post: </b> Se crea una nueva excepcion.
	*/
	public DomicilioYaConContratacionExcepcion(Contratacion nuevaContratacion, Contratacion contratacionexistente) {
		
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