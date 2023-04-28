package negocio;
//puede aceptar clonacion
public class PersonaFisica extends Abonado {
    public PersonaFisica() {
        super();
    }
    
    
    public double getPrecio(Factura factura){
        return factura.getPrecioPFisica();
    }
}
