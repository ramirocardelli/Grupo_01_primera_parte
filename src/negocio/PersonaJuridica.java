package negocio;
//no puede aceptar clonacion
public class PersonaJuridica extends Abonado {
    public PersonaJuridica() {
        super();
    }
    
    
    public double getPrecio(Factura factura){
        return factura.getPrecioPJuridica();
    }
}
