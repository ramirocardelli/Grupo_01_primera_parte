package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Observable;
import java.util.Observer;

import negocio.ActionEventExtended;
import negocio.Domicilio;
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
		String mensaje;
		mensaje = (String) arg;
		this.vista.muestraMensaje(mensaje);
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
				else if (comando.equalsIgnoreCase("SERIALIZAR")){
					try {
						this.serializar();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					try {
						this.deserializar();
					} catch (ClassNotFoundException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} // DESERIALIZAR
				}
	}
					

		public void serializar() throws IOException {
			IPersistencia persistencia = new Persistencia();
			
			persistencia.abrirOutput("sistema.bin");
			persistencia.escribir(sistema);
			persistencia.cerrarOutput();
			
		}
		
		public void deserializar() throws IOException, ClassNotFoundException {
			IPersistencia persistencia = new Persistencia();
			
			persistencia.abrirInput("sistema.bin");
			this.sistema = (Sistema) persistencia.leer();
			persistencia.cerrarInput();
			
		}


}
