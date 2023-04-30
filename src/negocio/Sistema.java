package negocio; // sistema ->invoca todos los metodos

import java.util.Iterator;

public class Sistema { //Singleton
    private SubSistemaDatos datos;
    private static Sistema instancia=null;

    private Sistema() { //se pasan por constructor y no se les hace new para no limitar la herencia
        super();
        this.datos=new SubSistemaDatos();
    }
    
    public static Sistema getInstance() {
    	if(Sistema.instancia==null) {
    		instancia= new Sistema();
    	}
		return instancia;
    }
    
    
    public Abonado buscaAbonado(String dni) throws DniDesconocidoException {
    	Abonado rta= this.datos.buscaAbonado(dni);
    	if(rta==null) {
    		throw new DniDesconocidoException(dni);
    	}
    	return rta;
    }
    
    public Contratacion buscaContratacion(Domicilio domicilio) throws DomicilioSinContratacionException {
    	Contratacion rta=this.datos.buscaContratacion(domicilio);
    	if(rta==null)
    		throw new DomicilioSinContratacionException(domicilio);
    	return rta;
    }
    
    public IFactura buscaFactura(String dni) throws DniDesconocidoException {
    	IFactura rta=this.datos.buscaFactura(dni);
    	if(rta==null) {
    		throw new DniDesconocidoException(dni);
    	}
    	return this.datos.buscaFactura(dni);
    }
    
    public void nuevoAbonado(String nombre,String dni,String tipo) throws AbonadoYaCargadoException, TipoIncorrectoPersonaException{ //FALTA LANZAR LA EXCEPCION DEL FACTORY
    	if(datos.buscaAbonado(dni)==null) { //verifica que abonado no este cargado en la lista de abonados sin contratacion
    		FactoryAbonado FA=new FactoryAbonado();
    		Abonado abonado=FA.creaAbonado(nombre, dni, tipo);
    		IFactura busquedaF=datos.buscaFactura(dni);
    		if(busquedaF==null) { //si no encuentra el abonado en las facturas lo guarda en la lista de abonados sin servicios
    			datos.agregaAbonadoSinFacctura(abonado);
    		}
    		else
    			throw new AbonadoYaCargadoException(nombre,dni,true);
    	}
    	else
    		throw new AbonadoYaCargadoException(nombre,dni,false);
    }
    
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
    
    public void eliminarFactura(String dni) throws DniDesconocidoException {
    	datos.eliminaFactura(dni);
    }
    
    public void eliminaAbonadoSinContratacion(String dni)throws DniDesconocidoException, AbonadoYaCargadoException {
    	datos.eliminaAbonadoSinFactura(dni);
    }
    
    public void eliminaContratacionAbonado(String dni,Domicilio domicilio) throws DomicilioSinContratacionEnAbonadoException, DniDesconocidoException {
    	IFactura factura=datos.buscaFactura(dni);
    	if(factura!=null) {
    		factura.eliminarContratacion(domicilio);
	        if(factura.sinContratacion()) {
	        	datos.agregaAbonadoSinFacctura(factura.getAbonado());
	        	datos.eliminaFactura(dni);
	        }
    	}
    	else {
    		throw new DniDesconocidoException(dni);
    	}
    }
    
    public void aplicaPromocion(Domicilio domicilio, Promo promocion) throws DomicilioSinContratacionException {
        Contratacion contratacion=datos.buscaContratacion(domicilio);
        if(contratacion!=null)
        	contratacion.promo(promocion);
        else
        	throw new DomicilioSinContratacionException(domicilio);  
    }
	
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
	 * Muestra el contenido de todas las Facturas almacenadas junto con el detalle de cada una de <br>
	 * las contrataciones que realizo el Abonado  
	 **/
	public void MuestraEstado() {
		Iterator<IFactura> it = datos.iteratorFacturas();
		System.out.println("El sistema cuenta con las siguientes facturas:\n");
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		
		//Recorre los 2 arreglos mostrando toda la informacion contenida en ellos (tostring)
	}
}                                               

