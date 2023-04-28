package negocio;

public abstract class Contratacion { 
    protected int camaras;
    protected int botonesAntipanicos;
    protected boolean movilAcompanamiento;
    protected Domicilio domicilio; //NO SE PUEDE REPETIR
    protected int id;
    protected static int numeroId=0;
    protected double precio;

    protected static final double valorUnidadCamara = 3000;
    protected static final double valorUnidadBotAntipanico = 2000;
    protected static final double valorMovilAcompanamiento = 7500;
                
    public Contratacion(int camaras, int botonesAntipanicos, boolean movilAcompanamiento, Domicilio domicilio) {
        super();
        numeroId++;
        this.id=numeroId;
        this.camaras = camaras;
        this.botonesAntipanicos = botonesAntipanicos;
        this.movilAcompanamiento = movilAcompanamiento;
        this.domicilio = domicilio;
    }


    public int getCamaras() {
        return camaras;
    }

    public int getBotonesAntipanicos() {
        return botonesAntipanicos;
    }

    public boolean isMovilAcompanamiento() {
        return movilAcompanamiento;
    }
    
    public void promo(Promo promo){
        promo.promo(this);
    }
    
    public double getPrecio() {
		return precio;
	}

	public abstract void aplicaPromocionPlatino();
    
    public abstract void aplicaPromocionDorada();
}

