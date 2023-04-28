package negocio;
//puede aceptar clonacion
public class PersonaFisica extends Abonado {
	
    public PersonaFisica(String nombre, String dni) {
		super(nombre, dni);
		// TODO Auto-generated constructor stub
	}

	@Override
    public double calcularTotal(Factura factura) {
    	return factura.getPrecioPersonaFisica();
    }
    
    // abonado.calcularPrecio()
    // system.calcularPrecio(abonado)
    // busca en facturas la del abonado
    // Factura y abonado
    // abonado.calcularPrecio(factura) (es la tuya)
    // polimorfismo para calcular el precio
    // Decorator
}
