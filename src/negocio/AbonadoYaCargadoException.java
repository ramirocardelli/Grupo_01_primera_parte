package negocio;

public class AbonadoYaCargadoException extends Exception {
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String dni;
	private boolean factura;
	
	public AbonadoYaCargadoException(String nombre,String dni, boolean factura) {
		this.nombre=nombre;
		this.dni=dni;
		this.factura=factura;
    }

	public String getNombre() {
		return nombre;
	}

	public String getDni() {
		return dni;
	}

	public boolean isFactura() {
		return factura;
	}
}
