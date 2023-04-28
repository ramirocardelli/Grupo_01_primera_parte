package negocio;

public class PromoPlatino extends Promo{
    public PromoPlatino() {
        super();
    }
    
    public void getPromocion(Contratacion contratacion){
        contratacion.aplicaPromocionPlatino();
    }
}
