package negocio;

import java.io.Serializable;

/** Clase que representa una promo que ocasionalmente brinda el sistema.
 */
public abstract class Promo {
    public Promo() {
        super();
    }
    
    public abstract void promo(Contratacion contratacion);
}
