package negocio;

/** Interface que establece el comportamiento de una factura.
 */
public interface IFactura {	
    double calcularTotalSinDescuento();
    double calcularTotalConDescuento();
    void agregarContratacion(Contratacion contratacion);
    void eliminarContratacion(Domicilio domicilio) throws DomicilioSinContratacionException;
    boolean sinContratacion();
    Abonado getAbonado();
    Contratacion getContratacion(Domicilio domicilio);
}
