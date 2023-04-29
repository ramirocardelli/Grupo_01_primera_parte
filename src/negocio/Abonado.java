package negocio;

/** Esta clase representa un abonado dentro de un sistema de contratación de un servicios de seguridad.
 * Contiene información sobre su nombre y su dni.
 */
public abstract class Abonado implements Cloneable{  //hacer como socio
    private String nombre;
    private String dni;


    /** Constructor de 2 parametros String para crear un nuevo abonado.
     * @param nombre : Nombre del abonado.
     * @param dni : Numero de documento de identidad del abonado.
     * <b> Pre: </b> nombre y dni no pueden ser nulos ni " ". <br>
     *  <b> Post: </b> Se crea un nuevo abonado con nombre y dni.
     */
    public Abonado(String nombre, String dni) {
		super();
		this.nombre = nombre;
		this.dni = dni;
	}

    /** Calculo del monto total entre todos las contrataciones correspondientes a un abonado.
     * @param factura : Factura para la cual se quiere calcular el monto total.
     * @return El resultado de la suma del precio de cada contratacion. <br>
     * <b> Pre: </b> Factura no puede ser null. <br>
     * <b> Post: </b> El resultado no puede ser menor a 0.
     */
    public abstract double calcularTotal(Factura factura);

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    /** Metodo para clonar un abonado.
     * @return : Se devuelve un clon del abonado correspondiente.
     * @throws CloneNotSupportedException : Se lanza una excepcion cuando el abonado es de tipo persona jurídica, la cual no puede aceptar clonacion.
     */
    public Object clon() throws CloneNotSupportedException{
    	Abonado clon= (Abonado)super.clone();
    return clon;
    }
}