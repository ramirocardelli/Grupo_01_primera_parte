package negocio;

/** Clase que utiliza el patr�n Factory para crear un abonado segun su tipo de persona. 
 */
public class FactoryAbonado {

	/** Metodo para crear abonados dependiendo el tipo.
     * @param nombre : nombre del abonado.<br>
     * @param dni : dni del abonado.<br>
     * @param tipo : tipo de persona(juridica o fisica)<br>
     * @return Abonado segun el tipo.<br>
     * @throws TipoIncorrectoPersonaException cuando el tipo de abonado es incorrecto.<br>
     * <b> Pre: </b> nombre,dni y tipo no pueden ser vacios o nulos. <br>
     * <b> Post: </b> Se crea un abonado segun el tipo. 
     */
	public Abonado creaAbonado(String nombre,String dni,String tipo) throws TipoIncorrectoPersonaException {
    	assert nombre != null : "Nombre nulo";
    	assert nombre != "" : "Nombre nulo";
    	assert dni != null : "DNI nulo";
    	assert dni != "" : "DNI nulo";
    	assert tipo != null : "tipo nulo";
    	assert tipo != "" : "tipo nulo";
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
