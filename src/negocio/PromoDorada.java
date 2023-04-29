package negocio;

/** Clase que representa la promo dorada que ocasionalmente la empresa aplica a sus servicios.
 */
public class PromoDorada extends Promo{
    public PromoDorada() {
        super();
    }

    /** Metodo que manda un mensaje a una contratacion determinada para aplicar la promo dorada. Double dispatching.
     * @param contratacion : contratacion a la cual aplicar la promo dorada. <br>
     * <b> Pre: </b> contratacion no puede ser null.
     */
    public void promo(Contratacion contratacion){
        contratacion.aplicaPromocionDorada();
    }
    
}

