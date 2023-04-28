package negocio;

public class PromoDorada extends Promo{
    public PromoDorada() {
        super();
    }
    
    public static void promo(Contratacion contratacion){
        contratacion.aplicaPromocionDorada();
    }
    
}

