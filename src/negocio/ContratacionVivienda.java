package negocio;

/** Clase que representa una contratación de monitoreo de alarmas para viviendas.
 */
public class ContratacionVivienda extends Contratacion {
    private static final double valorVivienda=8500;
   
    
     /**
      * Constructor con 4 parametros (2 int, 1 boolean y 1 Domicilio) para crear una nueva contratacion.
      * @param camaras Cantidad de camaras que se contratan.
      * @param botonesAntipanico Cantidad de botones antipanico que se contratan.
      * @param movilAcompanamiento Se determina si o no si se adquiere el movil de acompanamiento.
      * @param domicilio Domicilio del abonado, quien hara la contratacion. <br>
      * <b> Pre: </b> camaras y botonesAntipanicos no deben ser menores a 0, movilAcompanamiento no puede ser null, Domicilio no puede ser null.<br>
      * <b> Post: </b> Se crea una nueva contratacion.
      * 
      */
    public ContratacionVivienda(int camaras, int botonesAntipanico, boolean movilAcompanamiento, Domicilio domicilio) {
        super(camaras, botonesAntipanico, movilAcompanamiento, domicilio);
        this.precio = this.calculaPrecio();
    }
    
    /** Metodo para calcular el precio de la contratacion correspondiente, de acuerdo a las cantidades de botones antipanico y camaras, y si se contrata el movil de acompanamiento.
    * Se invoca al metodo calculaPrecio del padre y se suma el valor de la contratacion de comercio.
    * @return double con el valor del precio de la contratacion correspondiente. 
    */
    public double calculaPrecio(){ 
             return valorVivienda + super.calculaPrecio();
        }
        
    
    public void promo(Promo promo){ 
       promo.promo(this);
     }

    /** Metodo que aplica la promocion dorada para viviendas.
     * Modifica el precio de la contratacion con un descuento de $1500.
     */
     public void aplicaPromocionDorada(){
       this.precio -= 1500;
     }

    /** Metodo que aplica la promocion platino para comercios.
     * Modifica el precio de la contratacion con un descuento del 30%.
     */
     public void aplicaPromocionPlatino(){
       this.precio *= 0.7;
     }
}