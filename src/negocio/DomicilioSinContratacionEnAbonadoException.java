package negocio;

/** Clase que representa una excepcion que se lanza cuando no se encuentra ninguna contratacion de una abonado determinado para el domicilio ingresado.
 */
public class DomicilioSinContratacionEnAbonadoException extends DomicilioSinContratacionException{
	private static final long serialVersionUID = 1L;
	private Abonado abonado;
	
	/**
    * Constructor con 2 parametros (domicilio y abonado) para crear una nueva excepción.
    * @param domicilio.
    * @param abonado. <br>
    * <b> Pre: </b> domicilio y abonado distinto de null.<br>
    * <b> Post: </b> Se crea una nueva excepcion.
    */
	public DomicilioSinContratacionEnAbonadoException(Domicilio domicilio, Abonado abonado) {
		super(domicilio);
		this.abonado = abonado;
	}

	public Abonado getAbonado() {
		return abonado;
	}
	
}
