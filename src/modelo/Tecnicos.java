package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import negocio.Abonado;
import negocio.Tecnico;

/** Clase que representa una lista de tecnicos.
 */
public class Tecnicos implements Serializable {
    public ArrayList<Tecnico> Tecnicos=new ArrayList<Tecnico>();
    
    public Tecnicos() {
        super();
    }

    /** Metodo que retorna un Iterator para recorrer la lista de tecnicos.
     * @return Iterator de la lista de tecnicos.
     */
    public Iterator<Tecnico> getTecnicos() {
		return Tecnicos.iterator();
    }

    /** Metodo para dar de alta un tecnico (agregarlo a la lista).
     * @param tecnico : tecnico que se desea agregar a la lista. <br>
     * <b> Pre: </b> tecnico no puede ser null, ni tampoco puede ya encontrarse en la lista.
     */
    public void agregaTecnico(Tecnico tecnico) {
		this.Tecnicos.add(tecnico);
    }
    
}

