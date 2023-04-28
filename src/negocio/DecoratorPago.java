package negocio;

public abstract class DecoratorPago implements IFactura{
    protected IFactura encapsulado;
    public DecoratorPago(IFactura encapsulado) {
        super();
        this.encapsulado = encapsulado;        
    }
    
	public void agregarContratacion(Contratacion contratacion) {
		encapsulado.agregarContratacion(contratacion);
	}
}
