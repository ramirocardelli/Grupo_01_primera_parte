package negocio;

/** Clase que representa la promo platino que ocasinalmente la empresa aplica a sus servicios.
 */
public class PromoPlatino extends Promo{
    public PromoPlatino() {
        super();
    }

     /** Metodo que manda un mensaje a una contratacion determinada para aplicar la promo dorada. Double dispatching.
      * @param contratacion : contratacion a la cual aplicar la promo dorada. <br>
      * <b> Pre: </b> contratacion no puede ser null.
      */
    public void promo(Contratacion contratacion){
        contratacion.aplicaPromocionPlatino();
    }
}
