package persistencia;

import negocio.Sistema;

public class UtilPersistencia {
	
	
	 /**
     * Funcion que permite crear un DTO de la clase sistema.
     * Pre: sistema!=null
     * Post: Objeto de tipo DTO emparentado con la clase Sistema
     * @param sistema
     * @return
     */
	public static SistemaDTO sistemaDTOFromSistema(Sistema sistema) {
		SistemaDTO sistemaDTO= new SistemaDTO();
		sistemaDTO.setDatos(sistema.getDatos());
		sistemaDTO.setTecnicos(sistema.getTecnicos());
		return null;
	}
	/**
     * Funcion que inicializa la clase Sistema
     * Pre: sistemaDTO!=null
     * Post: Clase Sistema incializada
     * @param sistemaDTO
     */
	public static void sistemaFromSistemaDTO (SistemaDTO sistemaDTO) {
		Sistema.getInstance().setDatos(sistemaDTO.getDatos());
		Sistema.getInstance().setTecnicos(sistemaDTO.getTecnicos());
	}
}
