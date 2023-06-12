package modelo;

import java.util.ArrayList;
import java.util.Iterator;

import negocio.Abonado;

/** Clase que representa una lista de abonados sin haber contratado ningun servicio.
 */
public class AbonadosSinContratacion {
    public ArrayList<Abonado> Abonados=new ArrayList<Abonado>();
    
    public AbonadosSinContratacion() {
        super();
    }

    /** Metodo que retorna un Iterator para recorrer la lista de abonados.
     * @return Iterator de la lista de abonados.
     */
    public Iterator<Abonado> getAbonados() {
		return Abonados.iterator();
    }

    /** Metodo para agregar un abonado a la lista de abonados.
     * @param abonado : abonado que se desea agregar a la lista. <br>
     * <b> Pre: </b> abonado no puede ser null, ni tampoco puede ya encontrarse en la lista. <br>
     */
    public void agregaAbonado(Abonado abonado) {
    	assert abonado != null : "Abonado nulo";
		this.Abonados.add(abonado);
    }

    /** Metodo para eliminar un abonado de la lista.
     * @param abonado : abonado que se desea eliminar de la lista.
     * @return boolean para informar si se pudo eliminar el abonado.
     * <b> Pre: </b> abonado no puede ser null. <br>
     */
    public boolean eliminaAbonado(Abonado abonado) {
    	assert abonado != null : "Abonado nulo";
		return this.Abonados.remove(abonado);
    }
}