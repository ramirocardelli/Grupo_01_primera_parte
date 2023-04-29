package negocio; // sistema ->invoca todos los metodos

import java.util.Iterator;

import modelo.*;

public class Sistema { //Singleton
    private SubSistemaDatos datos;

    public Sistema() { //se pasan por constructor y no se les hace new para no limitar la herencia
        super();
        this.datos=new SubSistemaDatos();
    }
    
    public Abonado buscaAbonado(String dni) { //si no lo encuentra devuelve null
    	return this.datos.buscaAbonado(dni);
    }
    
    public Contratacion buscaContratacion(String calle,int numero) {
    	Domicilio domicilio=new Domicilio(calle,numero);
    	return this.datos.buscaContratacion(domicilio);
    }
    
    public Factura buscaFactura(Abonado abonado) {
    	return this.datos.buscaFactura(abonado);
    }
    
    public void nuevoAbonado(String nombre,String dni,String tipo) throws AbonadoYaCargadoConFactura,AbonadoYaCargadoSinFactura, TipoIncorrectoPersonaException{ //FALTA LANZAR LA EXCEPCION DEL FACTORY
    	//verificar que no existe en la lista de abonados existente y tampoco en la lista de facturas
    	if(buscaAbonado(dni)==null) {
    		FactoryAbonado FA=new FactoryAbonado();
    		Abonado abonado=FA.creaAbonado(nombre, dni, tipo);
    		Factura busquedaF=buscaFactura(abonado);
    		if(busquedaF==null) {
    			datos.agregaAbonadoSinFacctura(abonado);
    		}
    		else
    			throw new AbonadoYaCargadoConFactura(nombre,dni);
    	}
    	else
    		throw new AbonadoYaCargadoSinFactura(nombre,dni);
    }
    
    public void nuevaContratacion(Abonado abonado,int camaras, int botonesAntipanicos, boolean movilAcompanamiento, Domicilio domicilio, String tipo) { //falta lanzar la excepcion de domicilio ya con contratacion, del factory y la de no encontrar abonado con factura
    	Contratacion nuevaContratacion=factoryContratacion(camaras, botonesAntipanicos, movilAcompanamiento, domicilio, tipo);
    	
    	//Se debe buscar si el abonado que se pasa tiene una factura existente y si el domicilio ingresado ya existe en cualquier otra factura
    	if(abonado tiene facutura){
    		if(domicilio no se repite en cualq factura) { 
    			//agrega la contratacion a la factura
    		}
    		else
    			throw new DomicilioYaConContratacion(abonado,nuevaContratacion);
    	}
    	else
    		throw new AbonadoSinFacturaException(abonado,nuevaContratacion);
                
    }
    
    /* public int domicilioNoRepetido(Domicilio domicilio) {
	Iterator<Factura> it=Datos.getIteratorFacturas();
	Factura aux;
	while (it.hasNext()) {
		aux=(Factura)it.next();
		
	}
}*/
    
    public void nuevaFactura(Abonado abonado, String tipo) {
    	//busca el abonado en la lista de abonados sin contratacion
    	if(encuentraabonado)
    		//crea la factura con factory y la inserta en la capa de datos
    	else
    		throw new AbonadoYaConFacturaException();
    }
    
    public void eliminarFactura(Abonado abonado) {
    	
    }
    
    
    public void eliminaContratacionAbonado(Abonado abonado,Domicilio domicilio) {
    	Factura factura=null;
    	//busca la factura
    	if(factura existe) {
    		//busca contratacion
        	if(contratacion existe)
        		//elimina y pregunta si queda vacio el arreglo
        	if(arreglovacio)
        		//elimina la factura y mueve al abonado a la lista de abonados sin contratacion
    	}
    	else
    		//tira excepcion
    }
    
    public void aplicaPromoDorada(Contratacion contratacion) {
        contratacion.aplicaPromocionDorada();
    }
	
    public void aplicaPromoPlatino(Contratacion contratacion) {
        contratacion.aplicaPromocionPlatino();
    }
	
    public void calculaPrecioAPagar(){ //de una factura especifica pasada por parametro
    }
	
	public Factura clonacionFactura(Factura original) throws CloneNotSupportedException {
		Factura clon=(Factura) original.clone(); //llama al metodo de factura para que devuelva su clonado
		return clon;
	}
	
	public void MuestraEstado() {
		//Recorre los 2 arreglos mostrando toda la informacion contenida en ellos (tostring)
	}
}                                               

