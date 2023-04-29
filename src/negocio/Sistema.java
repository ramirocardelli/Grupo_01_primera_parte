package negocio; // sistema ->invoca todos los metodos

import java.util.Iterator;

import modelo.*;

public class Sistema { //Singleton
    //private Datos datos; -> array de clientes-alta de clientes

    public Sistema() {
        super(); 
    }
    //clase conjunto de clientes - interfaz conjunto de clientes
    //abonado 
    public void agregaContratacionAbonado(Abonado abonado,Contratacion contratacion) throws FacturaInexistenteException{
    	Factura factura=null;
    	factura = Datos.buscaFacturaAbonado(abonado);
    	//busca que no esta repetido el domicilio
    	if (factura==null)
    		throw new FacturaInexistenteException(abonado,contratacion);
    	else
            if (domicilioNoRepetido(domicilio))
                factura.agregarContratacion(contratacion);
    }
    
    public int domicilioNoRepetido(Domicilio domicilio) {
    	Iterator<Factura> it=Datos.getIteratorFacturas();
    	Factura aux;
    	while (it.hasNext()) {
    		aux=(Factura)it.next();
    		
    	}
    }
    
    public void eliminaContratacionAbonado(Abonado abonado,Domicilio domicilio) {
    	Factura factura=null;
    	factura=Datos.buscaFacturaAbonado(abonado);
    	factura.eliminarContratacion(domicilio);
    	this.almacenaFactura(factura);	
    }
    
    public void almacenaFactura(Factura factura) {
    	Datos.poneFacturaEnLista(factura);
    }
    
    public void aplicaPromoDorada(Contratacion contratacion) {
        contratacion.aplicaPromocionDorada();
    }
	
    public void aplicaPromoPlatino(Contratacion contratacion) {
        contratacion.aplicaPromocionPlatino();
    }
	
    public void calculaPrecioAPagar(){
    }
	
    /* public void 
	busca factura
	nueva factura
	elimina factura
	clonar factura
    */
}                                               

