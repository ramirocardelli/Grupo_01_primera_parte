package negocio;

public abstract class Abonado implements Cloneable{  //hacer como socio
    private String nombre;
    private String dni;
    

    public Abonado(String nombre, String dni) {
		super();
		this.nombre = nombre;
		this.dni = dni;
	}

	public abstract double calcularTotal(Factura factura);

	public String getNombre() {
		return nombre;
	}

	public String getDni() {
		return dni;
	}
    
	public Object clon() throws CloneNotSupportedException{
    	Abonado clon= (Abonado)super.clone();
		return clon;
    }
}