package negocio;

public class PromoPlatino extends Promo{
    public PromoPlatino() {
        super();
    }
    
    public void promo(Contratacion contratacion){
        contratacion.aplicaPromocionPlatino();
    }
}
