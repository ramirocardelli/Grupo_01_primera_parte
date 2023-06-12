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


    /** Implementacion del metodo clone() para clonar una persona juridica.
     * @throws CloneNotSupportedException siempre, la persona juridica no admite clonacion.
     */
    public Object clon() throws CloneNotSupportedException{
        throw new CloneNotSupportedException("Metodo clon no permitido para un tipo de persona juridica");
    }

	@Override
	public void findeMes(Factura factura) {
		factura.setPersonaJ(true);
		this.facturaPendiente.add(factura); //no cambia de estado y no pasa a moroso (no posee estados)
	}
}
