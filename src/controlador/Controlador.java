package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import vista.IVista;
import vista.Ventana;

public class Controlador implements ActionListener, Observer{
	private IVista vista;
	
	
	public Controlador() {
		this.vista = new Ventana();
		this.vista.setActionListener(this);
	}
	


	public IVista getVista() {
		return vista;
	}



	public void setVista(IVista vista) {
		this.vista = vista;
	}



	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
