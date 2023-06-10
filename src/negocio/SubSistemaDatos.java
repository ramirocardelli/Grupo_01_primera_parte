package negocio;

import java.io.Serializable;
import java.util.Iterator;

import modelo.AbonadosSinContratacion;
import modelo.DatosFacturas;
import modelo.Tecnicos;

/**
 * Clase que funciona como interfaz entre Sistema y los Datos
 * 
 * Invariantes de clase: <br>
 *  datosFacturas diferente de null <br>
 *  abonadosSinContratacion diferente de null <br>
 *
 *
 */
public class SubSistemaDatos implements Serializable {
	private AbonadosSinContratacion abonadosSinContratacion;
	private DatosFacturas datosFacturas;
	private Tecnicos tecnicos;
	
	/**
	 * Constructor de la clase instancia los ArrayList de la clase
	 */
	public SubSistemaDatos() {
		datosFacturas=new DatosFacturas();
		abonadosSinContratacion=new AbonadosSinContratacion();
		tecnicos=new Tecnicos();
	}
	
	
	/**
	 * Metodo que busca un Abonado asociado a un DNI que NO tenga una contratacion asociada a el
	 * @param dni DNI del Abonado buscado
	 * @return Abonado asociado al DNI 
	 */
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
	
	
	/**
	 * Metodo que busca una factura asociada a un Domicilio y la devuelve
	 * @param domicilio : Domicilio del que se desea obtener la contratacion asociada
	 * @return Contratacion con la contratacion pedida
	 * <b>Post</b> La contratacion si es encontrada se devuelve la referencia a esa Contratacion , si no se encuentra la contratacion se devuelve null
	 */
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
	/**
	 * Metodo que Busca una factura asociada al DNI de un Abonado
	 * @param dni : DNI del Abonado
	 * @return IFactura con la factura pedida
	 *  <b>Post</b> Se devuelve una Factura si se encuentra un abonado asociado a ella o null si no se encuentra dicha Factura
	 */
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
	/**
	 * Metodo que agrega una factura a la lista de facturas
	 * @param factura : Factura que se desea añadir a la lista
	 * <b>Pre</b> factura debe ser diferente de null.
	 */
	public void agregaFactura(IFactura factura) {
		this.datosFacturas.agregaFactura(factura);
	}
	/**
	 * Elimina de la lista de Abonados sin contratacion al abonado dado.
	 * @param abonado : Instancia de Abonado
	 * <b>Pre</b> Abonado debe ser diferente de null.
	 */
	public void agregaAbonadoSinFactura(Abonado abonado) {
		this.abonadosSinContratacion.agregaAbonado(abonado);
	}
	/**
	 * Metodo que elimina una Factura asociado a un Abonado mediante un DNI provisto.
	 * @param dni : DNI del abonado
	 * @throws DniDesconocidoException Se lanza esta excepcion si no hay un Abonado asociado a este DNI
	 */
	public void eliminaFactura(String dni) throws DniDesconocidoException {
		IFactura elimina=buscaFactura(dni);
		if(elimina!=null) {
			agregaAbonadoSinFactura(elimina.getAbonado());
			this.datosFacturas.eliminaFacturas(elimina);
			
		}
		else {
			throw new DniDesconocidoException(dni);
		}
		
	}
	/**
	 * Metodo que elimina un Abonado de la lista de AbonadosSinContratacion 
	 * @param dni : DNI del Abonado
	 * @throws DniDesconocidoException Excepcion lanzada si no se pudo encontrar un Abonado que este asociado a el DNI provisto
	 * @throws AbonadoYaCargadoException Excepcion lanzada si el Abonado que se desea eliminar ya fue cargado en la lista de Facturas
	 */
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
	
	
	
	/**
	 * Metodo que es un simple pasamanos. Pide un iterator a la capa de Datos y lo reenvia por el return
	 * @return Iterator de todas las Facturas
	 */
	public Iterator<IFactura> iteratorFacturas() {
		return datosFacturas.getFacturas();
	}
	
	
}