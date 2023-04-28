package negocio;

public class DecoratorPago implements IFactura{
    private IFactura encapsulado;
    
    public DecoratorPago() {
        super();
    }
}
