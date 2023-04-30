package negocio;

/** Clase que representa la excepcion que se lanza cuando el abonado correspondiente ya esta cargado en la lista y tiene una factura preexistente.
 */
public class AbonadoYaCargadoConFactura extends Exception {
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String dni;

    /** Constructor de la excepcion con dos parametros String.
     * @param nombre : nombre del abonado con factura preexistente.
     * @param dni : dni del abonado con factura preexistente. <br>
     * <b> Pre: </b> nombre y dni no pueden ser null o " ".
     */
    public AbonadoYaCargadoConFactura(String nombre,String dni) {
            this.nombre=dni;
            this.dni=nombre;
    }

    public String getNombre() {
            return nombre;
    }

    public String getDni() {
            return dni;
    }
}
