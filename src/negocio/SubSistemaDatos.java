package negocio;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Clase que funciona como interfaz entre Sistema y los Datos
 * 
 * Invariantes de clase: <br>
 *  datosFacturas diferente de null <br>
 *  abonadosSinContratacion diferente de null <br>
 *
 *
 */
public class SubSistemaDatos {
	private HashMap<String, Abonado>abonados=new HashMap<String, Abonado>();
	
	
	public SubSistemaDatos() {
	}	
	
	public void nuevaContratacion(String dni, Contratacion nuevaContratacion) throws DomicilioYaConContratacionExcepcion, DniDesconocidoException {
		Abonado buscaAbonado=this.abonados.get(dni);
    	if(buscaAbonado!=null){
    		Contratacion buscaContratacion=this.buscaContratacion(nuevaContratacion.domicilio);
    		if(buscaContratacion==null) { 
    			buscaAbonado.addContratacion(buscaContratacion);
    		}
    		else 
    			throw new DomicilioYaConContratacionExcepcion(nuevaContratacion,buscaContratacion);
    	}
    	else
    		throw new DniDesconocidoException(dni);
	}
	
	public void eliminarContratacion(String dni,Domicilio domicilio) throws DomicilioSinContratacionEnAbonadoException, DniDesconocidoException {
		Abonado abonado= abonados.get(dni);
		if(abonado!=null) {
			abonado.eliminaContratacion(domicilio);
		}
		else {
			throw new DniDesconocidoException(dni);
		}
	}
	
	
	public Abonado buscaAbonado(String dni)  {
		return abonados.get(dni);
	}
	
	public Contratacion buscaContratacion(Domicilio domicilio)  { //Ingresando un domicilio busca la contratacion
		Iterator<Abonado>it=abonados.values().iterator();
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
	
	public IFactura buscaFactura(String dni,GregorianCalendar mesYanio) throws DniDesconocidoException { //Ingresando un domicilio busca la contratacion
		Abonado buscaAbonado= abonados.get(dni);
		IFactura rta=null;
		if(buscaAbonado!=null) {
			rta=buscaAbonado.getFactura(mesYanio);	
		}
		else {
			throw new DniDesconocidoException(dni);
		}
		return rta;
	}
	
	public void agregaAbonado(Abonado abonado) throws AbonadoYaCargadoException {
		if(!this.abonados.containsKey(abonado.getDni())){ //verifica que abonado no este cargado en la lista de abonados sin contratacion	
			abonados.put(abonado.getDni(), abonado);
		}
		else
			throw new AbonadoYaCargadoException(abonado.getNombre(), abonado.getDni());
	}
	
	
	public void eliminaAbonado(String dni) throws DniDesconocidoException, AbonadoConFacturaException {
		Abonado eliminar=this.abonados.get(dni);
		if(eliminar!=null) {
			if(eliminar.getFactura(null)==null) {
				this.abonados.remove(dni);
			}
			else
				throw new AbonadoConFacturaException(dni, dni);
		}
			throw new DniDesconocidoException(dni);
	}
	
	public Iterator<Abonado> iteratorAbonados(){
		return this.abonados.values().iterator();
	}

	
	public void findeMes(GregorianCalendar fecha) {
		Iterator<Abonado> itAbonados= this.abonados.values().iterator();
		ArrayList<Contratacion> listaContrataciones=new ArrayList<Contratacion>();
		while(itAbonados.hasNext()) { //clona las contrataciones para que estan permanezcan constantes en el historico (no son referencias)
			Abonado abonado=itAbonados.next();
			listaContrataciones.clear();
			Iterator<Contratacion> itContrataciones= abonado.getContrataciones();
			while(itContrataciones.hasNext()) {
				listaContrataciones.add(itContrataciones.next());
			}
			abonado.findeMes(new Factura(fecha, listaContrataciones));
		}
	}
	
	public void pagaFactura(String dni,String metodoPago) throws DniDesconocidoException, MetodoDePagoInvalidoException,noHayFacturaAPagarException {
		Abonado abonado=this.abonados.get(dni);
		if(abonado!=null) {
			IFactura aPagar=abonado.getFactura(null);
			if(aPagar!=null) {
				FactoryDecoradoFactura Fa= new FactoryDecoradoFactura();
				IFactura pagada=Fa.creaFactura(aPagar, metodoPago); //obtiene la factura a pagar
				abonado.pagaFactura(pagada);
			}
			else
				throw new noHayFacturaAPagarException(dni);
		}
		else
			throw new DniDesconocidoException(dni);
	}
	
	public void muestraEstado() {
		Iterator<Abonado> it=this.abonados.values().iterator();
		System.out.println("El sistema cuenta con las siguientes facturas:\n");
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}
}