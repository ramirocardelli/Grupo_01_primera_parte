package negocio;

public interface IState {

	void contratarServicio(Contratacion contratacion) throws PagoException;
	void bajaServicio(Domicilio domicilio) throws PagoException, DomicilioSinContratacionEnAbonadoException;
	void pagaFactura(IFactura factura) throws PagoException;
}
