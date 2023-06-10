package negocio;

import java.io.Serializable;
import java.util.ArrayList;

public class Tecnicos implements Serializable{
	private ArrayList<Tecnico> tecnicos=new ArrayList<Tecnico>();

	
	
	public Tecnicos() {
		super();
	}
	/**
	 * 
	 * Pre: tecnico!=null
	 * @param tecnico
	 */
	public void agregarTecnico(Tecnico tecnico) {//Habria que poner semaforos para controlar acceso al mismo tiempo al arreglo
		tecnicos.add(tecnico);
	}
	/**
	 * 
	 * Pre: tecnico!=null
	 * @param tecnico
	 */
	public void eliminarTecnico(Tecnico tecnico) {
		tecnicos.remove(tecnico);
	}
	
	
	public void solicitarTecnico(Abonado solicitante) {
	boolean atendido=false;
	do {
		int i=0;
		while (i<tecnicos.size() && atendido==false) { //recorre todos los tecnicos
			if (tecnicos.get(i).atender()==false) { // Si el tecnico esta ocupado va al siguiente
				i++;
			}
			else {
				atendido=true;
				
			}
		}
		if (atendido==false) // si recorrio todos los tecnicos y todos estaban ocupados va al wait
			synchronized(this) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		else
			notifyAll(); //si el abonado fue atendido entonces un tecnico esta disponible y manda a un abonado en espera atenderse.
	}while(atendido==false);
}
}
