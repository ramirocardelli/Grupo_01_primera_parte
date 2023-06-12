package persistencia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import negocio.Sistema;

public class Persistencia implements IPersistencia
{

    private FileOutputStream fileoutput;
    private FileInputStream fileinput;
    private ObjectOutputStream objectoutput;
    private ObjectInputStream objectinput;

    public void abrirInput(String nombre) throws IOException
    {
        fileinput = new FileInputStream(nombre);
        objectinput = new ObjectInputStream(fileinput);

    }

    public void abrirOutput(String nombre) throws IOException
    {
        fileoutput = new FileOutputStream(nombre);
        objectoutput = new ObjectOutputStream(fileoutput);

    }

    public void cerrarOutput() throws IOException
    {
        if (objectoutput != null)
            objectoutput.close();

    }

    public void cerrarInput() throws IOException
    {
        if (objectinput != null)
            objectinput.close();

    }


    public void escribir(Serializable p) throws IOException
    {
        if (objectoutput != null)
            objectoutput.writeObject(p);
    }

    public Serializable leer() throws IOException, ClassNotFoundException
    {
        Serializable p = null;
        if (objectinput != null)
            p = (Serializable) objectinput.readObject();
        return p;
    }
    
    /**
     * Funcion que permite crear un DTO de la clase sistema.
     * Pre: sistema!=null
     * Post: Objeto de tipo DTO emparentado con la clase Sistema
     * @param sistema
     * @return
     */
    public SistemaDTO sistemaAsistemaDTO(Sistema sistema) {
    	SistemaDTO nuevoSistemaDTO=new SistemaDTO();
    	nuevoSistemaDTO.setDatos(sistema.getDatos());
    	nuevoSistemaDTO.setTecnicos(sistema.getTecnicos());
    	return nuevoSistemaDTO;
    }
    
    /**
     * Funcion que inicializa la clase Sistema
     * Pre: sistemaDTO!=null
     * Post: Clase Sistema incializada
     * @param sistemaDTO
     */
    public void inicializaSistemaConDTO(SistemaDTO sistemaDTO) {
    	Sistema nuevoSistema=Sistema.getInstance();
    	nuevoSistema.setDatos(sistemaDTO.getDatos());
    	nuevoSistema.setTecnicos(sistemaDTO.getTecnicos());    	
    }

}
