package modelo;

import java.util.ArrayList;
import java.util.Iterator;

import negocio.Abonado;

public class AbonadosSinContratacion {
    public ArrayList<Abonado> Abonados=new ArrayList<Abonado>();
    
    public AbonadosSinContratacion() {
        super();
    }

	public Iterator<Abonado> getAbonados() {
		return Abonados.iterator();
	}
	
	public void agregaAbonado(Abonado abonado) {
		this.Abonados.add(abonado);
	}
	
	public void eliminaAbonado(int i) {
		this.Abonados.remove(i);
	}
	
	@Override
	public String toString() {
		return "AbonadosSinContratacion [Abonados=" + Abonados + "]"; //NO HECHO
	}
}
//prueba commit - comentario jo