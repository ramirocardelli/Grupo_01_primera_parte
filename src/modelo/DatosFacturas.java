package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import negocio.IFactura;

/** Clase que representa una base de datos con la informacion de las facturas.
 */
public class DatosFacturas {
    public ArrayList<IFactura> facturas=new ArrayList<IFactura>();
    
    public DatosFacturas() {
        super();
    }

    /** Metodo que retorna un Iterator para recorrer la lista de facturas.
     * @return Iterator de la lista de facturas.
     */
    public Iterator<IFactura> getFacturas() {
            return this.facturas.iterator();
    }

    /** Metodo para agregar una factura a la lista de facturas.
     * @param factura : factura que se desea agregar a la lista. <br>
     * <b> Pre: </b> factura no puede ser null, ni tampoco puede ya encontrarse en la lista.
     */
    public void agregaFactura(IFactura factura) {
            this.facturas.add(factura);
    }
	
    /** Metodo para eliminar una factura de la lista.
     * @param factura : factura que se desea eliminar de la lista.
     * @return boolean para informar si se pudo eliminar la factura.
     */
    public boolean eliminaFacturas(IFactura factura) {
            return this.facturas.remove(factura);
    }
}