package negocio;

public class PromoDorada extends Promo{
    public PromoDorada() {
        super();
    }
    
    public void promo(Contratacion contratacion){
        contratacion.aplicaPromocionDorada();
    }
    
}

