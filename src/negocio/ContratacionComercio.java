package negocio;

public class ContratacionComercio extends Contratacion {
    private double precio; //E
    private static final double valorComercio=10000;
   
    public ContratacionComercio(int camaras, int botonesAntipanico, boolean movilAcompanamiento, Domicilio domicilio){
        super(camaras, botonesAntipanico, movilAcompanamiento, domicilio);
        this.precio=calculaPrecio(camaras,botonesAntipanico,movilAcompanamiento);
    }
    
    
    public double calculaPrecio(int camaras, int botonesAntipanico, boolean movilAcompanamiento){
        return (valorComercio+camaras*valorunidadCamara+botonesAntipanico*valorunidadBotAntipanico+((movilAcompanamiento)?valorMovilAcompanamiento:0));
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