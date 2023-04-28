package negocio;

import java.util.ArrayList;
import java.util.Iterator;

public class Factura implements Cloneable, IFactura {
    private Abonado abonado;
    private ArrayList<Contratacion> contrataciones = new ArrayList<Contratacion>();
    
    public Factura(Abonado abonado) {
        super();
        this.abonado = abonado;
    }
    
    // Precondiciones
    // Que el domicilio pertenezca al abonado
    public void agregarContratacion(Contratacion contratacion) {
    	contrataciones.add(contratacion);
    }
    // Si hay una contratacion con ese domicilio, tira excepción
    
    public double calcularTotalSinDescuento() {
    	return abonado.calcularTotal(this);
    }
    
    public double calcularTotalConDescuento() {
    	return abonado.calcularTotal(this);
    }
    
    public double getPrecioPersonaFisica(){
    	double res = 0;
    	Iterator<Contratacion>it = contrataciones.iterator();
    	while(it.hasNext()) {
    		res += it.next().getPrecio();
    	}
		return res;
    }
    
    public double getPrecioPersonaJuridica(){
    	double res = 0;
    	int i = 0;
    	Iterator<Contratacion>it = contrataciones.iterator();
    	while(it.hasNext()) {
    		if(i > 3)
    			res += it.next().getPrecio()*0.5;
    		else
    			res += it.next().getPrecio();
    		i++;
    	}
		return res;
    }
}

