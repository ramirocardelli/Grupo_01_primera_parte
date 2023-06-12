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
	private Sistema sistema; 
	
	
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
		if (o != this.)
			throw new InvalidParameterException();
		
		if (arg.toString().contentEquals("MENSAJE")) {
			this.vista.muestraMensaje();
		}
		
	}

	@Override
	public void actionPerformed(ActionEventExtended e) //ventana lanza eventos
	{
		String comando = e.getActionCommand();
		if (comando.equalsIgnoreCase("PAGAR")) {
			this.sistema.PagarFactura(e.DNI,e.metodoPago);
		}
		else if (comando.equalsIgnoreCase("CONTRATAR")) {
				this.sistema.nuevaContratacion(e.dni,e.cantCamaras,e.cantBotones,e.movil.equalsIgnoreCase("Si"),new Domicilio(e.calle,e.numero),e.tipoServicio);
			}
			else if (comando.equalsIgnoreCase("DARBAJA")) {
					this.sistema.eliminaContratacionAbonado(e.dni,new Domicilio(e.calle,e.num));
				}
				else if (comando.equalsIgnoreCase("HISTORICO")) {
						this.sistema.
					}
	
				else if (comando.equalsIgnoreCase("ACTUALIZARMES")) {
					this.sistema.findeMes();
				}
				else if (comando.equalsIgnoreCase("SOLICITARTECNICO")) {
					
				}
				else if (comando.equalsIgnoreCase("ALTATECNICO")){
					
				}
				else if (comando.equalsIgnoreCase("SERIALIZAR"){
					this.serializar();
				}
				else {
					this.deserializar();// DESERIALIZAR
				}
					

		public void serializar() {
			
		}
}
