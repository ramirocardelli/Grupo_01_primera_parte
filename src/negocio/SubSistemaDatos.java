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
	
	public void nuevaContratacion(String dni, Contratacion nuevaContratacion) throws DomicilioYaConContratacionExcepcion, DniDesconocidoException, PagoException {
		Abonado abonado=this.abonados.get(dni);
    	if(abonado!=null){
    		Contratacion buscaContratacion=this.buscaContratacion(nuevaContratacion.domicilio);
    		if(buscaContratacion==null) { 
    			abonado.contratarServicio(buscaContratacion);
    		}
    		else 
    			throw new DomicilioYaConContratacionExcepcion(nuevaContratacion,buscaContratacion);
    	}
    	else
    		throw new DniDesconocidoException(dni);
	}
	
	public void eliminarContratacion(String dni,Domicilio domicilio) throws DomicilioSinContratacionEnAbonadoException, DniDesconocidoException, PagoException {
		Abonado abonado= abonados.get(dni);
		if(abonado!=null) {
			abonado.bajaServicio(domicilio);
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
		Abonado abonado;
		while(itAbonados.hasNext()) { //clona las contrataciones para que estan permanezcan constantes en el historico (no son referencias)
				abonado=itAbonados.next();
				abonado.findeMes(new Factura(fecha,abonado.copiaContrataciones()));
		}
	}
	
	public IFactura pagaFactura(String dni,String metodoPago) throws DniDesconocidoException, MetodoDePagoInvalidoException,noHayFacturaAPagarException, PagoException {
		Abonado abonado=this.abonados.get(dni);
		IFactura factura=null;
		if(abonado!=null) {
			IFactura aPagar=abonado.getFactura(null);
			if(aPagar!=null) {
				FactoryDecoradoFactura Fa= new FactoryDecoradoFactura();
				factura=Fa.creaFactura(aPagar, metodoPago); //obtiene la factura a pagar
				abonado.pagaFactura(factura);
			}
			else
				throw new noHayFacturaAPagarException(dni);
		}
		else
			throw new DniDesconocidoException(dni);
		return factura; //devuelve la factura a pagar
	}
	
	public String muestraEstadoSistema() {
		String rta = "Abonados del Sistema: \n";
		Iterator<Abonado> it=this.abonados.values().iterator();
		while (it.hasNext()) {
			rta+= "  * "+it.next().toString();
		}
		return rta;
	}
	public String historico(String dni) throws DniDesconocidoException {
		Abonado abonado=this.abonados.get(dni);
		String rta=null;
		if(abonado!=null) {
			rta=abonado.historico();
		}
		else
			throw new DniDesconocidoException(dni);
		return rta;
	}
}