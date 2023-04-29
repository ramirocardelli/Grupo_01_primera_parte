package negocio;

public class AbonadoYaCargadoSinFactura extends Exception {
	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private String dni;
	
	public AbonadoYaCargadoSinFactura(String nombre,String dni) {
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
