package negocio;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/** Esta clase representa un abonado dentro de un sistema de contrataci�n de un servicios de seguridad.
 * Contiene informaci�n sobre su nombre y su dni.
 */
public abstract class Abonado implements Cloneable{  
    private String nombre;
    private String dni;
    HashMap<Domicilio, Contratacion>contrataciones= new HashMap<Domicilio, Contratacion>();
    HashMap<GregorianCalendar, IFactura>historicoFacturas= new HashMap<GregorianCalendar, IFactura>();
    LinkedList<Factura> facturaPendiente=new LinkedList<Factura>();

    /** Constructor de 2 parametros String para crear un nuevo abonado.
     * @param nombre : Nombre del abonado.
     * @param dni : Numero de documento de identidad del abonado. <br>
     * <b> Pre: </b> nombre y dni no pueden ser nulos ni " ". <br>
     * <b> Post: </b> Se crea un nuevo abonado con nombre y dni.
     */
    public Abonado(String nombre, String dni) {
		super();
		this.nombre = nombre;
		this.dni = dni;
	}

    public abstract void contratarServicio(Contratacion contratacion) throws PagoException;
    public abstract void bajaServicio(Domicilio domicilio) throws PagoException, DomicilioSinContratacionEnAbonadoException;
    public abstract void pagaFactura(IFactura factura) throws PagoException;

    
    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }
    
    public Contratacion getContratacion(Domicilio domicilio) {
    	return contrataciones.get(domicilio);
    }
    
    public void addContratacion(Contratacion contratacion) {
    	contrataciones.put(contratacion.getDomicilio(), contratacion);
    }
    
    public void eliminaContratacion(Domicilio domicilio) throws DomicilioSinContratacionEnAbonadoException {
    	if(this.contrataciones.remove(domicilio)==null) {
    		throw new DomicilioSinContratacionEnAbonadoException(domicilio, this);
    	}
    }
    
    public IFactura getFactura(GregorianCalendar mesYanio) {
    	IFactura rta=null;
    	if(mesYanio==null) {//obtiene la sig factura a pagar
    		rta=this.facturaPendiente.getFirst();
    	}
    	else
    		rta=this.historicoFacturas.get(mesYanio);
		return rta;
    }
    
    /** Metodo para clonar un abonado.
     * @return : Se devuelve un clon del abonado correspondiente.
     * @throws CloneNotSupportedException : Se lanza una excepcion cuando el abonado es de tipo persona jur�dica, la cual no puede aceptar clonacion.
     */
    public Object clon() throws CloneNotSupportedException{
    	Contratacion poneC;
    	IFactura poneF;
    	Abonado clon= (Abonado)super.clone();
    	
    	clon.facturaPendiente=(LinkedList<Factura>)this.facturaPendiente.clone();
    	clon.facturaPendiente.clear();
    	Iterator<Factura> itPendientes= facturaPendiente.iterator();
    	while(itPendientes.hasNext()) {
    		clon.facturaPendiente.add(itPendientes.next());
    	}
    	
    	clon.contrataciones=(HashMap<Domicilio, Contratacion>)this.contrataciones.clone();
    	clon.contrataciones.clear();
    	Iterator<Contratacion> it=this.contrataciones.values().iterator();
    	while(it.hasNext()) {
    		poneC=(Contratacion)it.next().clone();
    		clon.contrataciones.put(poneC.getDomicilio(), poneC);
    	}
    	
    	clon.historicoFacturas=(HashMap<GregorianCalendar, IFactura>)this.historicoFacturas.clone();
    	clon.historicoFacturas.clear();
    	Iterator<IFactura> itF= this.historicoFacturas.values().iterator();
    	while(itF.hasNext()) {
    		poneF=(IFactura)itF.next();
    		clon.historicoFacturas.put(poneF.getMesYAnio(),poneF);
    	}
    	
		return clon;
    }
    
    public Object clonFactura(GregorianCalendar mesYanio) throws CloneNotSupportedException {
    	IFactura clon=(IFactura)this.historicoFacturas.get(mesYanio).clone();
		return clon;
    }
    
	public void findeMes(Factura factura) {
		this.facturaPendiente.add(factura);
	}
	
	public void abonarFactura(IFactura factura){
		this.historicoFacturas.put(factura.getMesYAnio(),factura);
		this.facturaPendiente.removeFirst();
	}
	
	public Iterator<Contratacion> getContrataciones(){
		return contrataciones.values().iterator();
	}
	
	
	
	 @Override
		public String toString() {
			return "Abonado " + nombre + " con DNI: " + dni ;
		}

	
	
}