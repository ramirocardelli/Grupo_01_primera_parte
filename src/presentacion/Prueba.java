package presentacion;

import negocio.AbonadoYaCargado;
import negocio.Domicilio;
import negocio.DomicilioSinContratacionException;
import negocio.DomicilioYaConContratacionExcepcion;
import negocio.MetodoDePagoInvalidoException;
import negocio.NoExisteFacturaException;
import negocio.Sistema;
import negocio.TipoFacturaIncorrecto;
import negocio.TipoIncorrectoPersonaException;
import negocio.TipoIncorrectoServicio;
import negocio.dniDesconocidoException;

	public class Prueba {
		public static void main(String[] args)  {
			Sistema sistema=Sistema.getInstance();
			System.out.println("Prueba Ingreso Abonados");
			ingresaAbonado("Nicolas","44667826","Fisica");
			ingresaAbonado("Nicolas","44667826","Fisica");
			ingresaAbonado("Mateo","44235283","Juridica");
			ingresaAbonado("Joaquin","3434534354","lol");
			ingresaAbonado("Joaquin","3434534354","Fisica");
			ingresaAbonado("Augusto","44231231","Juridica");
			

			System.out.println("\nPrueba Ingreso Facturas");
			ingresaFactura("44667826", "Efectivo", null);
			ingresaFactura("44667826", "Efectivo", null);
			ingresaFactura("44235283", "Efectivo", null);
			ingresaFactura("2", "Efectivo", null);
			ingresaFactura("44231231", "Uala", null);
			ingresaFactura("3434534354", "Tarjeta", "A");
			
			System.out.println("\nPrueba Ingreso Contrataciones");
			ingresaContratacion("44231231", 1, 3, false,new Domicilio("Moreno",2410), "Comercio");
			ingresaContratacion("44667826", 1, 3, false,new Domicilio("Moreno",2410), "lol");
			ingresaContratacion("44667826", 1, 3, false,new Domicilio("Moreno",2410), "Comercio");
			ingresaContratacion("44667826", 1, 3, false,new Domicilio("Moreno",2410), "Comercio");
			
			System.out.println("\nPrueba eliminar factura");
			eliminaFactura("44667826");
			ingresaFactura("44667826", "Efectivo", null);//se carga otra vez a la lista para verificar que se elimino
			eliminaFactura("3434534354");
			
			System.out.println("\nPrueba eliminar abonado sin contratacion");
			eliminaAbonadoSinContratacion("44667826");
			eliminaAbonadoSinContratacion("2");
			eliminaAbonadoSinContratacion("44231231");
			ingresaAbonado("Augusto","44231231","Juridica"); //se lo carga de nuevo para comprobar que si se elimino
			
			System.out.println("\nPrueba eliminar contrataciones");
			eliminaContratacionAbonado("44667826", new Domicilio("Alverar",3245));
			eliminaContratacionAbonado("2", new Domicilio("Moreno",2410));
			ingresaContratacion("44667826", 1, 3, false,new Domicilio("Moreno",2410), "Comercio");
			eliminaContratacionAbonado("44667826", new Domicilio("Moreno",2410));
			ingresaAbonado("Nicolas", "44667826", "Juridica");//como se elimino el unico servicio con el que contaba pasa a formar parte de la lista de abonados sin contratacion(por ello es que no puede agregarse a la lista)
		}
	
	
	private static void ingresaAbonado(String nombre,String dni, String tipo) {
		try {
			Sistema.getInstance().nuevoAbonado(nombre, dni, tipo);
		}
		catch(AbonadoYaCargado e){
			String print="El abonado "+e.getNombre()+" con dni "+e.getDni()+" ya se encuentra cargado";
			if(e.isFactura())
				print+=" con una factura a su nombre";
			else
				print+=" sin una factura a su nombre";
			System.out.println(print);
		}
		catch(TipoIncorrectoPersonaException e) {
			System.out.println("El tipo de persona ingresado ('"+e.getTipo()+"') no se reconoce como uno de los tipos permitidos en el sistema");
		}
	}
	
	private static void ingresaContratacion(String dni,int camaras, int botonesAntipanicos, boolean movilAcompanamiento, Domicilio domicilio, String tipo) {
		try {
			Sistema.getInstance().nuevaContratacion(dni, camaras, botonesAntipanicos, movilAcompanamiento, domicilio, tipo);
		}
		catch(DomicilioYaConContratacionExcepcion e) {
			System.out.println(e.getDomicilio().toString()+" ya se encuentra con una servicio contratado");
		}
		catch(NoExisteFacturaException e) { 
			System.out.println("No existe una factura para el dni ingresado('"+e.getdni()+"')");
		}
		catch(TipoIncorrectoServicio e){
			System.out.println("El tipo de servicio ingresado ('"+e.getTipo()+"') no coincide con ninguno de los disponibles");
		}
	}
	
	private static void ingresaFactura(String dni, String tipoPago,String tipoFactura) {
		try {
			Sistema.getInstance().nuevaFactura(dni, tipoPago, tipoFactura);
		}
		catch(MetodoDePagoInvalidoException e) {
			System.out.println("El metodo de pago ingresado ('"+e.getMetododePago()+"') no se encuentra disponible'");
		}
		catch(TipoFacturaIncorrecto e) {
			System.out.println("El tipo de factura ingresada ('"+e.getTipo()+"') no coincide con ninguna factura disponible");
		}
		catch(AbonadoYaCargado e) {
			System.out.println("El Abonado("+e.getNombre()+") asociado con el dni "+e.getDni()+" ya dispone de una factura");
		}
		catch(dniDesconocidoException e) {
			System.out.println("Ningun cliente registrado posee como dni "+e.getDni());
		}
	}
	
	private static void eliminaFactura(String dni) {
		try {
			Sistema.getInstance().eliminarFactura(dni);
		}
		catch(dniDesconocidoException e) {
			System.out.println("El dni ingresado: "+e.getDni()+" no se corresponde con ningun cliente asociado factura");
		}
	}
	
	private static void eliminaAbonadoSinContratacion(String dni) {
		try {
			Sistema.getInstance().eliminaAbonadoSinContratacion(dni);
		}
		catch(dniDesconocidoException e) {
			System.out.println("El dni ingresado: '"+e.getDni()+"' no se encuentra cargado como cliente sin contratacion");
		}
		catch(AbonadoYaCargado e) {
			System.out.println("El abonado ingresado ('"+e.getNombre()+"'/'"+e.getDni()+"') dispone de una contratacion y por lo tanto no puede ser eliminado");
		}
	}
	
	private static void eliminaContratacionAbonado(String dni,Domicilio domicilio) {
		try {
			Sistema.getInstance().eliminaContratacionAbonado(dni, domicilio);
		}
		catch(DomicilioSinContratacionException e) {
			System.out.println(e.getDomicilio()+" no cuenta con ninguna contratacion existente para el abonado "+e.getAbonado().getNombre()+" con dni "+e.getAbonado().getDni());
		}
		catch(dniDesconocidoException e){
			System.out.println("El dni ingresado: '"+e.getDni()+"' no puede indentificarse con ningun abonado asociado factura");
		}
	}
}
