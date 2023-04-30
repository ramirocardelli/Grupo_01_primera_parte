package presentacion;

import negocio.*;

	public class Prueba {
		public static void main(String[] args)  {
			Sistema sistema=Sistema.getInstance();
			
			System.out.println("Prueba Ingreso Abonados");
			ingresaAbonado("Mateo","44235283","Juridica");//cargas correcta
			ingresaAbonado("Joaquin","3434534354","Fisica");
			ingresaAbonado("Augusto","44231231","Juridica");
			ingresaAbonado("Nicolas","44667826","Fisica");
			//errores
			ingresaAbonado("Nicolas","44667826","Fisica");//carga por 2da vez el abonado
			ingresaAbonado("Joaquin","3434534354","lol");//carga con tipo desconocido
			
			
			System.out.println("\nPrueba Ingreso Facturas");
			ingresaFactura("44667826", "Efectivo", null); //ingresa la factura de Nicolas
			ingresaFactura("44235283", "Tarjeta", null);//ingresa la facutura de Mateo
			//errores
			ingresaFactura("44667826", "Efectivo", null);//ingresa por 2da vez la factura de nicolas
			ingresaFactura("2", "Efectivo", null);	//ingresa dni no cargado como cliente
			ingresaFactura("44231231", "Uala", null); //ingresa metodo de pago incorrecto
			ingresaFactura("3434534354", "Tarjeta", "A");	//ingresa tipo de factura incorrecto
			
			
			System.out.println("\nPrueba Ingreso Contrataciones");
			ingresaContratacion("44667826", 1, 3, false,new Domicilio("Moreno",2410), "Comercio");//carga correcta
			ingresaContratacion("44235283", 1, 3, false,new Domicilio("Garay",4504), "Comercio");
			
			ingresaContratacion("44231231", 1, 3, false,new Domicilio("Moreno",2410), "Comercio");//no existe factura para el dni
			ingresaContratacion("44667826", 1, 3, false,new Domicilio("Moreno",2410), "lol");//tipo incorrecto de contratacion
			ingresaContratacion("44235283", 1, 3, false,new Domicilio("Moreno",2410), "Comercio");//ingresa 2 veces el mismo domicilio para contratacion
			
			
			System.out.println("\nPrueba eliminar factura");
			eliminaFactura("44667826");//eliminacion correcta
			ingresaFactura("44667826", "Efectivo", null);//se carga otra vez a la lista para verificar que se elimino
			ingresaFactura("44667826", "Efectivo", null);//como se carga 2 veces error xq ya esta en factura
			
			eliminaFactura("3434534354"); //no existe este dni con factura
			
			
			System.out.println("\nPrueba eliminar abonado sin contratacion");
			eliminaAbonadoSinContratacion("44231231"); //correcta eliminacion
			ingresaAbonado("Augusto","44231231","Juridica"); //se lo carga de nuevo para comprobar que si se elimino
			ingresaAbonado("Augusto","44231231","Juridica"); //se carga por 2da vez y tira error xq ya esta cargado
			
			eliminaAbonadoSinContratacion("44667826"); //abonado que tiene una factura vigente
			eliminaAbonadoSinContratacion("2"); //abonado no existente
			
			
			System.out.println("\nPrueba eliminar contrataciones");
			ingresaContratacion("44667826", 1, 3, false,new Domicilio("Moreno",2410), "Comercio");
			eliminaContratacionAbonado("44667826",  new Domicilio("Moreno",2410));
			ingresaAbonado("Nicolas", "44667826", "Fisica");//como se elimino la factura por no tener mas contrataciones, entonces pasa a formar parte de la lista de abonados
			
			ingresaContratacion("44235283", 1, 3, false,new Domicilio("Roca",1131), "Comercio");
			ingresaContratacion("44235283", 1, 3, false,new Domicilio("Jujuy",6231), "Vivienda");
			ingresaContratacion("44235283", 1, 3, false,new Domicilio("Roca",1131), "Comercio");
			ingresaFactura("44235283", "Efectivo", null); //como todavia cuenta con un servicio, su factura no se elimina y por ende no puede agregarse una nueva a su nombre
			
			eliminaContratacionAbonado("44667826", new Domicilio("Moreno",2410)); //intenta eliminar una contratacion inexistente en el abonado Nicolas
			eliminaContratacionAbonado("44235283", new Domicilio("Alverar",3245)); //intenta eliminar contratacion no existente
			
			
			System.out.println("\nPrueba promociones y Factura a pagar");
			aplicaPromocion(new Domicilio("Santa Fe",2410), new PromoDorada());
			FacturaAPagar("44667826");
			
			ingresaFactura("3434534354", "Efectivo", null);
			ingresaContratacion("3434534354", 3, 3, true,new Domicilio("Tucuman",2312), "Vivienda");
			aplicaPromocion(new Domicilio("Tucuman",2312), new PromoDorada());
			FacturaAPagar("3434534354");
			eliminaContratacionAbonado("44667826", new Domicilio("Moreno",2410));
			ingresaAbonado("Nicolas", "44667826", "Juridica");//como se elimino el unico servicio con el que contaba pasa a formar parte de la lista de abonados sin contratacion(por ello es que no puede agregarse a la lista)
			sistema.MuestraEstado();
			
			eliminaFactura("44235283");
			ingresaFactura("44235283", "Tarjeta", null);
			ingresaContratacion("44235283", 3, 3, true, new Domicilio("Roca",1234), "Vivienda");//31000-1500
			ingresaContratacion("44235283", 3, 3, true, new Domicilio("Alvear",2464), "Vivienda");//31000*0.70
			ingresaContratacion("44235283", 3, 3, true, new Domicilio("Tucuman",5642), "Comercio");//32500-2500
			ingresaContratacion("44235283", 3, 3, true, new Domicilio("Jujuy",8442), "Comercio");//32500*0.5*0.65
			aplicaPromocion(new Domicilio("Tucuman",5642), new PromoDorada());
			aplicaPromocion(new Domicilio("Roca",1234), new PromoDorada());
			aplicaPromocion(new Domicilio("Jujuy",8442), new PromoPlatino());
			aplicaPromocion(new Domicilio("Alvear",2464), new PromoPlatino());
			FacturaAPagar("44235283");
			
			ingresaFactura("44231231", "Cheque", null);
			ingresaContratacion("44231231", 3, 3, true, new Domicilio("Arenales",7236), "Vivienda");//31000
			FacturaAPagar("44231231");
		}
	
	
	private static void ingresaAbonado(String nombre,String dni, String tipo) {
		try {
			Sistema.getInstance().nuevoAbonado(nombre, dni, tipo);
		}
		catch(AbonadoYaCargadoException e){
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
		catch(TipoIncorrectoServicioException e){
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
		catch(AbonadoYaCargadoException e) {
			System.out.println("El Abonado '"+e.getNombre()+"' asociado con el dni "+e.getDni()+" ya dispone de una factura");
		}
		catch(DniDesconocidoException e) {
			System.out.println("Ningun cliente registrado posee como dni "+e.getDni());
		}
	}
	
	private static void eliminaFactura(String dni) {
		try {
			Sistema.getInstance().eliminarFactura(dni);
		}
		catch(DniDesconocidoException e) {
			System.out.println("El dni ingresado: "+e.getDni()+" no se corresponde con ningun cliente asociado factura");
		}
	}
	
	private static void eliminaAbonadoSinContratacion(String dni) {
		try {
			Sistema.getInstance().eliminaAbonadoSinContratacion(dni);
		}
		catch(DniDesconocidoException e) {
			System.out.println("El dni ingresado: '"+e.getDni()+"' no se encuentra cargado como cliente sin contratacion");
		}
		catch(AbonadoYaCargadoException e) {
			System.out.println("El abonado ingresado ('"+e.getNombre()+"'/'"+e.getDni()+"') dispone de una contratacion y por lo tanto no puede ser eliminado");
		}
	}
	
	private static void eliminaContratacionAbonado(String dni,Domicilio domicilio) {
		try {
			Sistema.getInstance().eliminaContratacionAbonado(dni, domicilio);
		}
		catch(DomicilioSinContratacionEnAbonadoException e){
			System.out.println(e.getDomicilio()+" no cuenta con ninguna contratacion existente para el abonado "+e.getAbonado().getNombre()+" con dni "+e.getAbonado().getDni());
		}
		catch(DniDesconocidoException e){
			System.out.println("El dni ingresado: '"+e.getDni()+"' no puede indentificarse con ningun abonado asociado factura");
		}
	}
	
	private static void aplicaPromocion(Domicilio domicilio, Promo promocion) {
		try {
			Sistema.getInstance().aplicaPromocion(domicilio, promocion);
		}
		catch(DomicilioSinContratacionException e) {
			System.out.println(e.getDomicilio()+" no se encuentra con una contratacion vigente");
		}
	}
	
	private static void FacturaAPagar(String dni) {
		try {
			double precio= Sistema.getInstance().calculaPrecioAPagar(dni);
			System.out.println("La factura a pagar por "+dni+" es de: $"+precio);
		}
		catch(DniDesconocidoException e) {
			System.out.println("El dni '"+e.getDni()+"' no dispone de factura");
		}
	}
	
}
