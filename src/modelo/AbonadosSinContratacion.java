package modelo;

import java.util.ArrayList;
import java.util.Iterator;

import negocio.WorkAbonado;

public class AbonadosSinContratacion {
    public ArrayList<WorkAbonado> Abonados=new ArrayList<WorkAbonado>();
    
    public AbonadosSinContratacion() {
        super();
    }

	public Iterator<WorkAbonado> getAbonados() {
		return Abonados.iterator();
	}

	@Override
	public String toString() {
		return "AbonadosSinContratacion [Abonados=" + Abonados + "]"; //NO HECHO
	}
	
}
//prueba commit - comentario jo