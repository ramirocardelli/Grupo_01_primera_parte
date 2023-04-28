package negocio;

/** Clase que representa una contratación de monitoreo de alarmas para comercios.
 */
public class ContratacionComercio extends Contratacion {
    private static final double valorComercio = 10000;

    /** Constructor de 4 parametros (int, int, boolean y Domicilio) para crear una nueva contratacion en el sistema.
     * @param camaras : Cantidad de camaras que se contratan.
     * @param botonesAntipanico : Cantidad de botones antipanico que se contratan.
     * @param movilAcompanamiento : Se determina o no si se contrata el movil de acompañamiento.
     * @param domicilio : Datos del domicilio del abonado.
     */
    public ContratacionComercio(int camaras, int botonesAntipanico, boolean movilAcompanamiento, Domicilio domicilio){
        super(camaras, botonesAntipanico, movilAcompanamiento, domicilio);
        this.precio = this.calculaPrecio();
    }

    /** Metodo para calcular el precio de la contratacion correspondiente, de acuerdo a las cantidades de botones antipanico y camaras, y si se contrata el movil de acompanamiento.
     * Se invoca al metodo calculaPrecio del padre y se suma el valor de la contratacion de comercio.
     * @return double con el valor del precio de la contratacion correspondiente. 
     */
    public double calculaPrecio(){ 
    	
    	return valorComercio + super.calculaPrecio();
    }
    
    public void promo(Promo promo){
       promo.promo(this);
     }

    /** Metodo que aplica la promocion dorada para comercios.
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