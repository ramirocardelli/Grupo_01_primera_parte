package negocio;

import java.util.ArrayList;

public abstract class Abonado {  //hacer como socio
    private ArrayList<Domicilio> Domicilios;
    private String nombre;
    private String dni;
    
    public Abonado() {
        super();
    }
    
    public abstract double calcularTotal();
}