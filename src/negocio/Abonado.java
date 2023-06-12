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

    /**
     * Agrega una contratación a la lista de contrataciones.
     * En caso de que no pueda agregar la contratacion, lanza una excepción.
     * @param contratacion: Contratacion a agregar
     * @throws PagoException
     * <b> Pre: </b> la contratacion no debe ser nula <br>
     * <b> Post: </b> Se agrega la contratacion a la lista <br>
     */
    public abstract void contratarServicio(Contratacion contratacion) throws PagoException;

    /**
     * Da de baja un servicio, eliminando la contratación de la lista de contrataciones. 
     * En caso de que no pueda eliminar la contratación, lanza excepción de pago o de domicilio erróneo
     * @param domicilio: Domicilio correspondiente a la contratación
     * @throws PagoException, DomicilioSinContratacionEnAbonadoException
     * <b> Pre: </b> El domicilio no debe ser nulo <br>
     * <b> Post: </b> Se elimina la contratacion de la lista <br>
     */
    public abstract void bajaServicio(Domicilio domicilio) throws PagoException, DomicilioSinContratacionEnAbonadoException;
    
    /**
     * Paga la primera factura pendiente, se pasa a la lista de facturas históricas.
     * En caso de que se pueda pagar la factura, lanza excepción de pago.
     * En caso de que sea persona física, delega al estado.
     * @param factura: IFactura ya decorada con el método de pago correspondiente.
     * @throws PagoException
     * <b> Pre: </b> La factura no debe ser nula <br>
     * <b> Post: </b> Se elimina la primera factura de la lista <br>
     */
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
    
    /**
     * Elimina la contratación correspondiente.
     * En caso de que el domicilio no tenga una contratación correspondiente a dicho abonado, lanza una excepción.
     * @param domicilio: Domicilio que corresponde a la contratación a eliminar.
     * @throws DomicilioSinContratacionEnAbonadoException
     * <b> Pre: </b> El domicilio no debe ser nulo <br>
     * <b> Post: </b> Elimina la contratación <br>
     * */
    public void eliminaContratacion(Domicilio domicilio) throws DomicilioSinContratacionEnAbonadoException {
    	if(this.contrataciones.remove(domicilio)==null) {
    		throw new DomicilioSinContratacionEnAbonadoException(domicilio, this);
    	}
    }
    
    /**Busca la factura que corresponda a la fecha. 
     * Si la fecha es null, devuelve la primera factura
     * @param mesYanio: Fecha a la que corresponde la factura
     * @return IFactura: Factura que corresponde a la fecha
     * <b> Pre: </b> <br>
     * <b> Post: </b> Retorna una IFactura <br>
     * */
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
    
    /** Dada una fecha, busca la factura correspondiente y la clona.
     * Si la fecha es null, clona la primera factura
     * @throws CloneNotSupportedException
     * <b> Pre: </b> <br>
     * <b> Post: </b> Retorna un clon de IFactura <br>
     * */
    public Object clonFactura(GregorianCalendar mesYanio) throws CloneNotSupportedException {
    	return this.historicoFacturas.get(mesYanio).clone();
    }
    
    /** Dada una factura, la agrega a las facturas pendientes
     * <b> Pre: </b> La factura no debe ser nula <br>
     * <b> Post: </b> La factura se agregó a la lista de las facturas pendientes <br>
     * */
	public void findeMes(Factura factura) {
		this.facturaPendiente.add(factura);
	}
    
    /** Dada una factura, la agrega a las facturas pendientes
     * <b> Pre: </b> La factura no debe ser nula <br>
     * <b> Post: </b> La factura se agregó a la lista de las facturas pendientes <br>
     * */	
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