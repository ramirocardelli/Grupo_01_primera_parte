package modelo;

import java.io.*;
import java.util.ArrayList;

import negocio.*;

public class DAObject implements Serializable{
	public ArrayList<Abonado> listaAbonados;

	public DAObject(ArrayList<Abonado> listaAbonados) {
		super();
		this.listaAbonados = listaAbonados;
	}	
}
