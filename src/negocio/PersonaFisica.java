package negocio;
//puede aceptar clonacion
public class PersonaFisica extends Abonado {
	
    public PersonaFisica() {
        super();
    }
    
    public double calcularTotal(Factura factura){
    	return factura.calcularTotalPersonaFisica();
    }
    
    // abonado.calcularPrecio()
    // system.calcularPrecio(abonado)
    // busca en facturas la del abonado
    // Factura y abonado
    // abonado.calcularPrecio(factura) (es la tuya)
    // polimorfismo para calcular el precio
    // Decorator
}
