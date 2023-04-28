package negocio;

public class FactoryPagos {
    public FactoryPagos() {
        super();
    }
    
    public static IFactura factoryFactura(Abonado abonado, String metodoPago) {
    	IFactory creado = null;
    	if(metodoPago.equalsIgnoreCase("EFECTIVO"))
    }
}
