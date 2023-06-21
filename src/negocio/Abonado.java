package negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/** Esta clase representa un abonado dentro de un sistema de contrataci�n de un servicios de seguridad.
 * Contiene informaci�n sobre su nombre y su dni.
 */
public abstract class Abonado extends Observable implements Cloneable,Runnable,Serializable{  
    protected String nombre;
    protected String dni;
    protected HashMap<Domicilio, Contratacion>contrataciones= new HashMap<Domicilio, Contratacion>();
    protected HashMap<GregorianCalendar, IFactura>historicoFacturas= new HashMap<GregorianCalendar, IFactura>();
    protected LinkedList<Factura> facturaPendiente=new LinkedList<Factura>();

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
    public abstract IFactura pagaFactura(IFactura factura) throws PagoException;
    public abstract void findeMes(Factura factura);
		

    
    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }
    
    public Contratacion getContratacion(Domicilio domicilio) {
    	return contrataciones.get(domicilio);
    }
    
    public IFactura getFactura(GregorianCalendar mesYanio) {
    	IFactura rta=null;
    	if(mesYanio==null) {//obtiene la sig factura a pagar
    		try {
    			rta=this.facturaPendiente.getFirst();
    		}
    		catch (NoSuchElementException e) { //se lanza esta excepcion si la linked list se encuentra vacia
    			rta=null;
    		}
    	}
    	else
    		rta=this.historicoFacturas.get(mesYanio);
		return rta;
    }
    
    /** Metodo para clonar un abonado.
     * @return : Se devuelve un clon del abonado correspondiente.
     * @throws CloneNotSupportedException : Se lanza una excepcion cuando el abonado es de tipo persona jur�dica, la cual no puede aceptar clonacion.
     */
    public Object clone() throws CloneNotSupportedException{
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
    
    public ArrayList<Contratacion> copiaContrataciones() {
    	ArrayList<Contratacion> contrataciones=new ArrayList<Contratacion>();
    	Iterator<Contratacion> it=this.contrataciones.values().iterator();
    	while(it.hasNext()) {
    		try {
				contrataciones.add((Contratacion)it.next().clone());
			} catch (CloneNotSupportedException e) {
				//las contrataciones son siempre clonables
			}
    	}
		return contrataciones;
    }

	public Iterator<Contratacion> getContrataciones(){
		return contrataciones.values().iterator();
	}
	
	
	/**
	 * Funcion que permite que un abonado solicite un tecnico al azar de forma concurrente.
	 */
	public void solicitarTecnico() {
    	Thread t1=new Thread(this);
    	t1.start();	
    }

	@Override
	public void run() {
		String texto;
		Random rand=new Random(new Date().getTime());
		try {
			Thread.sleep(200+rand.nextInt(300));//tiempo de concurrencia
		} catch (InterruptedException e) {
		} 
		texto="El abonado: "+this.nombre+ "solicita la atencion de un tecnico";
		Sistema.getInstance().muestraThread(texto);
		String tecnico=Sistema.getInstance().getTecnicos().solicitarTecnico(this.nombre);
		try {
			Thread.sleep(2000+rand.nextInt(2000)); // el tiempo de atencion es de unos seg(entre 2 y 4)
		} catch (InterruptedException e) {
		}
		texto="El tecnico"+tecnico+" termina de atender abonado "+this.nombre;
		Sistema.getInstance().muestraThread(texto);
		Sistema.getInstance().getTecnicos().liberarTecnico(tecnico);
		
	}

	
	@Override
	public abstract String toString();

	protected String historico() {
		return "El historico de facturas para el abonado: " + this.nombre +" es: "+this.historicoFacturas.toString();
	}
	
	
	
	
}