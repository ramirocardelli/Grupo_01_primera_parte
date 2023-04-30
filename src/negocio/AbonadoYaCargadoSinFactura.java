package negocio;

/** Clase que representa la excepcion que se lanza cuando el abonado correspondiente ya esta cargado en la lista (no tiene una factura preexistente).
 */
public class AbonadoYaCargadoSinFactura extends Exception {
	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private String dni;
	
    /** Constructor de la excepcion con dos parametros String.
     * @param nombre : nombre del abonado ya cargado en la lista.
     * @param dni : dni del abonado ya cargado en la lista. <br>
     * <b> Pre: </b> nombre y dni no pueden ser null o " ".
     */
    public AbonadoYaCargadoSinFactura(String nombre,String dni) {
            this.nombre=nombre;
            this.dni=dni;
    }

    public String getNombre() {
            return nombre;
    }

    public String getDni() {
            return dni;
    }
}
