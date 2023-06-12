package negocio;

import java.io.Serializable;
import java.util.ArrayList;

public class SubSistemaTecnicos implements Serializable{
	private ArrayList<Tecnico> tecnicos=new ArrayList<Tecnico>();

	
	
	public SubSistemaTecnicos() {
		super();
	}
	/**
	 * Funcion que agrega un tecnico a la lista de tecnicos y notifica a todos los thread de que hay un nuevo recurso
	 * disponible.
	 * Pre: tecnico!=null
	 * Post: Tecnico agregado a la lista
	 * @param tecnico
	 */
	public void agregarTecnico(Tecnico tecnico) {//Habria que poner semaforos para controlar acceso al mismo tiempo al arreglo
		tecnicos.add(tecnico);
		notifyAll();
	}
	/**
	 * 
	 * Pre: tecnico!=null
	 * @param tecnico
	 */
	public void eliminarTecnico(Tecnico tecnico) {
		tecnicos.remove(tecnico);
	}
	
	/**
	 * 
	 * 
	 * Funcion interna que libera un tecnico y notifique a todos los threads que ese tecnico ahora se encuentra libre
	 * para ser utilizado por otro thread.
	 * @param tecnico
	 */
	public synchronized void liberarTecnico(Tecnico tecnico) {
		tecnico.atendiendo=false;
		notifyAll();
	}
	
	/**
	 * Funcion interna que administra los threads para asignarle a cada uno un tecnico o si no hubiera tecnicos disponibles
	 * se los pone en espera. 
	 * Post: Devuelve un Tecnico que esta atendiendo a ese thread
	 * @return
	 */
	public synchronized Tecnico solicitarTecnico() {
	int i;
	Tecnico rta=null;
	do {
		i=0;
		while (i<tecnicos.size() && tecnicos.get(i).atendiendo==true) //recorre todos los tecnicos
			i++;
		if (i<tecnicos.size()==true){//encontro un tecnico libre
			tecnicos.get(i).atendiendo=true;
			rta=tecnicos.get(i);
		} else
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}while(rta!=null);
	
	return rta;
	
	
	}
}