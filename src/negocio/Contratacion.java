package negocio;


/**
 *Clase que representa una contratacion de un cliente con los servicios correspondientes.
 */
public abstract class Contratacion implements Cloneable{ 
    protected int camaras;
    protected int botonesAntipanicos;
    protected boolean movilAcompanamiento;
    protected Domicilio domicilio; //NO SE PUEDE REPETIR
    protected int id;
    protected static int numeroId=0;
    protected double precio;
    protected int promocion=0; //evita que se apliquen mas de 2 promociones simultaneamente (0=no/1=dorada/2=platino)

    protected static final double valorUnidadCamara = 3000;
    protected static final double valorUnidadBotAntipanico = 2000;
    protected static final double valorMovilAcompanamiento = 7500;

    /**
     * Constructor con 4 parametros (2 int, 1 boolean y 1 Domicilio) para crear una nueva contratacion.
     * @param camaras Cantidad de camaras que se contratan.
     * @param botonesAntipanicos Cantidad de botones antipanico que se contratan.
     * @param movilAcompanamiento Se determina si o no si se adquiere el movil de acompanamiento.
     * @param domicilio Domicilio del abonado, quien hara la contratacion. <br>
     * <b> Pre: </b> camaras y botonesAntipanicos no deben ser menores a 0, movilAcompanamiento no puede ser null, Domicilio no puede ser null.<br>
     * <b> Post: </b> Se crea una nueva contratacion.
     * 
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

    /** Metodo para calcular el precio de una contratcion.
     * @return double con el valor del precio sin contar el costo de contratacion base dependiendo si es comercio o vivienda.
     */
    protected double calculaPrecio(){ 
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

    /** Metodo para aplicar una determinada promocion (platino/dorada) al servicio correspondiente. (Double dispatching)
     * @param tipoPromo : tipo de promo que se quiera aplicar
     * <b>Pre: </b> el tipo de promo no puede ser nulo <br> 
     */
    public void promo(Promo tipoPromo){
    	assert tipoPromo != null : "Tipo promo nulo";
        tipoPromo.promo(this);
    }
    
    public double getPrecio() {
        return precio;
	}
    
    public Domicilio getDomicilio() {
		return domicilio;
	}    
    

    /** Se aplica la promocion platino a la contratacion correspondiente.
     */
    public abstract void aplicaPromocionPlatino();

    /** Se aplica la promocion dorada a la contratacion correspondiente. 
     */
    public abstract void aplicaPromocionDorada();
    
    public abstract void actualizaContratacion(int camaras, int botonesAntipanicos, int movilAcompanamiento);
    
    public abstract String toString();
    
    public Object clone() throws CloneNotSupportedException {
    	Contratacion clon=(Contratacion)super.clone();
    	this.domicilio=(Domicilio)this.domicilio.clone();
    return clon;
    }
}

