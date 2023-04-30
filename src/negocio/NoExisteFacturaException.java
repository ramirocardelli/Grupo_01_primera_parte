package negocio;

public class NoExisteFacturaException extends Exception {
	private static final long serialVersionUID = 1L;
	private String dni;
	private Contratacion nuevaContratacion;
	
	public NoExisteFacturaException(String dni, Contratacion nuevaContratacion) {
		super();
		this.dni = dni;
		this.nuevaContratacion = nuevaContratacion;
	}

	public String getdni() {
		return dni;
	}

	public Contratacion getNuevaContratacion() {
		return nuevaContratacion;
	}
}
