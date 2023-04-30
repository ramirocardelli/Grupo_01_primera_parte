package negocio;

/** Clase que representa un domicilio de un abonado. Contiene un String para el nombre de la calle y un int para la altura. 
 */
public class Domicilio {
    private String calle;
    private int numero;

    /** Constructor de un parametro String y otro int que crea un domicilio para un abonado.
     * @param calle : String con el nombre de la calle.
     * @param numero : int con la altura de la vivienda.<br>
     * <b> Pre: </b> calle no puede ser null ni " ", numero no puede ser negativo. <br>
     * <b> Post: </b> Se crea domicilio, que contiene la calle y el numero de la vivienda.
     */
    public Domicilio(String calle, int numero) {
        this.calle=calle;
        this.numero=numero;
    }

	public String getCalle() {
		return calle;
	}

	public int getNumero() {
		return numero;
	}
	
	public boolean equals(Domicilio domicilio) {
		boolean rta=false;
		if(this.calle.equals(domicilio.calle) && this.numero==domicilio.numero) {
			rta=true;
		}
		return rta;
	}

	@Override
	public String toString() {
		return "Domicilio con calle: " + calle + " y numero: " + numero ;
	}
	
	
    
}
