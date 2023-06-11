package negocio;

import java.io.Serializable;
import java.util.ArrayList;

public class SubSistemaTecnicos implements Serializable{
	private ArrayList<Tecnico> tecnicos=new ArrayList<Tecnico>();

	
	
	public SubSistemaTecnicos() {
		super();
	}
	/**
	 * 
	 * Pre: tecnico!=null
	 * @param tecnico
	 */
	public void agregarTecnico(Tecnico tecnico) {//Habria que poner semaforos para controlar acceso al mismo tiempo al arreglo
		tecnicos.add(tecnico);
		notifyAll();//notifica a todos q hay un nuevo tecnico disponible
	}
	/**
	 * 
	 * Pre: tecnico!=null
	 * @param tecnico
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
	
	public synchronized void liberarTecnico(Tecnico tecnico) {
		tecnico.atendiendo=false;
		notifyAll();
	}
}
