package modelo;

import java.util.ArrayList;
import java.util.Iterator;

import negocio.IFactura;

public class DatosFacturas {
    public ArrayList<IFactura> Facturas=new ArrayList<IFactura>();
    
    public DatosFacturas() {
        super();
    }

	public Iterator<IFactura> getFacturas() {
		return this.Facturas.iterator();
	}

	@Override
	public String toString() {
		return "DatosFacturas [Facturas=" + this.Facturas + "]"; //NO HECHO
	}
    
}