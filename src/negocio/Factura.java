package negocio;

import java.util.ArrayList;
import java.util.Iterator;

/** Clase que representa una factura en el sistema de contrataciones. 
 */
public class Factura implements Cloneable, IFactura {
    private Abonado abonado;
    private ArrayList<Contratacion> contrataciones = new ArrayList<Contratacion>();

    /** Constructor de un parametro abonado que crea un nueva factura.
     * @param abonado : abonado que contrata un servicio. <br>
     * <b> Pre: </b> abonado no debe ser null. <br>
     * <b> Post: </b> Se crea una nueva factura para un abunado determinado.  
     */
    public Factura(Abonado abonado) {
        super();
        this.abonado = abonado;
    }
    
    /** Metodo que agrega una contratacion a la lista de todas las contrataciones.
     * @param contratacion : Contratacion que se quiere agregar a la lista de contrataciones.<br>
     * <b> Pre: </b> La contratacion no puede ser nula. <br>
     * <b> Post: </b> Se a�ade el elemento contratacion a la lista.
     */
    public void agregarContratacion(Contratacion contratacion) {
    	contrataciones.add(contratacion);
    }

    /** Metodo que calcula el monto total (entre todas sus contrataciones) que debe pagar un abonado sin aplicar el descuento por el metodo de pago.
     * @return double que contiene el valor que se debe abonar.
     */
    public double calcularTotalSinDescuento() {
    	return abonado.calcularTotal(this);
    }
   
    /** Metodo que calcula el monto total (entre todas sus contrataciones) que debe pagar un abonado aplicando el descuento por el metodo de pago.
     * @return double que contiene el valor que se debe abonar.
     */ 
    public double calcularTotalConDescuento() {
    	return abonado.calcularTotal(this);
    }


    /** Calcula el monto total de contrataciones que debe abonar una persona fisica.
     * @return double que contiene el valor del monto total a abonar.
     */
    public double getPrecioPersonaFisica(){
    	double res = 0;
    	Iterator<Contratacion>it = contrataciones.iterator();
    	while(it.hasNext()) {
    		res += it.next().getPrecio();
    	}
		return res;
    }
    
    /** Calcula el monto total de contrataciones que debe abonar una persona juridica. Aplica descudento si la persona contrato mas de 3 servicios.
     * @return double que contiene el valor del monto total a abonar.
     */
    public double getPrecioPersonaJuridica(){
    	double res = 0;
    	int i = 0;
    	Iterator<Contratacion>it = contrataciones.iterator();
    	while(it.hasNext()) {
    		if(i >=3)
    			res += it.next().getPrecio()*0.5;
    		else
    			res += it.next().getPrecio();
    		i++;
    	}
		return res;
    }

    /** Metodo que clona una factura, cuando sea posible.
     * @return factura clonada.
     * @throws CloneNotSupportedException cuando la factura que se quiere clonar tiene como abonado a una persona juridica.
     */
    public Object clone() throws CloneNotSupportedException{
    	Factura clon= (Factura)super.clone();
    	clon.abonado=(Abonado)this.abonado.clon();
    	clon.contrataciones=(ArrayList<Contratacion>)this.contrataciones.clone();
    	clon.contrataciones.clear();
    	Iterator<Contratacion> it=this.contrataciones.iterator();
    	while(it.hasNext()) {
    		clon.contrataciones.add((Contratacion)it.next().clone());
    	}
		return clon;
    }

	public Abonado getAbonado() {
		return abonado;
	}
	
	public void eliminarContratacion(Domicilio domicilio) throws DomicilioSinContratacionEnAbonadoException {
		int i=buscaContratacion(domicilio);
    	if(i>-1) {
    		this.contrataciones.remove(i);
    	}
    	else
    		throw new DomicilioSinContratacionEnAbonadoException(domicilio,this.abonado);
	}

	@Override
	public boolean sinContratacion() {
		return (this.contrataciones.size()==0);
	}

	private int buscaContratacion(Domicilio domicilio) {
		int i=0;
		int tamanio=contrataciones.size();
    	while(i<tamanio && !this.contrataciones.get(i).getDomicilio().equals(domicilio)) {
    		i++;
    	}
    	if(i>=tamanio) {
    		i=-1;
    	}
    	return i;
    		
	}
	
	@Override
	public Contratacion getContratacion(Domicilio domicilio) {
		int i=buscaContratacion(domicilio);
		Contratacion rta=null;
		if(i>-1) {
			rta=this.contrataciones.get(i);
    	}
    	return rta;
	}

	@Override
	public String toString() {
		return "Factura de " + abonado + ", cuenta con las siguientes contrataciones: \n" + contrataciones + " \n ";
	}
	
	
	
	
}

