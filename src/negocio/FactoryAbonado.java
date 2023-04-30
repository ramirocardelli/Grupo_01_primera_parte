package negocio;

/** Clase que utiliza el patrón Factory para crear un abonado segun su tipo de persona. 
 */
public class FactoryAbonado {
	
	public Abonado creaAbonado(String nombre,String dni,String tipo) throws TipoIncorrectoPersonaException {
		Abonado creado=null;
		if(tipo.equals("Juridica")) 
			creado=new PersonaJuridica(nombre, dni);
		else if(tipo.equals("Fisica"))
			creado=new PersonaFisica(nombre, dni);
		else
			throw new TipoIncorrectoPersonaException(nombre, dni, tipo);
		
		return creado;
	}
}
