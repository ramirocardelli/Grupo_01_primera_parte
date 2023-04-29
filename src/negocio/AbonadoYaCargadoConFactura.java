package negocio;

public class AbonadoYaCargadoConFactura extends Exception {
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String dni;
	
	public AbonadoYaCargadoConFactura(String nombre,String dni) {
		this.nombre=dni;
		this.dni=nombre;
    }

	public String getNombre() {
		return nombre;
	}

	public String getDni() {
		return dni;
	}
}
