package negocio;

import java.io.Serializable;
import java.util.ArrayList;

/** Esta clase representa un abonado dentro de un sistema de contrataci�n de un servicios de seguridad.
 * Contiene informacion sobre nombre, dni y una lista con el historico de sus facturas.
 */
public abstract class Abonado implements Cloneable, Serializable,Runnable{  
    private String nombre;
    private String dni;
    private ArrayList<IFactura> historicoFacturas = new ArrayList<IFactura>();


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

    /** Calculo del monto total entre todos las contrataciones correspondientes a un abonado.
     * @param factura : Factura para la cual se quiere calcular el monto total.
     * @return El resultado de la suma del precio de cada contratacion. <br>
     * <b> Pre: </b> Factura no puede ser null. <br>
     * <b> Post: </b> El resultado no puede ser menor a 0.
     */
    public abstract double calcularTotal(Factura factura);

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    
    @Override
	public String toString() {
		return "Abonado " + nombre + " con DNI: " + dni ;
	}

	/** Metodo para clonar un abonado.
     * @return : Se devuelve un clon del abonado correspondiente.
     * @throws CloneNotSupportedException : Se lanza una excepcion cuando el abonado es de tipo persona jur�dica, la cual no puede aceptar clonacion.
     */
    public Object clon() throws CloneNotSupportedException{
    	Abonado clon= (Abonado)super.clone();
    	return clon;
    }
    
    public void solicitarTecnico() {
    	Thread t1=new Thread(this);
    	t1.start();	
    }

	@Override
	public void run() {
		Sistema.getInstance().getTecnicos().solicitarTecnico(this);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
    
    
}