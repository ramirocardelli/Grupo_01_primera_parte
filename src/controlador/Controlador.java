package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Observable;
import java.util.Observer;

import negocio.AbonadoYaCargadoException;
import negocio.ActionEventExtended;
import negocio.Domicilio;
import negocio.Estado;
import negocio.Sistema;
import negocio.TipoIncorrectoPersonaException;
import persistencia.IPersistencia;
import persistencia.Persistencia;
import persistencia.SistemaDTO;
import persistencia.UtilPersistencia;
import vista.*;

public class Controlador implements ActionListener, Observer
{
	private Sistema sistema; 
	private IVista vista; //vista
	
	
	public Controlador() {
		this.setVista(new Ventana(this));
		sistema=Sistema.getInstance();
		this.sistema.addObserver(this);
		this.sistema.despersistir();
		
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

	/**
	 * Pre: a debe ser ser de tipo ActionEventExtended o hijo de el
	 */
	public void actionPerformed(ActionEvent a) { //ventana lanza eventos
		assert ((a instanceof ActionEventExtended)==true);
		
		ActionEventExtended e=(ActionEventExtended) a;
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
			this.sistema.solicitarTecnico(e.DNI);
		}
		else if (comando.equalsIgnoreCase("ALTATECNICO")){
			this.sistema.altaTecnico(e.nombreTecnico);
		}
		else if (comando.equalsIgnoreCase("PERSISTIR")){
				this.sistema.persistir();
		}
		else if (comando.equalsIgnoreCase("ALTACLIENTE")) { 
				this.sistema.nuevoAbonado(e.nombreCliente,e.DNI,e.tipoPersona);
		}	 
	}
}
