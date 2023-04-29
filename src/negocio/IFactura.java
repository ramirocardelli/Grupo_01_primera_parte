package negocio;

/** Interface que establece el comportamiento de una factura.
 */
public interface IFactura {	
    double calcularTotalSinDescuento();
    double calcularTotalConDescuento();
    void agregarContratacion(Contratacion contratacion);
}
