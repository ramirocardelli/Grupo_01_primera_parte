package negocio;

/** Clase que representa una excepcion que se lanza cuando no se encuentra ninguna contratacion de una abonado determinado para el domicilio ingresado.
 */
public class DomicilioSinContratacionEnAbonadoException extends DomicilioSinContratacionException{
	private static final long serialVersionUID = 1L;
	private Abonado abonado;
	
	public DomicilioSinContratacionEnAbonadoException(Domicilio domicilio, Abonado abonado) {
		super(domicilio);
		this.abonado = abonado;
	}

	public Abonado getAbonado() {
		return abonado;
	}
	
}
