package negocio;

import java.io.Serializable;
import java.util.concurrent.*;

public class Tecnico implements Serializable{
	protected String nombre;
	protected Semaphore semaforo=new Semaphore(1,true);

	public Tecnico(String nombre) {
		super();
		this.nombre = nombre;
	}

	@Override
	public boolean equals(Object obj) {
		Tecnico tecnico;
		if (obj instanceof Tecnico) {
			tecnico=(Tecnico) obj;
			return this.nombre.equalsIgnoreCase(tecnico.nombre);
		}
		return false;
	}
	
	public boolean atender() {
		if (semaforo.tryAcquire()==true) {
			try {
				TimeUnit.SECONDS.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		else
			return false;
	}
	
	
}
