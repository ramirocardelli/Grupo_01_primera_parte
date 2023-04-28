package negocio;
//no puede aceptar clonacion
public class PersonaJuridica extends Abonado {
	
    public PersonaJuridica() {
        super();
    }
    
    public double calcularTotal(Factura factura){
    	return factura.calcularTotalPersonaJuridica();
    }
}
