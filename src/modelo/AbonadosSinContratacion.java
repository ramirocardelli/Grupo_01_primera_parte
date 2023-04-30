package modelo;

import java.util.ArrayList;
import java.util.Iterator;

import negocio.Abonado;

/** Clase que representa la base de datos que contiene la informacion de los clientes sin contrataciones aun.
 */
public class AbonadosSinContratacion {
    public ArrayList<Abonado> Abonados=new ArrayList<Abonado>();
    
    public AbonadosSinContratacion() {
        super();
    }

    /** Metodo que retorna un Iterator de la lista de abonados sin contratacion.
     * @return Iterator de la lista de abonados sin contrataciones.
     */
    public Iterator<Abonado> getAbonados() {
		return Abonados.iterator();
	}

    /** Metodo que agrega un nuevo abonado a la lista. // si ya esta en la lista que hacemo
     * @param abonado : abonado a agregar. <br>
     * <b> Pre: </b> Abonado no puede ser null.
     */
    public void agregaAbonado(Abonado abonado) {
            this.Abonados.add(abonado);
    }

    /** Metodo que elimina un abonado de la lista.
     * @param i : posicion de la lista en la que se encuentra el abonado que se quiere eliminar. <br>
     * <b> Pre: </b> i no puede ser null ni menor a 0.
     */
    public void eliminaAbonado(int i) {
            this.Abonados.remove(i);
    }

    /** Metodo sobrescrito toString que muestra en pantalla todos los abonados con su informacion.
     * @return String con todos los abonados y su informacion.
     */
    @Override
    public String toString() {
            return "AbonadosSinContratacion [Abonados=" + Abonados + "]"; //NO HECHO
    }
}
//prueba commit - comentario jo