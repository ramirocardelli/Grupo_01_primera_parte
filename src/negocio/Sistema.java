package negocio;

import java.io.Serializable;
import java.util.Iterator;


/** Clase que representa el sistema de gestion de facturas y abonados.
 */
public class Sistema implements Serializable { 
    private SubSistemaDatos datos;
    private static Sistema instancia=null;

    /** Constructor privado para evitar que se creen muchas instancias del Sistema
     */
    private Sistema() { 
        super();
        this.datos=new SubSistemaDatos();
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
    public IFactura buscaFactura(String dni) throws DniDesconocidoException {
    	IFactura rta=this.datos.buscaFactura(dni);
    	if(rta==null) {
    		throw new DniDesconocidoException(dni);
    	}
    	return this.datos.buscaFactura(dni);
    }

    /** Metodo para agregar un nuevo abonado al sistema.
     * @param nombre : nombre del abonado
     * @param dni : dni del abonado
     * @param tipo : tipo del abonado 
     * @throws AbonadoYaCargadoException si el abonado ya se encuentra cargado en el sistema
     * @throws TipoIncorrectoPersonaException si el tipo de persona ingresado no es valido. <br>
     * <b> Pre: </b> nombre, dni y tipo no pueden ser null.
     */
    public void nuevoAbonado(String nombre,String dni,String tipo) throws AbonadoYaCargadoException, TipoIncorrectoPersonaException{ //FALTA LANZAR LA EXCEPCION DEL FACTORY
    	if(datos.buscaAbonado(dni)==null) { //verifica que abonado no este cargado en la lista de abonados sin contratacion
    		FactoryAbonado FA=new FactoryAbonado();
    		Abonado abonado=FA.creaAbonado(nombre, dni, tipo);
    		IFactura busquedaF=datos.buscaFactura(dni);
    		if(busquedaF==null) { //si no encuentra el abonado en las facturas lo guarda en la lista de abonados sin servicios
    			datos.agregaAbonadoSinFactura(abonado);
    		}
    		else
    			throw new AbonadoYaCargadoException(nombre,dni,true);
    	}
    	else
    		throw new AbonadoYaCargadoException(nombre,dni,false);
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
     */
    public void nuevaContratacion(String dni,int camaras, int botonesAntipanicos, boolean movilAcompanamiento, Domicilio domicilio, String tipo) throws DomicilioYaConContratacionExcepcion, NoExisteFacturaException, TipoIncorrectoServicioException { //falta lanzar la excepcion de domicilio ya con contratacion, del factory y la de no encontrar abonado con factura
    	FactoryContratacion FC=new FactoryContratacion();
    	Contratacion nuevaContratacion=FC.creaContratacion(camaras, botonesAntipanicos, movilAcompanamiento, domicilio, tipo);
    	IFactura buscaFactura=datos.buscaFactura(dni);
    	//Se debe buscar si el abonado que se pasa tiene una factura existente y si el domicilio ingresado ya existe en cualquier otra factura
    	if(buscaFactura!=null){
    		Contratacion buscaContratacion=datos.buscaContratacion(domicilio);
    		if(buscaContratacion==null) { 
    			buscaFactura.agregarContratacion(nuevaContratacion);
    		}
    		else 
    			throw new DomicilioYaConContratacionExcepcion(domicilio,nuevaContratacion,buscaContratacion);
    	}
    	else
    		throw new NoExisteFacturaException(dni,nuevaContratacion);
    }

    /** Metodo para crear una factura para un determinado abonado en el sistema.
     * @param dni : del abonado
     * @param tipoPago : metodo de pago del abonado.
     * @param tipoFactura : tipo de factura 
     * @throws MetodoDePagoInvalidoException cuando el metodo de pago ingresado no es valido.
     * @throws TipoFacturaIncorrecto cuando el tipo de factura ingresado no es valido
     * @throws AbonadoYaCargadoException cuando el abonado ya tiene cargada una factura
     * @throws DniDesconocidoException cuando el dni no corresponde a ningun abonado. <br>
     * <b> Pre: </b> dni, tipoPago y tipoFacrura no pueden ser null ni " ".
     */
    public void nuevaFactura(String dni, String tipoPago,String tipoFactura) throws MetodoDePagoInvalidoException,TipoFacturaIncorrecto, AbonadoYaCargadoException, DniDesconocidoException{
    	Abonado buscaAbonado=datos.buscaAbonado(dni);
    	if(buscaAbonado!=null) {
    		FactoryFactura FF=new FactoryFactura();//crea la factura con factory y la inserta en la capa de datos
    		IFactura nuevaFactura= FF.creaFactura(buscaAbonado, tipoPago, tipoFactura);
    		datos.agregaFactura(nuevaFactura);
    		datos.eliminaAbonadoSinFactura(dni);
    	}
    	else {
    		IFactura buscafactura=datos.buscaFactura(dni);
    		if(buscafactura!=null) {
    			throw new AbonadoYaCargadoException(buscafactura.getAbonado().getNombre(),dni,true);
    		}
    		else
    			throw new DniDesconocidoException(dni);
    	}
    }

    /** Metodo para eliminar una factura del sistema.
     * @param dni : dni del abonado cuya factura se desea eliminar.
     * @throws DniDesconocidoException cuando el dni ingresado no corresponde a ningun abonado. <br>
     * <b> Pre: </b> dni no puede ser null ni " ".
     */
    public void eliminarFactura(String dni) throws DniDesconocidoException {
    	datos.eliminaFactura(dni);
    }

    /** Metodo para eliminar un abonado de la lista de abonados sin contratacion.
     * @param dni dni del abonado que se desea eliminar.
     * @throws DniDesconocidoException cuando el dni ingresado no corresponde a ningun abonado.
     * @throws AbonadoYaCargadoException cuando el abonado ya tiene cargada una factura
     */
    public void eliminaAbonadoSinContratacion(String dni)throws DniDesconocidoException, AbonadoYaCargadoException {
    	datos.eliminaAbonadoSinFactura(dni);
    }

    /** Metodo para eliminar una contratacion de un abonado.
     * @param dni dni del abonado que se desea eliminar la contratacion.
     * @param domicilio de la contratacion que se desea eliminar.
     * @throws DomicilioSinContratacionEnAbonadoException cuando el domicilio ingresado no corresponde a ninguna contratacion.
     * @throws DniDesconocidoException cuando el dni ingresado no corresponde a ningun abonado. <br>
     * <b> Pre: </b> dni no puede ser " " ni null y domicilio no puede ser null.
     */
    public void eliminaContratacionAbonado(String dni,Domicilio domicilio) throws DomicilioSinContratacionEnAbonadoException, DniDesconocidoException {
    	IFactura factura=datos.buscaFactura(dni);
    	if(factura!=null) {
    		factura.eliminarContratacion(domicilio);
	        if(factura.sinContratacion()) {
	        	datos.agregaAbonadoSinFactura(factura.getAbonado());
	        	datos.eliminaFactura(dni);
	        }
    	}
    	else {
    		throw new DniDesconocidoException(dni);
    	}
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

    /** Metodo que calcula el precio que debe pagar un abonado entre todas las contrataciones.
     * @param dni del abonado.
     * @param descuento true si se debe aplicar un descuento.
     * @return double con el valor que debe abonar el cliente.
     * @throws DniDesconocidoException cuando el dni ingresado no corresponde a ningun abonado. <br>
     * <b> Pre: </b> dni no debe ser null ni " ", y descuento no debe ser null.
     */
    public double calculaPrecioAPagar(String dni,boolean descuento) throws DniDesconocidoException{ 
    	IFactura factura=datos.buscaFactura(dni);
    	double rta=0;
    	if(factura!=null) {
    		if(descuento)
    			rta=factura.calcularTotalConDescuento();
    		else
    			rta=factura.calcularTotalSinDescuento();
    	}
    	else {
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
    public IFactura clonacionFactura(String dni) throws CloneNotSupportedException,DniDesconocidoException {
		IFactura original=datos.buscaFactura(dni);
		IFactura clon=null;
		if(original!=null) {
			clon=(IFactura) original.clone();	
		}
		else {
			throw new DniDesconocidoException(dni);
		}
		return clon;
	}
	
	
	/**
	 * Muestra el contenido de todas las facturas almacenadas junto con el detalle de cada una de <br>
	 * las contrataciones que realizo el Abonado  
	 **/
	public void MuestraEstado() {
		Iterator<IFactura> it = datos.iteratorFacturas();
		System.out.println("El sistema cuenta con las siguientes facturas:\n");
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}
}                                               

