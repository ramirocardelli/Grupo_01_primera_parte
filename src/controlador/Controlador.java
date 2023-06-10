package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import negocio.Sistema;
import vista.IVista;
import vista.Ventana;

public class Controlador implements ActionListener, Observer
{
	private IVista vista; //vista
	private Sistema sistema; //modelo no se si va sistema aca- el que vaya tiene que implementar Observable
	
	
	public Controlador() {
		this.vista = new Ventana();
		this.vista.setActionListener(this);
	}
	


	public IVista getVista() {
		return vista;
	}


	public void setVista(IVista vista) { //independecia con la vista - poder cambiarlas
		this.vista = vista;
		this.vista.setActionListener(this);
		
	}

	@Override
	public void update(Observable o, Object arg) { //modelo envia mensaje
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) //ventana lanza eventos
	{
		String comando = e.getActionCommand();
		if (comando.equalsIgnoreCase("PAGAR")) {
			
		}
		else if (comando.equalsIgnoreCase("CONTRATAR")) {
				
			}
			else if (comando.equalsIgnoreCase("DARBAJA")) {
					
				}
				else if (comando.equalsIgnoreCase("HISTORICO")) {
						
					}
				else if (comando.equalsIgnoreCase("FACTURACION")) {
					
				}
				else if (comando.equalsIgnoreCase("ACTUALIZARMES")) {
					
				}
				else if (comando.equalsIgnoreCase("SOLICITARTECNICO")) {
					
				}
				else { //ALTA TECNICO
					
				}
	}

}
