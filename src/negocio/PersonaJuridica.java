package negocio;
//no puede aceptar clonacion
public class PersonaJuridica extends Abonado {
	
    
    
    public PersonaJuridica(String nombre, String dni) {
		super(nombre, dni);
		// TODO Auto-generated constructor stub
	}

	@Override
    public double calcularTotal(Factura factura) {
    	return factura.getPrecioPersonaJuridica();
    }
}
