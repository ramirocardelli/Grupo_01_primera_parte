package negocio;

public abstract class DecoratorPago implements IFactura{
    protected IFactura encapsulado;
    
    public DecoratorPago() {
        super();
    }
    
	public void agregarContratacion(Contratacion contratacion) {
		encapsulado.agregarContratacion(contratacion);
	}
}
