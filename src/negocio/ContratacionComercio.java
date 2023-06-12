package negocio;

import java.io.Serializable;

/** Clase que representa una contratacion de monitoreo de alarmas para comercios.
 */
public class ContratacionComercio extends Contratacion implements Serializable {
    private static final double valorComercio = 10000;

     /**
      * Constructor con 4 parametros (2 int, 1 boolean y 1 Domicilio) para crear una nueva contratacion.
      * @param camaras Cantidad de camaras que se contratan.
      * @param botonesAntipanico Cantidad de botones antipanico que se contratan.
      * @param movilAcompanamiento Se determina si o no si se adquiere el movil de acompanamiento.
      * @param domicilio Domicilio del abonado, quien hara la contratacion. <br>
      * <b> Pre: </b> camaras y botonesAntipanicos no deben ser menores a 0, Domicilio no puede ser null.<br>
      * <b> Post: </b> Se crea una nueva contratacion.
      * 
      */
    public ContratacionComercio(int camaras, int botonesAntipanico, boolean movilAcompanamiento, Domicilio domicilio){
        super(camaras, botonesAntipanico, movilAcompanamiento, domicilio);
    	assert camaras < 0 : "Numero de camaras menor a cero";
    	assert botonesAntipanico < 0 : "Numero de botones antipanicomenor a cero";
    	assert domicilio != null : "Domicilio nulo";
        this.precio = this.calculaPrecio();
    }

    /** Metodo para calcular el precio de la contratacion correspondiente, de acuerdo a las cantidades de botones antipanico y camaras, y si se contrata el movil de acompanamiento.
     * Se invoca al metodo calculaPrecio del padre y se suma el valor de la contratacion de comercio.
     * @return double con el valor del precio de la contratacion correspondiente. 
     */
    public double calculaPrecio(){ 
    	return valorComercio + super.calculaPrecio();
    }
    
    /** Metodo que aplica la promocion dorada para comercios.
     * Modifica el precio de la contratacion con un descuento de $2500.
     */
    public void aplicaPromocionDorada(){
    	 if(this.promocion!=1) {
        	 if(this.promocion==2) {
        	   calculaPrecio();
        	 }
        	 this.promocion=1;
        	 this.precio -= 2500;
           }
     }

    /** Metodo que aplica la promocion platino para comercios.
     * Modifica el precio de la contratacion con un descuento del 35%.
     */
     public void aplicaPromocionPlatino(){
    	if(this.promocion!=2) {
        	if(this.promocion==1) {
        	  calculaPrecio();
        	}
        	this.promocion=2;
        	this.precio *= 0.65;
    	}
     }

	@Override
	public String toString() {
		return "\t\t* Monitoreo de Alarmas de Comercio para Domicilio: " + domicilio + ". ID: "+ id +". Precio total: " + precio + " \n"
				+ "\t\t\tAgregados: \n "
				+ " \t\t\tCamaras: " + camaras + "\n"
				+ " \t\t\tBotones Antipánico: " + botonesAntipanicos+ "\n"
				+ " \t\t\tMovil/es de acompanamiento=" + movilAcompanamiento + " \n ";
	}

	@Override
	public void actualizaContratacion(int camaras, int botonesAntipanicos, int movilAcompanamiento) {
		this.camaras += camaras;
        if(this.camaras<0) {
        	this.camaras=0;
        }
        this.botonesAntipanicos += botonesAntipanicos;
        if(this.botonesAntipanicos<0) {
        	this.botonesAntipanicos=0;
        }
        if(movilAcompanamiento==0) //cualquier otro valorque no sea 1 o 0 no modifica atributo
        	this.movilAcompanamiento=false;
        else
        	if(movilAcompanamiento==1)
        		this.movilAcompanamiento=true;
        this.precio=calculaPrecio(); //recalcula el precio actualizado
        if(this.promocion==1) {
        	this.promocion=0;
        	this.aplicaPromocionDorada();
        }
        else {
        	if(this.promocion==2) {
        		this.promocion=0;
        		this.aplicaPromocionPlatino();
        	}
        }
        
    }
     
     
}