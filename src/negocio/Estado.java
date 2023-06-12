package negocio;

public class Estado{
	String mensaje; //mensaje a mostrar o null
	String queSoy;//informa si es una excepcion, resultado de thread o un cambio en sistema
	
	public Estado(String mensaje, String queSoy) {
		super();
		this.mensaje = mensaje;
		this.queSoy = queSoy;
	}
	public String getMensaje() {
		return mensaje;
	}
	public String getQueSoy() {
		return queSoy;
	}

}
