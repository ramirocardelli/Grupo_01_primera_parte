package negocio;

public class FactoryContratacion {
	
	public Contratacion creaContratacion(int camaras, int botonesAntipanicos, boolean movilAcompanamiento, Domicilio domicilio, String tipo) throws TipoIncorrectoServicioException {
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
