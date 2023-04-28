package negocio;

public interface IFactura {	
    double calcularTotalSinDescuento();
    double calcularTotalConDescuento();
    void agregarContratacion(Contratacion contratacion);
}
