package negocio;

public class TipoIncorrectoServicio extends Exception {
	private static final long serialVersionUID = 1L;
	private int camaras,botonesAntipanicos;
	private boolean movilAcompanamiento;
	private Domicilio domicilio;
	private String tipo;
	
	public TipoIncorrectoServicio (int camaras, int botonesAntipanicos, boolean movilAcompanamiento, Domicilio domicilio, String tipo) {
		this.camaras=camaras;
		this.botonesAntipanicos=botonesAntipanicos;
		this.domicilio=domicilio;
		this.tipo=tipo;
		this.movilAcompanamiento=movilAcompanamiento;
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

	public Domicilio getDomicilio() {
		return domicilio;
	}

	public String getTipo() {
		return tipo;
	}
}
