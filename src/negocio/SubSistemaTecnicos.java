package negocio;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * Clase que permite realizar la administracion concurrente de Tecnicos solicitados por los Abonados. 
 *
 */
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
	public synchronized void agregarTecnico(Tecnico tecnico) {
    	assert tecnico != null: "Tecnico nulo";
		tecnicos.add(tecnico);
		notifyAll();
	}
	
	/**
	 * 
	 * Pre: tecnico!=null
	 * @param tecnico
	 */
	public void eliminarTecnico(Tecnico tecnico) {
    	assert tecnico != null: "Tecnico nulo";
		tecnicos.remove(tecnico);
	}
	
	/**
	 * 
	 * 
	 * Funcion interna que libera un tecnico y notifique a todos los threads que ese tecnico ahora se encuentra libre
	 * para ser utilizado por otro thread.
	 * @param tecnico
	 */
	public synchronized void liberarTecnico(String tecnico) {
    	assert tecnico != null: "Tecnico nulo";
		int i=0;
		while(i<tecnicos.size()&& !tecnicos.get(0).getNombre().equals(tecnico))
			i++;
		if(i<tecnicos.size())
			tecnicos.get(i).setAtendiendo(false);
		else
			//se ingreso un nombre de un tecnico desconocido, no hace nada (seria un error del programador obt nombres de otro lado que no sea el recurso compartido o modificar su nombre en su uso)

		Sistema.getInstance().muestraThread("El tecnico "+tecnico+" finaliza su trabajo y se dispone a seguir trabajando");
		notifyAll();
	}
	
	/**
	 * Funcion interna que administra los threads para asignarle a cada uno un tecnico o si no hubiera tecnicos disponibles
	 * se los pone en espera. 
	 * Post: Devuelve un Tecnico que esta atendiendo a ese thread
	 * @return
	 */
	public synchronized String solicitarTecnico(String nombre) {
    	assert nombre != null: "Nombre nulo";
    	assert nombre != "": "Nombre vacio";
		Tecnico rta=tecnicoLibre();
		while(tecnicoLibre()==null) { //si no se encontro un tecnico libre se espera
			try {
				this.wait();
				rta=tecnicoLibre();
			} catch (InterruptedException e) {
			}
		}
		Sistema.getInstance().muestraThread("El tecnico "+rta.getNombre()+" atiende al abonado "+ nombre);
		return rta.getNombre();
	}
	
	private synchronized Tecnico tecnicoLibre() {
		Tecnico rta=null;
		int i=0;
		while (i<tecnicos.size() && tecnicos.get(i).isAtendiendo()==true) //recorre todos los tecnicos
			i++;
		if (i<tecnicos.size()){//encontro un tecnico libre
			rta=tecnicos.get(i);
			rta.setAtendiendo(true);
		}
		return rta;
	}
}