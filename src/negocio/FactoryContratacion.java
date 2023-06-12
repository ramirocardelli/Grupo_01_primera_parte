package negocio;

/** Clase que utiliza el patr�n Factory para crear una contratacion segun su tipo (de vivienda o de comercio). 
 */
public class FactoryContratacion {
	
	/** Metodo para crear contrataciones dependiendo el tipo.
     * @param camaras : cantidad de camaras contratadas.<br>
     * @param botonesAntipanicos : cantidad de botones contratados.<br>
     * @param movilAcompanamiento : booleano que indica si se contrata o no un movil de acompanamiento<br>
     * @param domicilio : domicilio que contrata el servicio<br>
     * @param tipo : tipo de contratacion<br>
     * @return Contratacion segun el tipo.<br>
     * @throws TipoIncorrectoServicioException cuando el tipo de contratacion es incorrecto.<br>
     * <b> Pre: </b> camaras y botones no pueden ser negativos, domicilio no puede ser null y tipo no puede ser vacio o nulo. <br>
     * <b> Post: </b> Se crea una contratacion segun el tipo. 
     */
	public Contratacion creaContratacion(int camaras, int botonesAntipanicos, boolean movilAcompanamiento, Domicilio domicilio, String tipo) throws TipoIncorrectoServicioException {
    	assert camaras < 0 : "Camaras negativas";
    	assert botonesAntipanicos < 0 : "Botones antipanico negativos";
    	assert domicilio != null : "Domicilio nulo";
    	assert tipo != null : "Tipo nulo";
    	assert tipo != "" : "Tipo vacio";
		Contratacion creado = null;
		if(tipo.equals("Vivienda")) 
			creado=new ContratacionVivienda(camaras, botonesAntipanicos, movilAcompanamiento, domicilio);
		else if(tipo.equals("Comercio"))
			creado=new ContratacionComercio(camaras, botonesAntipanicos, movilAcompanamiento, domicilio);
		else
			throw new TipoIncorrectoServicioException(camaras,botonesAntipanicos,movilAcompanamiento,domicilio,tipo);
		
		return creado;
	}
	
}
