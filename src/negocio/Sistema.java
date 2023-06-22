package negocio;


import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Observable;

import persistencia.DAO;


/** Clase que representa el sistema de gestion de facturas y abonados.
 */
public class Sistema extends Observable{ 
    private SubSistemaDatos datos;
    private static Sistema instancia=null;
    private GregorianCalendar fechaAct;
    private SubSistemaTecnicos tecnicos;
    private transient DAO dao=new DAO("nombredelarchpersist.bin");
    

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
    	assert dni != null: "DNI nulo";
    	assert dni != "": "DNI vacio";
    	assert metodoPago != null: "Metodo de pago nulo";
    	assert metodoPago != "": "Metodo de pago vacio";
    	Estado estado=null;
    	try {
			IFactura Fpagada=datos.pagaFactura(dni,metodoPago);
			Abonado abonado=datos.buscaAbonado(dni);
			String mensaje="El abonado "+abonado.nombre+" paga correctamente su factura del "+ Fpagada.getMesYAnio().get(Calendar.MONTH)+"/"+ Fpagada.getMesYAnio().get(Calendar.YEAR)+" por un importe de: $"+ Fpagada.valorConDesc();
			estado= new Estado(mensaje,"SISTEMA");
			setChanged();
	    	notifyObservers(estado);
		} catch (DniDesconocidoException e) {
			estado= new Estado("Ningun cliente registrado posee como dni "+e.getDni(),"EXCEPCION");
			setChanged();
	    	notifyObservers(estado);
		} catch (PagoException e) {
			estado=new Estado(e.getMessage(),"EXCEPCION");
			setChanged();
	    	notifyObservers(estado);
		} catch (MetodoDePagoInvalidoException e) {
			estado= new Estado("El metodo de pago ingresado no coincide con los disponibles","EXCEPCION");
			setChanged();
	    	notifyObservers(estado);
		} catch (noHayFacturaAPagarException e) {
			estado= new Estado("El abonado con dni: "+ e.getDni()+ " no dispone de facturas pendientes de pago","EXCEPCION");
			setChanged();
	    	notifyObservers(estado);
		}	
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
    	assert dni != null: "DNI nulo";
    	assert dni != "": "DNI vacio";
    	assert camaras < 0: "Camaras negativas";
    	assert botonesAntipanicos < 0: "Botones antipanicos negativos";
    	assert tipo != null: "tipo nulo";
    	assert tipo != "": "tipo vacio";
    	assert domicilio != null: "Domicilio nulo";
    	Estado estado=null;
		try {
			//System.out.println("entro");
			FactoryContratacion FC=new FactoryContratacion();
			Contratacion nuevaContratacion = FC.creaContratacion(camaras, botonesAntipanicos, movilAcompanamiento, domicilio, tipo);
			this.datos.nuevaContratacion(dni,nuevaContratacion);
			Abonado abonado=datos.buscaAbonado(dni);
			estado=new Estado("El abonado "+ abonado.getNombre()+ " contrata un nuevo sistema de "+tipo+ "para su domicilio: "+ domicilio.toString(),"SISTEMA");
			setChanged();
	    	notifyObservers(estado);
		} catch (DomicilioYaConContratacionExcepcion e) {
			estado=new Estado("El domicilio ya se encuentra con una contratacion","EXCEPCION");
			setChanged();
	    	notifyObservers(estado);
		} catch (DniDesconocidoException e) {
			estado= new Estado("Ningun cliente registrado posee como dni "+e.getDni(),"EXCEPCION");
			setChanged();
	    	notifyObservers(estado);
		}
		catch (PagoException e) {
			estado = new Estado(e.getMessage(),"EXCEPCION");
			setChanged();
	    	notifyObservers(estado);
		}
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
    	assert dni != null: "DNI nulo";
    	assert dni != "": "DNI vacio";
    	assert domicilio != null: "Domicilio nulo";
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
    	assert dni != null: "DNI nulo";
    	assert dni != "": "DNI vacio";
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
    	assert domicilio != null: "Domicilio nulo";
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
    	assert dni != null: "DNI nulo";
    	assert dni != "": "DNI vacio";
    	assert mesYanio != null: "Fecha nula";
    	
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
    	assert dni != null: "DNI nulo";
    	assert dni != "": "DNI vacio";
    	assert mesYanio != null: "Fecha nula";
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
    	assert domicilio != null: "domicilio nulo";
    	assert promocion != null: "promocion nula";
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
    public void nuevoAbonado(String nombre,String dni,String tipo) {
    	assert nombre != null: "nombre nulo";
    	assert nombre != "": "nombre vacio";
    	assert dni != null: "dni nulo";
    	assert dni != "": "dni vacio";
    	assert tipo != null: "tipo nulo";
    	assert tipo != "": "tipo vacio";
    	FactoryAbonado FA=new FactoryAbonado();
    	Abonado abonado=FA.creaAbonado(nombre, dni, tipo);	
    	try {
			datos.agregaAbonado(abonado);
		} catch (AbonadoYaCargadoException e) {
			Estado estado=new Estado("Abonado ya cargado","SISTEMA"); 
			setChanged();
			notifyObservers(estado);
		}
    	Estado estado2 = new Estado("Se creo el abonado correctamente","SISTEMA");
    	setChanged();
		notifyObservers(estado2);
    }
 
    
    public void eliminaAbonado(String dni) throws DniDesconocidoException,AbonadoConFacturaException {
    	assert dni != null: "dni nulo";
    	assert dni != "": "dni vacio";
    	this.datos.eliminaAbonado(dni);
    }
    
    public void actualizaContratacion(String dni, Domicilio domicilio,int camaras, int botonesAntipanicos, int movilAcompanamiento) throws DomicilioSinContratacionEnAbonadoException, DniDesconocidoException {
    	assert dni != null: "DNI nulo";
    	assert dni != "": "DNI vacio";
    	assert camaras < 0: "Camaras negativas";
    	assert botonesAntipanicos < 0: "Botones antipanicos negativos";
    	assert domicilio != null: "Domicilio nulo";
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
    	assert datos != null: "datos nulo";
		this.datos = datos;
	}


	public void setTecnicos(SubSistemaTecnicos tecnicos) {
    	assert tecnicos != null: "tecnicos nulos";
		this.tecnicos = tecnicos;
	}

	public SubSistemaTecnicos getTecnicos() {
		return tecnicos;
	}

	public void historico(String dni) { //MUESTRA ESTADO
    	assert dni != null: "dni nulo";
    	assert dni != "": "DNI vacio";
		Estado estado;
		try {
			String muestraHistorico=datos.historico(dni);
			estado=new Estado(muestraHistorico,"SISTEMA"); 
			
		} catch (DniDesconocidoException e) {
			estado= new Estado("Ningun cliente registrado posee como dni "+e.getDni(),"EXCEPCION");
		}
		setChanged();
		notifyObservers(estado);
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
    	assert dni != null: "dni nulo";
    	assert dni != "": "DNI vacio";
		Estado estado=null;
		Abonado aux=this.datos.buscaAbonado(dni);
		if (aux!=null) {
			aux.solicitarTecnico();
			estado=new Estado("Se solicito un tecnico","SISTEMA");
			setChanged();
			notifyObservers(estado);
		}
			
		else {
			estado= new Estado("Ningun cliente registrado posee como dni "+dni,"EXCEPCION");
			setChanged();
			notifyObservers(estado);
		}
		
	}
	
	/**
	  * Dado un dni nombre de un tecnico
	 *  Pre: nombreTecnico no puede ser null.
	 *  Post:se agrego un tecnico al listado dd tecnicos
	 * @param nombreTecnico
	 */
	public void altaTecnico(String nombreTecnico) {
    	assert nombreTecnico != null: "nombreTecnico nulo";
    	assert nombreTecnico != "": "nombreTecnico vacio";
    	AgregaTecnico aux=new AgregaTecnico(nombreTecnico,tecnicos);
		aux.start();
		Estado estado = new Estado ("Se dio de alta el tecnico correctamente","SISTEMA");
		setChanged();
		notifyObservers(estado);
	}
	
	public void muestraThread(String mnsg) {
		setChanged();
		notifyObservers(new Estado(mnsg,"THREAD"));
	}
	
	public void persistir() {
		try {
			this.dao.persistir();
			
		} catch (IOException e) {

			Estado estado=new Estado("Error al persistir","SISTEMA");
			setChanged();
			notifyObservers(estado);
		}
	}
	
	public void despersistir() {
		try {
			this.dao.despersistir();
		} catch (IOException e) {
			Estado estado=new Estado("Error al despersistir "+e.getLocalizedMessage()+ "","SISTEMA");
			setChanged();
			notifyObservers(estado);
		}
	}
}                                               

