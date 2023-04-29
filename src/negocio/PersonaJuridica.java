package negocio;

/** Clase que representa un abonado que es de tipo persona juridica. No puede aceptar clonacion.
 */
public class PersonaJuridica extends Abonado {
	
    /** Constructor de 2 parametros String que crea un abonado de tipo persona juridica.
     * @param nombre : nombre de la persona juridica
     * @param dni : numero de documento de la persona jurdica. <br>
     * <b> Pre: </b> nombre y dni no pueden ser null ni " ". 
     * <b> Pre: </b> Se crea una persona juridica.
     */
    public PersonaJuridica(String nombre, String dni) {
		super(nombre, dni);
	}

    /** Metodo que invoca a la factura de la persona juridica para calcular el monto total que se debe abonar, ya que hay un descuento dependiendo el tipo de persona que es abonado.
     * @param factura : factura de la persona juridica.
     * @return double que representa el valor a abonar. <br>
     * <b> Pre: </b> factura no puede ser null. <br>
     * <b> Post: </b> El valor de retorno no puede ser negativo.
     */
    @Override
    public double calcularTotal(Factura factura) {
    	return factura.getPrecioPersonaJuridica();
    }

    /** Implementacion del metodo clone() para clonar una persona juridica.
     * @throws CloneNotSupportedException siempre, la persona juridica no admite clonacion.
     */
    public Object clon() throws CloneNotSupportedException{
        throw new CloneNotSupportedException();
    }
}
