package negocio;


import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Observable;

import persistencia.DAO;


/** Clase que representa el sistema de gestion de facturas y abonados.
=======
/** Clase que representa el sistema de gesti�n de facturas y abonados.
>>>>>>> Stashed changes
 */
public class Sistema extends Observable implements Serializable{ 
    private SubSistemaDatos datos;
    private static Sistema instancia=null;
    private GregorianCalendar fechaAct;
    private SubSistemaTecnicos tecnicos;
    private transient DAO dao=new DAO("nombredelarchpersist.txt");
    

    /** Constructor privado para evitar que se creen muchas instancias del Sistema
     */
    private Sistema() { 
        super();
        fechaAct= new GregorianCalendar(); 
        this.datos=new SubSistemaDatos();
        this.tecnicos=new SubSistemaTecnicos();
        GregorianCalendar aux= new GregorianCalendar(); //se obtiene la hr act del sistema
        fechaAct.set(aux.get(Calendar.YEAR), aux.get(Calendar.MONTH), 0);
    }
    
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
    	this.persistir(); //persiste una vez se cierra el mes
    }
    
    public void PagarFactura(String dni,String metodoPago) { //metodo invocado por ventana
    	Estado estado=null;
    	try {
			IFactura Fpagada=datos.pagaFactura(dni,metodoPago);
			Abonado abonado=datos.buscaAbonado(dni);
			String mensaje="El abonado "+abonado.nombre+" paga correctamente su factura del "+ Fpagada.getMesYAnio().get(Calendar.MONTH)+"/"+ Fpagada.getMesYAnio().get(Calendar.YEAR)+" por un importe de: $"+ Fpagada.valorConDesc();
			estado= new Estado(mensaje,"SISTEMA");
		} catch (DniDesconocidoException e) {
			estado= new Estado("Ningun cliente registrado posee como dni "+e.getDni(),"EXCEPCION");
		} catch (PagoException e) {
			estado=new Estado(e.getMessage(),"EXCEPCION");
		} catch (MetodoDePagoInvalidoException e) {
			estado= new Estado("El metodo de pago ingresado no coincide con los disponibles","EXCEPCION");
		} catch (noHayFacturaAPagarException e) {
			estado= new Estado("El abonado con dni: "+ e.getDni()+ " no dispone de facturas pendientes de pago","EXCEPCION");
		}
    	setChanged();
    	notifyObservers(estado);
    	
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
    public void nuevaContratacion(String dni,int camaras, int botonesAntipanicos, boolean movilAcompanamiento, Domicilio domicilio, String tipo) {
    	Estado estado=null;
		try {
			FactoryContratacion FC=new FactoryContratacion();
			Contratacion nuevaContratacion = FC.creaContratacion(camaras, botonesAntipanicos, movilAcompanamiento, domicilio, tipo);
			this.datos.nuevaContratacion(dni,nuevaContratacion);
			Abonado abonado=datos.buscaAbonado(dni);
			estado=new Estado("El abonado "+ abonado.getNombre()+ " contrata un nuevo sistema de "+tipo+ "para su domicilio: "+ domicilio.toString(),"SISTEMA");
		} catch (TipoIncorrectoServicioException e) {
			estado=new Estado("El tipo de servicio ingresado es invalido","EXCEPTION");
		} catch (DomicilioYaConContratacionExcepcion e) {
			estado=new Estado("El domicilio: "+ e.getDomicilio().toString()+ " ya se encuentra con una contratacion","EXCEPTION");
		} catch (DniDesconocidoException e) {
			estado= new Estado("Ningun cliente registrado posee como dni "+e.getDni(),"EXCEPCION");
		} catch (PagoException e) {
			
		}
		setChanged();
    	notifyObservers(estado);
    }
    
    /** Metodo para eliminar una contratacion de un abonado.
     * @param dni dni del abonado que se desea eliminar la contratacion.
     * @param domicilio de la contratacion que se desea eliminar.
     * @throws DomicilioSinContratacionEnAbonadoException cuando el domicilio ingresado no corresponde a ninguna contratacion.
     * @throws DniDesconocidoException cuando el dni ingresado no corresponde a ningun abonado. <br>
     * <b> Pre: </b> dni no puede ser " " ni null y domicilio no puede ser null.
     * @throws PagoException 
     */
    public void eliminaContratacionAbonado(String dni,Domicilio domicilio)  {
    	Estado estado=null;
    	try {
			this.datos.eliminarContratacion(dni, domicilio);
			Abonado abonado= datos.buscaAbonado(dni);
			estado= new Estado("El abonado "+ abonado.getNombre()+ "da de baja su contratacion en: "+domicilio.toString(),"SISTEMA" );
		} catch (DomicilioSinContratacionEnAbonadoException e) {
			estado=new Estado("El abonado con DNI: "+e.getAbonado().getDni()+" no posee ninguna contratacion en el domicilio:" +e.getDomicilio() , "EXCEPCION");
		} catch (DniDesconocidoException e) {
			estado= new Estado("Ningun cliente registrado posee como dni "+e.getDni(),"EXCEPCION");
		} catch (PagoException e) {
			estado=new Estado(e.getMessage(),"EXCEPTION");
		}
    	setChanged();
    	notifyObservers(estado);
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
	 * Funcion que hace de pasamanos para mandar mensajes a la Vista
	 * Post: Ventana notificada
	 * @param mensaje
	 */
	
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

	public void historico(String dni) { //MUESTRA ESTADO
		Estado estado;
		try {
			String muestraHistorico=datos.historico(dni);
			estado=new Estado(muestraHistorico,"SISTEMA"); 
		} catch (DniDesconocidoException e) {
			estado= new Estado("Ningun cliente registrado posee como dni "+e.getDni(),"EXCEPCION");
		}
	}

	public String muestraEstadoSist() {
		return datos.muestraEstadoSistema();
	}
	/**
	  * Dado un dni de un abonado se solicita un tecnico para ese Abonado
	 *  Pre:-
	 *  Post:-
	 * @param dNI
	 * @throws DniDesconocidoException: Si no existe un Abonado para ese DNI se lanza una excepcion
	 */
	public void solicitarTecnico(String dni) {
		Estado estado=null;
		Abonado aux=this.datos.buscaAbonado(dni);
		if (aux!=null)
			aux.solicitarTecnico();
		else
			estado= new Estado("Ningun cliente registrado posee como dni "+dni,"EXCEPCION");

	}

	public void altaTecnico(String nombreTecnico) {
		this.tecnicos.agregarTecnico(new Tecnico(nombreTecnico));
		
	}
	
	public void muestraThread(String mnsg) {
		setChanged();
		notifyObservers(new Estado(mnsg,"THREAD"));
	}
	
	public void persistir() {
		this.dao.persistir();
	}
	
	public void despersistir() {
		this.dao.despersistir();
	}
}                                               

