package negocio;

import java.io.Serializable;

public class AbonadoConFacturaException extends Throwable implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String dni;
	
	public AbonadoConFacturaException(String nombre,String dni) {
		this.nombre=nombre;
		this.dni=dni;
    }

	public String getNombre() {
		return nombre;
	}

	public String getDni() {
		return dni;
	}
}