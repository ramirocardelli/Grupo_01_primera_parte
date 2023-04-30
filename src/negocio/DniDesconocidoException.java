package negocio;

public class DniDesconocidoException extends Exception{
	private static final long serialVersionUID = 1L;
	private String dni;
	
	public DniDesconocidoException(String dni) {
		super();
		this.dni = dni;
	}

	public String getDni() {
		return dni;
	}
	
	

}
