package negocio;

import java.io.Serializable;
/**
 * Clase simple que implementa los Tecnicos usados por la clase SubSistemaTecnicos.
 * @author Joaquin
 */
public class Tecnico implements Serializable{
	protected String nombre;
	protected boolean atendiendo;

	public Tecnico(String nombre) {
		super();
		this.nombre = nombre;
	}

	@Override
	public boolean equals(Object obj) {
		Tecnico tecnico;
		if (obj instanceof Tecnico) {
			tecnico=(Tecnico) obj;
			return this.nombre.equalsIgnoreCase(tecnico.nombre);
		}
		return false;
	}
	
}
