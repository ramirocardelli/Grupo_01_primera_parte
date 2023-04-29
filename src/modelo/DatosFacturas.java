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
	
	public void eliminaFacturas(int i) {
		this.eliminaFacturas(i);
	}
	
	@Override
	public String toString() {
		return "DatosFacturas [Facturas=" + this.facturas + "]"; //NO HECHO
	}
    
}