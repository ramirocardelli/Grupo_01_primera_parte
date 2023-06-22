package negocio;

/**
 * 
 * Clase auxiliar para agregar tecnicos de forma concurrente
 * @author Joaquin
 *
 */
public class AgregaTecnico extends Thread{
	private String nombre;
	private SubSistemaTecnicos tecnicos;
	
	
	public AgregaTecnico(String nombre, SubSistemaTecnicos tecnicos) {
		super();
		this.nombre = nombre;
		this.tecnicos = tecnicos;
	}


	@Override
	public void run() {
		super.run();
		this.tecnicos.agregarTecnico(new Tecnico(nombre));
		
		
	}
	
	
	
	
	
}
