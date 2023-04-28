package negocio;

public class PromoDorada extends Promo{
    public PromoDorada() {
        super();
    }
    
    public void movilAcompanamiento(Contratacion contratacion){
        contratacion.aplicaPromocionDorada();
    }
    
}

