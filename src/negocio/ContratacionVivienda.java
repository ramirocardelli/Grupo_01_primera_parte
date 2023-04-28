package negocio;

/** Clase que representa una contratación de monitoreo de alarmas para viviendas.
 */
public class ContratacionVivienda extends Contratacion {
    private static final double valorVivienda=8500;
   
    
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

     public void aplicaPromocionDorada(){
       this.precio -= 1500;
     }

     public void aplicaPromocionPlatino(){
       this.precio *= 0.7;
     }
}