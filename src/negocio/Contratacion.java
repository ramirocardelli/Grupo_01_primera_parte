package negocio;


/**
 *Clase que representa una contratacion de un cliente con los servicios correspondientes.
 */
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

    /**
     * Constructor con 4 parametros (2 int, 1 boolean y 1 Domicilio) para crear una nueva contratacion.
     * @param camaras Cantidad de camaras que se contratan
     * @param botonesAntipanicos Cantidad de botones antipanico que se contratan
     * @param movilAcompanamiento Se determina si o no si se adquiere el movil de acompanamiento
     * @param domicilio Domicilio del abonado, quien hara la contratacion
     */
    public Contratacion(int camaras, int botonesAntipanicos, boolean movilAcompanamiento, Domicilio domicilio) {
        super();
        numeroId++;
        this.id=numeroId;
        this.camaras = camaras;
        this.botonesAntipanicos = botonesAntipanicos;
        this.movilAcompanamiento = movilAcompanamiento;
        this.domicilio = domicilio;
    }

    public double calculaPrecio(){ 
        double res = 0;
        res += this.camaras*valorUnidadCamara;
        res += this.botonesAntipanicos*valorUnidadBotAntipanico;
        if(this.movilAcompanamiento)
                res += valorMovilAcompanamiento;
        return res;
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

    /** Se aplica la promocion platino a la contratacion correspondiente.
     */
    public abstract void aplicaPromocionPlatino();

    /** Se aplica la promocion dorada a la contratacion correspondiente. 
     */
    public abstract void aplicaPromocionDorada();
}

