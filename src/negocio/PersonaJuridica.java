package negocio;
//no puede aceptar clonacion
public class PersonaJuridica extends Abonado {
	
    public PersonaJuridica(String nombre, String dni) {
		super(nombre, dni);
	}

	@Override
    public double calcularTotal(Factura factura) {
    	return factura.getPrecioPersonaJuridica();
	}
	
	public Object clon() throws CloneNotSupportedException{
    	throw new CloneNotSupportedException();
    }
}
