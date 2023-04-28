package negocio;

public class PromoPlatino extends Promo{
    public PromoPlatino() {
        super();
    }
    
    public static void promo(Contratacion contratacion){
        contratacion.aplicaPromocionPlatino();
    }
}
