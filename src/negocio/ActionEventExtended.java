package negocio;

import java.awt.event.ActionEvent;


/** Clase que funciona como pojo para transportar datos de la ventana mediante un ActionEvent.
 */
public class ActionEventExtended extends ActionEvent{ 
	protected String DNI;
	protected String calle;
	protected int numero;
	protected String tipoServicio;
	protected int cantBotones;
	protected int cantCamaras; 
	protected String movil; 
	protected String nombreTecnico;
	protected String metodoPago;
	
	public ActionEventExtended(Object source, int id, String command, String dNI, String calle, int numero,
			String tipoServicio, int cantBotones, int cantCamaras, String movil, String nombreTecnico,
			String metodoPago) {
		super(source, id, command);
		DNI = dNI;
		this.calle = calle;
		this.numero = numero;
		this.tipoServicio = tipoServicio;
		this.cantBotones = cantBotones;
		this.cantCamaras = cantCamaras;
		this.movil = movil;
		this.nombreTecnico = nombreTecnico;
		this.metodoPago = metodoPago;
	}
	
	

}
