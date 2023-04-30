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
    
    
    public Abonado buscaAbonado(String dni) { //si no lo encuentra devuelve null
    	return this.datos.buscaAbonado(dni);
    }
    
    public Contratacion buscaContratacion(Domicilio domicilio) {
    	return this.datos.buscaContratacion(domicilio);
    }
    
    public IFactura buscaFactura(String dni) {
    	return this.datos.buscaFactura(dni);
    }
    
    public void nuevoAbonado(String nombre,String dni,String tipo) throws AbonadoYaCargado, TipoIncorrectoPersonaException{ //FALTA LANZAR LA EXCEPCION DEL FACTORY
    	if(buscaAbonado(dni)==null) { //verifica que abonado no este cargado en la lista de abonados sin contratacion
    		FactoryAbonado FA=new FactoryAbonado();
    		Abonado abonado=FA.creaAbonado(nombre, dni, tipo);
    		IFactura busquedaF=buscaFactura(dni);
    		if(busquedaF==null) { //si no encuentra el abonado en las facturas lo guarda en la lista de abonados sin servicios
    			datos.agregaAbonadoSinFacctura(abonado);
    		}
    		else
    			throw new AbonadoYaCargado(nombre,dni,true);
    	}
    	else
    		throw new AbonadoYaCargado(nombre,dni,false);
    }
    
    public void nuevaContratacion(String dni,int camaras, int botonesAntipanicos, boolean movilAcompanamiento, Domicilio domicilio, String tipo) throws DomicilioYaConContratacionExcepcion, NoExisteFacturaException, TipoIncorrectoServicio { //falta lanzar la excepcion de domicilio ya con contratacion, del factory y la de no encontrar abonado con factura
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
    
    public void nuevaFactura(String dni, String tipoPago,String tipoFactura) throws MetodoDePagoInvalidoException,TipoFacturaIncorrecto, AbonadoYaCargado, dniDesconocidoException{
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
    			throw new AbonadoYaCargado(buscafactura.getAbonado().getNombre(),dni,true);
    		}
    		else
    			throw new dniDesconocidoException(dni);
    	}
    }
    
    public void eliminarFactura(String dni) throws dniDesconocidoException {
    	datos.eliminaFactura(dni);
    }
    
    public void eliminaAbonadoSinContratacion(String dni)throws dniDesconocidoException, AbonadoYaCargado {
    	datos.eliminaAbonadoSinFactura(dni);
    }
    
    public void eliminaContratacionAbonado(String dni,Domicilio domicilio) throws DomicilioSinContratacionenAbonadoException, dniDesconocidoException {
    	IFactura factura=datos.buscaFactura(dni);
    	if(factura!=null) {
    		factura.eliminarContratacion(domicilio);
	        if(factura.sinContratacion()) {
	        	datos.agregaAbonadoSinFacctura(factura.getAbonado());
	        	datos.eliminaFactura(dni);
	        }
    	}
    	else {
    		throw new dniDesconocidoException(dni);
    	}
    }
    
    public void aplicaPromocion(Domicilio domicilio, Promo promocion) throws DomicilioSinContratacionException {
        Contratacion contratacion=datos.buscaContratacion(domicilio);
        if(contratacion!=null)
        	contratacion.promo(promocion);
        else
        	throw new DomicilioSinContratacionException(domicilio);  
    }
	
    public double calculaPrecioAPagar(String dni) throws dniDesconocidoException{ 
    	IFactura factura=datos.buscaFactura(dni);
    	double rta=0;
    	if(factura!=null) {
    		rta=factura.calcularTotalConDescuento();
    	}
    	else {
    		throw new dniDesconocidoException(dni);
    	}
		return rta;
    }
	
	public Factura clonacionFactura(Factura original) throws CloneNotSupportedException {
		Factura clon=(Factura) original.clone(); //llama al metodo de factura para que devuelva su clonado
		return clon;
	}
	
	
	
	
	/**
	 * Muestra el contenido de todas las Facturas almacenadas junto con el detalle de cada una de <br>
	 * las contrataciones que realizo el Abonado  
	 **/
	public void MuestraEstado() {
		Iterator<IFactura> it = datos.iteratorFacturas();
		System.out.println("Entro a metodo");
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		
		//Recorre los 2 arreglos mostrando toda la informacion contenida en ellos (tostring)
	}
}                                               

