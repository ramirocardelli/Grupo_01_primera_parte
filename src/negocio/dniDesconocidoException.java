package negocio;

public class dniDesconocidoException extends Exception{
	private static final long serialVersionUID = 1L;
	private String dni;
	
	public dniDesconocidoException(String dni) {
		super();
		this.dni = dni;
	}

	public String getDni() {
		return dni;
	}
	
	

}
