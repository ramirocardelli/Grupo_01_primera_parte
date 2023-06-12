package persistencia;

import negocio.*;

public class SistemaDTO {
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
