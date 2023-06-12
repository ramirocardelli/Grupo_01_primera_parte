package negocio;

public class PagoException extends Exception {
	
	/**
	* Constructor con 1 parametro (mensaje) para crear una nueva excepción.
	* @param dni. <br>
	* <b> Pre: </b> mensaje distinto de null<br>
	* <b> Post: </b> Se crea una nueva excepcion.
	*/
	public PagoException(String mensaje) {
		super(mensaje);
	}
}
