package negocio;

import java.io.Serializable;
/**
 * Clase simple que implementa los Tecnicos usados por la clase SubSistemaTecnicos.
 * @author Joaquin
 */
public class Tecnico implements Serializable{
	protected String nombre;
	protected transient boolean atendiendo=false;

	public Tecnico(String nombre) {
		super();
    	assert nombre != null: "Nombre nulo";
    	assert nombre != "": "Nombre vacio";
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
	
	
	
	public String getNombre() {
		return nombre;
	}

	public boolean isAtendiendo() {
		return atendiendo;
	}

	public void setAtendiendo(boolean atendiendo) {
		this.atendiendo = atendiendo;
	}

	@Override
	public int hashCode() {
		int hash=1;
		hash=hash*12+this.nombre.hashCode();
		return 1;
	}
}
