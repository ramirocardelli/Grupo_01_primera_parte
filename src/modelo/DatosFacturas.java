package modelo;

import java.util.ArrayList;
import java.util.Iterator;

import negocio.IFactura;

public class DatosFacturas {
    public ArrayList<IFactura> facturas=new ArrayList<IFactura>();
    
    public DatosFacturas() {
        super();
    }

	public Iterator<IFactura> getFacturas() {
		return this.facturas.iterator();
	}

	public void agregaFactura(IFactura factura) {
		this.facturas.add(factura);
	}
	
	public boolean eliminaFacturas(IFactura factura) {
		return this.facturas.remove(factura);
	}

    
}