package negocio; // sistema ->invoca todos los metodos

import java.util.Iterator;

import modelo.*;

public class Sistema { //Singleton
    private AbonadosSinContratacion datoAbonadosSinC; //sistema tendra una referencia a la capa de datos mediante la cual se comunicara para solicitar o modificar datos
    private DatosFacturas datoFacturas;

    public Sistema(AbonadosSinContratacion abonadosSnC, DatosFacturas datoFacturas) { //se pasan por constructor y no se les hace new para no limitar la herencia
        super();
        this.datoAbonadosSinC=abonadosSnC;
        this.datoFacturas=datoFacturas;
    }
    
    
    //clase conjunto de clientes - interfaz conjunto de clientes
    //abonado 
    
    public Abonado buscaAbonado(String dni) {
    	//Busca abonado en lista de facturas y lista de abonados sin contratacion
    }
    
    public Contratacion buscaContratacion(Abonado abonado,Domicilio domicilio) {
    	//busca la factura y luego dentro de la factura busca la contratacion
    }
    
    public Factura buscaFactura(Abonado abonado) {
    	//busca la factura por abonado
    }
    
    public void nuevoAbonado(String nombre,String dni,String tipo){ //FALTA LANZAR LA EXCEPCION DEL FACTORY
    	//verificar que no existe en la lista de abonados existente y tampoco en la lista de facturas
    	Abonado abonado=factoryAbonado(nombre,dni,tipo);
    	//agregar a la lista de abonadosSinContratacion
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

