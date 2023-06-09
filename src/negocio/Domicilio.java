package negocio;

/** Clase que representa un domicilio de un abonado. Contiene un String para el nombre de la calle y un int para la altura. 
 */
public class Domicilio implements Cloneable {
    private String calle;
    private int numero;

    /** Constructor de un parametro String y otro int que crea un domicilio para un abonado.
     * @param calle : String con el nombre de la calle.
     * @param numero : int con la altura de la vivienda.<br>
     * <b> Pre: </b> calle no puede ser null ni " ", numero no puede ser negativo. <br>
     * <b> Post: </b> Se crea domicilio, que contiene la calle y el numero de la vivienda.
     */
    private Domicilio(String calle, int numero) {
            this.calle=calle;
            this.numero=numero;
    }
    
    /**
     * 
     * Metodo que se encarga de generar un Objeto de tipo Domicilio con los datos proporcionados.<br>
     * No se permite la preacion de Domicilios incorrectos
     * @param calle : String de calle del domicilio
     * @param numero : int numero del domicilio 
     * @return Domicilio. Se devuelve un Objeto de tipo Domicilio si los datos cargados cumplen con las condiciones
     * <b> Post: </b> Se crea domicilio si cumple las condiciones, caso contrario devuelve null.
     */
    public static Domicilio generaDomicilio(String calle,int numero) {
    	Domicilio rta=null;
    	if(calle!=null && !calle.equals("") && numero<=9999 && numero>0) {
    		rta=new Domicilio(calle, numero);
    	}
    	return rta;
    }

	public String getCalle() {
		return calle;
	}

	public int getNumero() {
		return numero;
	}
	
	public boolean equals(Domicilio domicilio) {
		return (this.calle.equals(domicilio.calle) && this.numero==domicilio.numero);
	}

	@Override
	public String toString() {
		return "Calle: " + calle + " numero: " + numero ;
	}
	
	public Object clone() throws CloneNotSupportedException {
		Domicilio clon=(Domicilio)super.clone();
		return clon;
	}
}
