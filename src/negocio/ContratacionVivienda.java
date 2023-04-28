package negocio;

public class ContratacionVivienda extends Contratacion {
    private static final double valorVivienda=8500;
   
    public ContratacionVivienda(int camaras, int botonesAntipanico, boolean movilAcompanamiento, Domicilio domicilio) {
        super(camaras, botonesAntipanico, movilAcompanamiento, domicilio);
        this.precio = 0;
    }
    
    public double calculaPrecio(int camaras, int botonesAntipanico, boolean movilAcompanamiento){
    	double res = 0;
    	res = valorVivienda;
    	res += camaras*valorUnidadCamara;
    	res += botonesAntipanico*valorUnidadBotAntipanico;
    	if(movilAcompanamiento)
    		res += valorMovilAcompanamiento;
    	
    	return res;
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