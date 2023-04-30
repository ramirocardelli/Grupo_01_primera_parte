package modelo;

import java.util.ArrayList;
import java.util.Iterator;

import negocio.IFactura;

/** Clase que representa la base de datos que contiene toda la informacion de las facturas.
 */
public class DatosFacturas {
    public ArrayList<IFactura> facturas=new ArrayList<IFactura>();
    
    public DatosFacturas() {
        super();
    }

    /** Metodo que retorna un Iterator de la lista de facturas.
     * @return Iterator de la lista de abonados sin facturas.
     */
    public Iterator<IFactura> getFacturas() {
            return this.facturas.iterator();
    }

    /** Metodo que agrega una nueva factura a la lista. // si ya esta en la lista que hacemo
     * @param IFactura : factura a agregar. <br>
     * <b> Pre: </b> factura no puede ser null.
     */
    public void agregaFactura(IFactura factura) {
            this.facturas.add(factura);
    }
    
    /** Metodo que elimina una factura de la lista.
     * @param i : posicion de la lista en la que se encuentra la factura que se quiere eliminar. <br>
     * <b> Pre: </b> i no puede ser null ni menor a 0.
     */
    public void eliminaFacturas(int i) {
    	this.eliminaFacturas(i);
    }
	
    /** Metodo sobrescrito toString que muestra en pantalla todas las facturas con su informacion.
     * @return String con todas las facturas y su informacion.
     */
    @Override
    public String toString() {
    	return "DatosFacturas [Facturas=" + this.facturas + "]"; //NO HECHO
    }
    
}