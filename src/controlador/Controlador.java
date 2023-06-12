package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Observable;
import java.util.Observer;

import negocio.ActionEventExtended;
import negocio.Domicilio;
import negocio.Estado;
import negocio.Sistema;
import persistencia.IPersistencia;
import persistencia.Persistencia;
import vista.IVista;
import vista.Ventana;

@SuppressWarnings("deprecation")
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
		if (o != this.sistema) {
			throw new InvalidParameterException();
		}
		Estado estado=(Estado)arg;
		if(estado.getQueSoy()=="SISTEMA")
			this.vista.muestraMensaje(estado.getMensaje());
		else
			if(estado.getQueSoy()=="EXCEPCION")
				this.vista.muestraMensaje(estado.getMensaje());
			else
				if(estado.getQueSoy()=="THREAD")
					this.vista.muestraMensaje(estado.getMensaje());
		}

	@Override
	public void actionPerformed(ActionEvent a) //ventana lanza eventos
	{
		
		ActionEventExtended e = (ActionEventExtended) a;
		String comando = e.getActionCommand();
		if (comando.equalsIgnoreCase("PAGAR")) {
			this.sistema.PagarFactura(e.DNI,e.metodoPago);
		}
		else if (comando.equalsIgnoreCase("CONTRATAR")) {
				this.sistema.nuevaContratacion(e.DNI,e.cantCamaras,e.cantBotones,e.movil.equalsIgnoreCase("Si"),new Domicilio(e.calle,e.numero),e.tipoServicio);
			}
			else if (comando.equalsIgnoreCase("DARBAJA")) {
					this.sistema.eliminaContratacionAbonado(e.DNI,new Domicilio(e.calle,e.numero));
				}
				else if (comando.equalsIgnoreCase("HISTORICO")) {
						this.sistema.historico(e.DNI); //AGREGAR
					}
	
				else if (comando.equalsIgnoreCase("ACTUALIZARMES")) {
					this.sistema.findeMes();
				}
				else if (comando.equalsIgnoreCase("SOLICITARTECNICO")) {
					this.sistema.solicitarTecnico(e.DNI); //AGREGAR
				}
				else if (comando.equalsIgnoreCase("ALTATECNICO")){
					this.sistema.altaTecnico(e.nombreTecnico); //AGREGAR
				}
				else if (comando.equalsIgnoreCase("PERSISTIR")){
						this.serializar();
				}
				else if (comando.equalsIgnoreCase("DESPERSISTIR")){

						this.deserializar();
				}
	}
					

		public void serializar() {
			IPersistencia persistencia = new Persistencia();

			try {
				persistencia.abrirOutput("sistema.bin");
				persistencia.escribir(sistema);
				persistencia.cerrarOutput();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		
		public void deserializar() {
			IPersistencia persistencia = new Persistencia();
			try {
				persistencia.abrirInput("sistema.bin");
				this.sistema = (Sistema) persistencia.leer();
				persistencia.cerrarInput();
			} catch (ClassNotFoundException | IOException e1) {
				e1.printStackTrace();
			}
			
		}


}
