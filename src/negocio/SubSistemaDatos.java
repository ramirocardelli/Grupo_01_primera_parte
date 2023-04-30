package negocio;

import java.util.Iterator;

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
		Iterator<Abonado>it=abonadosSinContratacion.getAbonados();
		Abonado busqueda=null;
		boolean encuentra=false;
		while(it.hasNext() && !encuentra){
			busqueda=it.next();
			if(dni.equals(busqueda.getDni())) {
				encuentra=true;
			}
		}
		if(!encuentra)
			busqueda=null;
		return busqueda;
	}
	
	public Contratacion buscaContratacion(Domicilio domicilio) { //Ingresando un domicilio busca la contratacion
		Iterator<IFactura>it=this.datosFacturas.getFacturas();
		Contratacion rta = null;
		boolean encuentra=false;
		while(it.hasNext() && !encuentra){
			rta=it.next().getContratacion(domicilio);
			if(rta!=null)
				encuentra=true;
		}
		if(!encuentra)
			rta=null;
		return rta;
	}
	
	public IFactura buscaFactura(String dni) {
		Iterator<IFactura>it=this.datosFacturas.getFacturas();
		IFactura rta = null;
		boolean encuentra=false;
		while(it.hasNext() && !encuentra){
			rta=it.next();
			String rtadni=rta.getAbonado().getDni();
			if(rtadni.compareTo(dni)==0) {
				encuentra=true;
			}
		}
		if(!encuentra)
			rta=null;
		return rta;
	}
	
	public void agregaFactura(IFactura factura) {
		this.datosFacturas.agregaFactura(factura);
	}
	
	public void agregaAbonadoSinFacctura(Abonado abonado) {
		this.abonadosSinContratacion.agregaAbonado(abonado);
	}
	
	public void eliminaFactura(String dni) throws DniDesconocidoException {
		IFactura elimina=buscaFactura(dni);
		if(elimina!=null) {
			agregaAbonadoSinFacctura(elimina.getAbonado());
			this.datosFacturas.eliminaFacturas(elimina);
			
		}
		else {
			throw new DniDesconocidoException(dni);
		}
		
	}
	
	public void eliminaAbonadoSinFactura(String dni) throws DniDesconocidoException, AbonadoYaCargadoException{ 
		Abonado elimina=buscaAbonado(dni);
		if(elimina!=null)
			this.abonadosSinContratacion.eliminaAbonado(elimina);
		else {
			Iterator<IFactura>it=this.datosFacturas.getFacturas();
			IFactura busca=null;
			boolean encuentra=false;
			while(it.hasNext() && !encuentra) {
				busca=it.next();
				if(busca.getAbonado().getDni().equals(dni))
					encuentra=true;
			}
			if(!encuentra)
				throw new DniDesconocidoException(dni);
			else
				throw new AbonadoYaCargadoException(dni, busca.getAbonado().getNombre(), true);
		}
	}
	public Iterator<IFactura> iteratorFacturas() {
		return datosFacturas.getFacturas();
		
		
		//Recorre los 2 arreglos mostrando toda la informacion contenida en ellos (tostring)
	}
	
	
}