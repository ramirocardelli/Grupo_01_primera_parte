package negocio;

/** Clase que representa una excepcion que se lanza cuando la factura que se ingreso no existe en la base de datos.
 */
public class FacturaInexistenteException extends Exception{
    
    public FacturaInexistenteException(Abonado abonado, Contratacion contratacion) {
    }
}
