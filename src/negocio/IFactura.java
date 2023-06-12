package negocio;

import java.io.Serializable;
import java.util.GregorianCalendar;

/** Interface que establece el comportamiento de una factura.
 */
public interface IFactura extends Serializable {	
    double valorSinDesc();
    double valorConDesc();
    GregorianCalendar getMesYAnio();
    boolean sinContratacion();
    Object clone() throws CloneNotSupportedException;
    String toString();
}
