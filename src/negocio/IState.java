package negocio;

import java.io.Serializable;

public interface IState extends Serializable{

	void contratarServicio(Contratacion contratacion) throws PagoException;
	void bajaServicio(Domicilio domicilio) throws PagoException, DomicilioSinContratacionEnAbonadoException;
	IFactura pagaFactura(IFactura factura) throws PagoException;
	void findeMes(Factura factura);
}
