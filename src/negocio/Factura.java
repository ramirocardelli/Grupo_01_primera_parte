package negocio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

/** Clase que representa una factura en el sistema de contrataciones. 
 */
public class Factura implements Cloneable, IFactura {
    private ArrayList<Contratacion> contrataciones = new ArrayList<Contratacion>();
    private GregorianCalendar mesYanio;
    private boolean personaJ=false; //F=Fisica-V=Juridica

    /** Constructor de un parametro abonado que crea un nueva factura.
     * <b> Pre: </b> abonado no debe ser null. <br>
     * <b> Post: </b> Se crea una nueva factura para un abunado determinado.  
     */
    public Factura(GregorianCalendar mesYanio,ArrayList<Contratacion> contrataciones) {
        super();
        this.mesYanio=mesYanio;
        this.contrataciones=contrataciones;
    }

    protected void setPersonaJ(boolean personaJ) {
		this.personaJ = personaJ;
	}

	/** Calcula el monto total de contrataciones que debe abonar una persona juridica. Aplica descudento si la persona contrato mas de 3 servicios.
     * @return double que contiene el valor del monto total a abonar.
     */
    public double getPrecio(){
    	double res = 0;
    	int i = 0;
    	Iterator<Contratacion>it = contrataciones.iterator();
    	while(it.hasNext()) {
    		if(personaJ && i >=3)
    			res += it.next().getPrecio()*0.5;
    		else
    			res += it.next().getPrecio();
    		i++;
    	}
		return res;
    }

   
    public GregorianCalendar getMesYAnio() {
		return mesYanio;
	}

	/** Metodo que determina si la lista de contrataciones esta vacia
     * @return boolean true si la lista no tiene contrataciones, false si se encuentran contrataciones en la lista.
     */
    @Override
	public boolean sinContratacion() {
		return (this.contrataciones.size()==0);
    }

	public double valorConDesc() {
		return getPrecio();
	}

	public double valorSinDesc() {
		return getPrecio();
	}


	/** Metodo que clona una factura, cuando sea posible.
     * @return factura clonada.
     * @throws CloneNotSupportedException cuando la factura que se quiere clonar tiene como abonado a una persona juridica.
     */
    public Object clone() throws CloneNotSupportedException{
    	Factura clon= (Factura)super.clone();
    	clon.mesYanio=(GregorianCalendar) this.mesYanio.clone();
    	clon.contrataciones=(ArrayList<Contratacion>)this.contrataciones.clone();
    	clon.contrataciones.clear();
    	Iterator<Contratacion> it=this.contrataciones.iterator();
    	while(it.hasNext()) {
    		clon.contrataciones.add((Contratacion)it.next().clone());
    	}
		return clon;
    }
	
	
	
	
	 

	
    @Override
	public String toString() {
		return "Factura del "+ mesYanio.get(Calendar.MONTH)+"/"+ mesYanio.get(Calendar.YEAR)+"\n\t\t Contrataciones: \n" + contrataciones + " \n";
	}
    
}

