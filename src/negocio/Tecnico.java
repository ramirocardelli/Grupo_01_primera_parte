package negocio;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public class Tecnico implements Serializable{
	public Abonado atendiendo;
	
	
	public synchronized void solicitarTecnico() {
		while (atendiendo!=null) {
			System.out.println("Tecnico ocupado");
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			TimeUnit.SECONDS.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		notifyAll();
	}
}
