package negocio;

import java.util.GregorianCalendar;

/** Clase abstracta que representa el comportamiento de una factura segun su metodo de pago.
 */
public abstract class DecoratorPago implements IFactura,Cloneable{
    protected IFactura encapsulado;

    /** Constructor de un parametro IFactura que crea un nuevo objeto DecoratorPago con una IFactura como encapsulado.
     * @param encapsulado : factura que se quiere decorar. <br>
     * <b> Pre: </b> encapsulado no puede ser null.
     * 
     */
    public DecoratorPago(IFactura encapsulado) {
        super();
        this.encapsulado = encapsulado;
    }

    /** @return valor del pago total sin descuento
     */
    public double valorSinDesc() {
    	return this.encapsulado.valorSinDesc();
    }

    /** @return valor del pago total con descuento
     */
    public abstract double valorConDesc() ;
    

    /** @return si el encapsulado tiene contrataciones
     */
    @Override
	public boolean sinContratacion() {
		return this.encapsulado.sinContratacion();
	}
  
	@Override
	public abstract String toString();
	
	public Object clone() throws CloneNotSupportedException {
		DecoratorPago clon=(DecoratorPago)super.clone();
		clon.encapsulado=(IFactura)this.encapsulado.clone();
		return clon;
	}
	
	public GregorianCalendar getMesYAnio() {
		return encapsulado.getMesYAnio();
	}
}
