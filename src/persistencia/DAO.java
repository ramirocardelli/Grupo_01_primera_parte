package persistencia;

import java.io.IOException;

import negocio.Sistema;

public class DAO {
	private Persistencia dataAccessObject=new Persistencia();
	private String DataSource;
	//la referencia al BusinessObject la obtiene a traves de forma estatica por ser singleton
	
	public DAO(String dataSource) {
		super();
		DataSource = dataSource;
	}
	
	public void persistir () throws IOException{
		dataAccessObject.abrirOutput(DataSource);
		SistemaDTO sistemaDTO= UtilPersistencia.sistemaDTOFromSistema(Sistema.getInstance());
		dataAccessObject.escribir(sistemaDTO);
		dataAccessObject.cerrarOutput();
	}
	
	

	public void despersistir() throws IOException{
		dataAccessObject.abrirInput(DataSource);
		SistemaDTO sistemaDTO= (SistemaDTO)dataAccessObject.leer();
		UtilPersistencia.sistemaFromSistemaDTO(sistemaDTO);
		dataAccessObject.cerrarInput();
	}
}
