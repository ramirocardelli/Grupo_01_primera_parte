package negocio;

import modelo.AbonadosSinContratacion;
import modelo.DatosFacturas;

public class SubSistemaDatos {
	private AbonadosSinContratacion abonadosSinContratacion;
	private DatosFacturas datosFacturas;
	
	
	public SubSistemaDatos() {
		datosFacturas=new DatosFacturas();
		abonadosSinContratacion=new AbonadosSinContratacion();
	}
	
	public Abonado buscaAbonado(String dni) {
		return null;
	}
	
	public Contratacion buscaContratacion(Domicilio domicilio) { //Ingresando un domicilio busca la contratacion
		return null;
	}
	
	public Factura buscaFactura(Abonado abonado) {
		return null;
	}
	
	public void agregaFactura(Factura factura) {
		this.datosFacturas.agregaFactura(factura);
		
	}
	
	public void agregaAbonadoSinFacctura(Abonado abonado) {
		this.abonadosSinContratacion.agregaAbonado(abonado);
	}
	
	public void eliminaFactura(int i) {
		this.datosFacturas.eliminaFacturas(i);
	}
	
	public void eliminaAbonadoSinFactura(int i){
		this.abonadosSinContratacion.eliminaAbonado(i);
	}
	
}