package negocio;

/** Clase que representa un abonado que es de tipo persona fisica. Puede aceptar clonacion.
 */
public class PersonaFisica extends Abonado {

    /** Constructor de 2 parametros String que crea un abonado de tipo persona fisica.
     * @param nombre : nombre de la persona fisica
     * @param dni : numero de documento de la persona fisica. <br>
     * <b> Pre: </b> nombre y dni no pueden ser null ni " ". 
     * <b> Pre: </b> Se crea una persona fisica.
     */
    public PersonaFisica(String nombre, String dni) {
		super(nombre, dni);
		// TODO Auto-generated constructor stub
	}

    /** Metodo que invoca a la factura de la persona fisica para calcular el monto total que se debe abonar ya que hay un descuento dependiendo del tipo de persona que es el abonado.
     * @param factura : factura de la persona fisica.
     * @return double que representa el valor a abonar. <br>
     * <b> Pre: </b> factura no puede ser null. <br>
     * <b> Post: </b> El valor de retorno no puede ser negativo.
     */
    @Override
    public double calcularTotal(Factura factura) {
    	return factura.getPrecioPersonaFisica();
    }
    
    //No es necesario sobreescribir el clon() xq no cambia del ya creado en la clase padre (como se extiende ya lo tiene)
	
    // abonado.calcularPrecio()
    // system.calcularPrecio(abonado)
    // busca en facturas la del abonado
    // Factura y abonado
    // abonado.calcularPrecio(factura) (es la tuya)
    // polimorfismo para calcular el precio
    // Decorator
}
