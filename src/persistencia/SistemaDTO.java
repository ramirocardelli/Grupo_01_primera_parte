package persistencia;

import java.io.Serializable;

import negocio.*;
/**
 * Clase que permite serializar un objeto que implementa el patron Singleton, en nuestro programa la clase
 * "Sistema".
 * 
 * @author Joaquin
 *
 */
public class SistemaDTO implements Serializable {
	private SubSistemaDatos datos;
	private SubSistemaTecnicos tecnicos;
	
	public SistemaDTO() {
		super();
	}

	public SubSistemaDatos getDatos() {
		return datos;
	}

	public void setDatos(SubSistemaDatos datos) {
		this.datos = datos;
	}

	public SubSistemaTecnicos getTecnicos() {
		return tecnicos;
	}

	public void setTecnicos(SubSistemaTecnicos tecnicos) {
		this.tecnicos = tecnicos;
	}
	
	
	
}
