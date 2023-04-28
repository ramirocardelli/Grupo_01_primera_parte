package negocio;

import java.util.ArrayList;

public class Factura implements Cloneable {
    private Abonado abonado;
    private ArrayList<Contratacion> contrataciones = new ArrayList<Contratacion>();
    

    
    public Factura() {
        super();
    }
    
    public double getPrecioTotal(){
		return 0;
    }
    
    public double getPrecioPFisica(){
		return 0;
    }
    
    public double getPrecioPJuridica(){
		return 0;
    }
    
}

