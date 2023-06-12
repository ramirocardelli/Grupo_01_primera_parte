package persistencia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Persistencia implements IPersistencia
{

    private FileOutputStream fileoutput;
    private FileInputStream fileinput;
    private ObjectOutputStream objectoutput;
    private ObjectInputStream objectinput;

    public void abrirInput(String nombre) 
    {
        try {
			fileinput = new FileInputStream(nombre);
			objectinput = new ObjectInputStream(fileinput);
		} catch (IOException e) {
		}
    }

    public void abrirOutput(String nombre)
    {
        try {
        	fileoutput = new FileOutputStream(nombre);
			objectoutput = new ObjectOutputStream(fileoutput);
		} catch (IOException e) {
			
		}

    }

    public void cerrarOutput() 
    {
        if (objectoutput != null)
			try {
				objectoutput.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    }

    public void cerrarInput()
    {
        if (objectinput != null)
			try {
				objectinput.close();
			} catch (IOException e) {
			}

    }


    public void escribir(Serializable p) 
    {
        if (objectoutput != null)
			try {
				objectoutput.writeObject(p);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }

    public Serializable leer() 
    {
        Serializable p = null;
        if (objectinput != null)
			try {
				p = (Serializable) objectinput.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        return p;
    }

}
