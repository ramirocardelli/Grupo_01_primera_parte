package negocio;


import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Observable;


/** Clase que representa el sistema de gestion de facturas y abonados.
=======
/** Clase que representa el sistema de gesti�n de facturas y abonados.
>>>>>>> Stashed changes
 */
public class Sistema extends Observable implements Serializable { 
    private SubSistemaDatos datos;
    private static Sistema instancia=null;
    private GregorianCalendar fechaAct;
    private SubSistemaTecnicos tecnicos;
    

    /** Constructor privado para evitar que se creen muchas instancias del Sistema
     */
    private Sistema() { 
        super();
        this.datos=new SubSistemaDatos();
        GregorianCalendar aux= new GregorianCalendar(); //se obtiene la hr act del sistema
        fechaAct.set(aux.get(Calendar.YEAR), aux.get(Calendar.MONTH), 0);
        this.tecnicos=new SubSistemaTecnicos();
    }
    
    /** Constructor estatico para evitar que se cree mas de una instancia del Sistema (patron Singleton)
    /** Constructor estatico para evitar que se cree m�s de una instancia del Sistema (patron Singleton)
     * @return instancia de Sistema
     */
    public static Sistema getInstance() {
    	if(Sistema.instancia==null) {
    		instancia= new Sistema();
    	}
		return instancia;
    }

    public void findeMes() {
    	datos.findeMes(this.fechaAct);
    	fechaAct.add(Calendar.MONTH, 1);
    }
    
    public void PagarFactura(String dni,String metodoPago) throws DniDesconocidoException, MetodoDePagoInvalidoException, noHayFacturaAPagarException, PagoException {
    	datos.pagaFactura(dni,metodoPago);
    	//deberia notificarle a la vista quien y el precio de la factura (se puede hacer q el metodo devuelva esos datos y se hace un notify obeserver)
    }
    
    
    /** Metodo para agregar una nueva contratacion al sistema.
     * @param dni : del abonado que hace la contratacion.
     * @param camaras : cantidad de camaras que se contratan.
     * @param botonesAntipanicos : cantidad de botones antipanico que se contratan.
     * @param movilAcompanamiento : true si se contrata el movil de acompanamiento, false en otro caso.
     * @param domicilio : para el cual se hara la contratacion.
     * @param tipo : tipo de contratacion.
     * @throws DomicilioYaConContratacionExcepcion si el domicilio ya cuenta con una contratacion.
     * @throws NoExisteFacturaException si no existe la factura para agregar la contratacion
     * @throws TipoIncorrectoServicioException cuando se ingresa un tipo incorrecto de servicio.
     * @throws DniDesconocidoException 
     * @throws PagoException 
     */
    public void nuevaContratacion(String dni,int camaras, int botonesAntipanicos, boolean movilAcompanamiento, Domicilio domicilio, String tipo) throws DomicilioYaConContratacionExcepcion, TipoIncorrectoServicioException, DniDesconocidoException, PagoException { //falta lanzar la excepcion de domicilio ya con contratacion, del factory y la de no encontrar abonado con factura
    	FactoryContratacion FC=new FactoryContratacion();
    	Contratacion nuevaContratacion=FC.creaContratacion(camaras, botonesAntipanicos, movilAcompanamiento, domicilio, tipo);
    	this.datos.nuevaContratacion(dni,nuevaContratacion);
    }
    
    /** Metodo para eliminar una contratacion de un abonado.
     * @param dni dni del abonado que se desea eliminar la contratacion.
     * @param domicilio de la contratacion que se desea eliminar.
     * @throws DomicilioSinContratacionEnAbonadoException cuando el domicilio ingresado no corresponde a ninguna contratacion.
     * @throws DniDesconocidoException cuando el dni ingresado no corresponde a ningun abonado. <br>
     * <b> Pre: </b> dni no puede ser " " ni null y domicilio no puede ser null.
     * @throws PagoException 
     */
    public void eliminaContratacionAbonado(String dni,Domicilio domicilio) throws DniDesconocidoException, DomicilioSinContratacionEnAbonadoException, PagoException {
    	this.datos.eliminarContratacion(dni, domicilio);
    }
    
    /** Metodo para buscar un abonado en la base de datos dado un dni. 
     * @param dni : dni del abonado a buscar
     * @return Abonado requerido
     * @throws DniDesconocidoException cuando el dni ingresado no corresponde a ningun abonado. <br>
     * <b> Pre: </b> dni no puede ser null ni " ".
     */
    public Abonado buscaAbonado(String dni) throws DniDesconocidoException {
    	Abonado rta= this.datos.buscaAbonado(dni);
    	if(rta==null) {
    		throw new DniDesconocidoException(dni);
    	}
    	return rta;
    }
    
    /** Metodo para buscar una contratacion en la lista de contrataciones en base a un domicilio dado.
     * @param domicilio : domicilio de la contratacion a buscar.
     * @return Contratacion del domicilio correspondiente.
     * @throws DomicilioSinContratacionException cuando el domicilio ingresado no corresponde a ninguna contratacion. <br>
     * <b> Pre: </b> domicilio no puede ser null.
     */
    public Contratacion buscaContratacion(Domicilio domicilio) throws DomicilioSinContratacionException {
    	Contratacion rta=this.datos.buscaContratacion(domicilio);
    	if(rta==null)
    		throw new DomicilioSinContratacionException(domicilio);
    	return rta;
    }
    
    /** Metodo para buscar una factura de un abonado segun su dni en el listado de facturas de la base de datos.
     * @param dni del abonado que se quiere obtener la factura.
     * @return IFactura la factura del abonado del dni correspondiente.
     * @throws DniDesconocidoException cuando el dni ingresado no corresponde a ningun abonado con alguna factura.<br>
     * <b> Pre: </b> dni no puede ser null ni " ".
     */
    public IFactura buscaFactura(String dni, GregorianCalendar mesYanio) throws DniDesconocidoException {
    	IFactura rta=this.datos.buscaFactura(dni, mesYanio);
    	if(rta==null) {
    		throw new DniDesconocidoException(dni);
    	}
    	return rta;
    }
    
    public double ValorFactura(String dni, GregorianCalendar mesYanio) {
		return 0;
    }
    
    /** Metodo que realiza, cuando sea posible, la clonaci�n de una factura.
     * @param dni del abonado del que se quiere clonar la factura.
     * @return factura clonada.
     * @throws CloneNotSupportedException cuando no se pueda clonar la factura (cuando el abonado sea de tipo persona juridica.
     * @throws DniDesconocidoException cuando el dni ingresado no corresponde a ningun abonado. <br>
     * <b> Pre: </b> dni no puede ser null ni " ".
     */
    public IFactura clonacionFactura(String dni,GregorianCalendar mesYanio) throws CloneNotSupportedException,DniDesconocidoException {
    	IFactura rta=null;
    	Abonado abonado=datos.buscaAbonado(dni);
    	if(abonado!=null) {
    		rta=(IFactura) abonado.clonFactura(mesYanio);
    	}
    	else
    		throw new DniDesconocidoException(dni);
		return rta;
	}
	 
    /** Metodo que aplica una promocion (dorada o platino).
     * @param domicilio : de la contratacion a la que se quiere aplicar la promocion.
     * @param promocion : tipo de promocion que se quiere aplicar.
     * @throws DomicilioSinContratacionException cuando el domicilio ingresado no corresponde a ninguna contratacion.
     */
    public void aplicaPromocion(Domicilio domicilio, Promo promocion) throws DomicilioSinContratacionException {
        Contratacion contratacion=datos.buscaContratacion(domicilio);
        if(contratacion!=null)
        	contratacion.promo(promocion);
        else
        	throw new DomicilioSinContratacionException(domicilio);  
    }
    
    /** Metodo para agregar un nuevo abonado al sistema.
     * @param nombre : nombre del abonado
     * @param dni : dni del abonado
     * @param tipo : tipo del abonado 
     * @throws TipoIncorrectoPersonaException si el tipo de persona ingresado no es valido. <br>
     * <b> Pre: </b> nombre, dni y tipo no pueden ser null.
     * @throws  
     */
    public void nuevoAbonado(String nombre,String dni,String tipo) throws AbonadoYaCargadoException, TipoIncorrectoPersonaException{ //FALTA LANZAR LA EXCEPCION DEL FACTORY
    	FactoryAbonado FA=new FactoryAbonado();
    	Abonado abonado=FA.creaAbonado(nombre, dni, tipo);
    	datos.agregaAbonado(abonado);
    }

    public void eliminaAbonado(String dni) throws DniDesconocidoException,AbonadoConFacturaException {
    	this.datos.eliminaAbonado(dni);
    }
    
    public void actualizaContratacion(String dni, Domicilio domicilio,int camaras, int botonesAntipanicos, int movilAcompanamiento) throws DomicilioSinContratacionEnAbonadoException, DniDesconocidoException {
    	Abonado buscaAbonado= datos.buscaAbonado(dni);
    	if(buscaAbonado!=null) {
    		Contratacion contratacion=buscaAbonado.getContratacion(domicilio);
    		if(contratacion!=null) {
    			contratacion.actualizaContratacion(camaras, botonesAntipanicos, movilAcompanamiento);
    		}
    		else {
    			throw new DomicilioSinContratacionEnAbonadoException(domicilio,buscaAbonado);
    		}
    	}
    	else {
    		throw new DniDesconocidoException(dni);
    	}
    }
    
    
	/**
	 * Muestra el contenido de todas las facturas almacenadas junto con el detalle de cada una de <br>
	 * las contrataciones que realizo el Abonado  
	 **/
	public void MuestraEstado() {
		datos.muestraEstado();
	}

	
	public void enviarMensaje(String mensaje) {
		setChanged();
		notifyObservers(mensaje);
	}
	
	public SubSistemaDatos getDatos() {
		return datos;
	}

	public void setDatos(SubSistemaDatos datos) {
		this.datos = datos;
	}


	public void setTecnicos(SubSistemaTecnicos tecnicos) {
		this.tecnicos = tecnicos;
	}

	public SubSistemaTecnicos getTecnicos() {
		return tecnicos;
	}

	public void historico(String dNI) { //MUESTRA ESTADO
		// TODO Auto-generated method stub
		
	}

	public void solicitarTecnico(String dNI) {
		this.datos.buscaAbonado(dNI).solicitarTecnico();
	}

	public void altaTecnico(String nombreTecnico) {
		this.tecnicos.agregarTecnico(new Tecnico(nombreTecnico));
		
	}
	
}                                               

