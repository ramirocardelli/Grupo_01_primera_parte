package negocio;

public class SubSistemaDatos {
	AbonadosSinContratacion abonadosSinContratacion;
	DatosFacturas datosFacturas;
	
	
	public SubSistemaDatos() {
		datosFacturas=new DatosFacturas();
		abonadosSinContratacion=new AbonadosSinContratacion();
	}
	
	public int existeAbonado();
	
	private int existeFacturaAbonado();
	
	private int existeAbonadoSinContratacion();
}
