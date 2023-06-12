package negocio;

import java.awt.event.ActionEvent;


/** Clase que funciona como pojo para transportar datos de la ventana mediante un ActionEvent.
 */
public class ActionEventExtended extends ActionEvent{ 
	public String DNI;
	public String calle;
	public int numero;
	public String tipoServicio;
	public int cantBotones;
	public int cantCamaras; 
	public String movil; 
	public String nombreTecnico;
	public String metodoPago;
	
	public ActionEventExtended(Object source, int id, String command) {
		super(source,id,command);
	}
	
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
