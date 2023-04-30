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
			ingresaAbonado("Mariana","3215123","Astronauta");//carga con tipo desconocido
			
			
			System.out.println("\nPrueba Ingreso Facturas");
			ingresaFactura("44667826", "Efectivo", null); //ingresa la factura de Nicolas
			ingresaFactura("44235283", "Tarjeta", null);//ingresa la facutura de Mateo
			//errores
			ingresaFactura("44667826", "Efectivo", null);//ingresa por 2da vez la factura de nicolas
			ingresaFactura("2", "Efectivo", null);	//ingresa dni no cargado como cliente
			ingresaFactura("44231231", "Uala", null); //ingresa metodo de pago incorrecto
			ingresaFactura("3434534354", "Tarjeta", "A");	//ingresa tipo de factura incorrecto
			
			
			System.out.println("\nPrueba Ingreso Contrataciones");
			ingresaContratacion("44667826", 1, 3, false,Domicilio.generaDomicilio("Moreno",2410), "Comercio");//carga correcta
			ingresaContratacion("44235283", 1, 3, false,Domicilio.generaDomicilio("Garay",4504), "Comercio");
			
			ingresaContratacion("44231231", 1, 3, false,Domicilio.generaDomicilio("Moreno",2410), "Comercio");//no existe factura para el dni
			ingresaContratacion("44667826", 1, 3, false,Domicilio.generaDomicilio("Moreno",2410), "lol");//tipo incorrecto de contratacion
			ingresaContratacion("44235283", 1, 3, false,Domicilio.generaDomicilio("Moreno",2410), "Comercio");//ingresa 2 veces el mismo domicilio para contratacion
			
			
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
			ingresaContratacion("44667826", 1, 3, false,Domicilio.generaDomicilio("Moreno",2410), "Comercio");
			eliminaContratacionAbonado("44667826", Domicilio.generaDomicilio("Moreno",2410));
			ingresaAbonado("Nicolas", "44667826", "Fisica");//como se elimino la factura por no tener mas contrataciones, entonces pasa a formar parte de la lista de abonados
			
			ingresaContratacion("44235283", 1, 3, false,Domicilio.generaDomicilio("Roca",1131), "Comercio");
			ingresaContratacion("44235283", 1, 3, false,Domicilio.generaDomicilio("Jujuy",6231), "Vivienda");
			ingresaContratacion("44235283", 1, 3, false,Domicilio.generaDomicilio("Roca",1131), "Comercio");
			ingresaFactura("44235283", "Efectivo", null); //como todavia cuenta con un servicio, su factura no se elimina y por ende no puede agregarse una nueva a su nombre
			
			eliminaContratacionAbonado("44667826", Domicilio.generaDomicilio("Moreno",2410)); //intenta eliminar una contratacion inexistente en el abonado Nicolas
			eliminaContratacionAbonado("44235283", Domicilio.generaDomicilio("Alverar",3245)); //intenta eliminar contratacion no existente
			
			
			System.out.println("\nPrueba promociones y Factura a pagar");
			aplicaPromocion(Domicilio.generaDomicilio("Santa Fe",2410), new PromoDorada());
			FacturaAPagar("44667826",false);
			FacturaAPagar("44667826",true);
			
			ingresaFactura("3434534354", "Efectivo", null);
			ingresaContratacion("3434534354", 3, 3, true,Domicilio.generaDomicilio("Tucuman",2312), "Vivienda");
			aplicaPromocion(Domicilio.generaDomicilio("Tucuman",2312), new PromoDorada());
			FacturaAPagar("3434534354",true);
			FacturaAPagar("3434534354",false);
			
			eliminaFactura("44235283");
			ingresaFactura("44235283", "Tarjeta", null);
			ingresaContratacion("44235283", 3, 3, true, Domicilio.generaDomicilio("Roca",1234), "Vivienda");//31000-1500
			ingresaContratacion("44235283", 3, 3, true, Domicilio.generaDomicilio("Alvear",2464), "Vivienda");//31000*0.70
			ingresaContratacion("44235283", 3, 3, true, Domicilio.generaDomicilio("Tucuman",5642), "Comercio");//32500-2500
			ingresaContratacion("44235283", 3, 3, true, Domicilio.generaDomicilio("Jujuy",8442), "Comercio");//32500*0.5*0.65
			aplicaPromocion(Domicilio.generaDomicilio("Tucuman",5642), new PromoDorada());
			aplicaPromocion(Domicilio.generaDomicilio("Roca",1234), new PromoDorada());
			aplicaPromocion(Domicilio.generaDomicilio("Jujuy",8442), new PromoPlatino());
			aplicaPromocion(Domicilio.generaDomicilio("Alvear",2464), new PromoPlatino());
			FacturaAPagar("44235283",true);// *1.05 ya que se paga con tarjeta
			FacturaAPagar("44235283",false);
			
			ingresaFactura("44231231", "Cheque", null);
			ingresaContratacion("44231231", 3, 3, true, Domicilio.generaDomicilio("Arenales",7236), "Vivienda");//31000
			FacturaAPagar("44231231",true);
			FacturaAPagar("44231231",false);
			
			System.out.println("\nPrueba clonacion");
			IFactura clon1=clonaFactura("44235283");
			IFactura clon2=clonaFactura("44667826");
			IFactura clon3=clonaFactura("3434534354"); //clonacion correcta
			aplicaPromocion(Domicilio.generaDomicilio("Tucuman",2312), new PromoDorada());
			MuestraFactura("3434534354");
			System.out.println("MUESTRA CLON: \n"+clon3);
			sistema.MuestraEstado();
		}
		
	private static void ingresaAbonado(String nombre,String dni, String tipo) {
		if(nombre!=null && !nombre.equals("") && dni!=null && !dni.equals("") && !tipo.equals("")) { //tipo si puede ser null, ya que implica que es la factura default
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
		else
			System.out.println("Parametros incorrectos, por favor reingresar informacion correctamente");
	}
	
	private static void ingresaContratacion(String dni,int camaras, int botonesAntipanico, boolean movilAcompanamiento, Domicilio domicilio, String tipo) {
		if(dni!=null && !dni.equals("") && camaras>=0 && botonesAntipanico>=0 && domicilio!=null && tipo!=null && !tipo.equals("")) {
			try {
				Sistema.getInstance().nuevaContratacion(dni, camaras, botonesAntipanico, movilAcompanamiento, domicilio, tipo);
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
		else
			System.out.println("Parametros incorrectos, por favor reingresar informacion correctamente");	
	}
	
	private static void ingresaFactura(String dni, String tipoPago, String tipoFactura) {
		if(dni!=null && !dni.equals("")&& tipoPago!=null && !tipoPago.equals("")) { //el tipo de factura puede ser null, en tal caso la factura se crea de tipo default
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
		else
			System.out.println("Parametros incorrectos, por favor reingresar informacion correctamente");	
	}
	
	private static void eliminaFactura(String dni) {
		if(dni!=null && !dni.equals("")) {
			try {
				Sistema.getInstance().eliminarFactura(dni);
			}
			catch(DniDesconocidoException e) {
				System.out.println("El dni ingresado: "+e.getDni()+" no se corresponde con ningun cliente asociado factura");
			}
		}
		else {
			System.out.println("Parametros incorrectos, por favor reingresar informacion correctamente");
		}
	}
	
	private static void eliminaAbonadoSinContratacion(String dni) {
		if(dni!=null && !dni.equals("")) {
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
		else {
			System.out.println("Parametros incorrectos, por favor reingresar informacion correctamente");
		}
	}
	
	private static void eliminaContratacionAbonado(String dni,Domicilio domicilio) {
		if(dni!=null && !dni.equals("") && domicilio!=null) {
			try {
				Sistema.getInstance().eliminaContratacionAbonado(dni, domicilio);
			}
			catch(DomicilioSinContratacionEnAbonadoException e){
				System.out.println("El domicilio con "+e.getDomicilio()+" no cuenta con ninguna contratacion existente para el abonado "+e.getAbonado().getNombre()+" con dni "+e.getAbonado().getDni());
			}
			catch(DniDesconocidoException e){
				System.out.println("El dni ingresado: '"+e.getDni()+"' no puede indentificarse con ningun abonado asociado factura");
			}
		}
		else {
			System.out.println("Parametros incorrectos, por favor reingresar informacion correctamente");
		}
	}
	
	private static void aplicaPromocion(Domicilio domicilio, Promo promocion) {
		if(domicilio!=null && promocion!=null) {
			try {
				Sistema.getInstance().aplicaPromocion(domicilio, promocion);
			}
			catch(DomicilioSinContratacionException e) {
				System.out.println("El domicilio con "+ e.getDomicilio()+" no se encuentra con una contratacion vigente");
			}
		}
		else
			System.out.println("Parametros incorrectos, por favor reingresar informacion correctamente");
	}
	
	private static void FacturaAPagar(String dni,boolean descuento) {
		if(dni!=null && !dni.equals("")){
			try {
				double precio= Sistema.getInstance().calculaPrecioAPagar(dni,descuento);
				System.out.println("La factura a pagar por "+dni+" es de: $"+precio);
			}
			catch(DniDesconocidoException e) {
				System.out.println("El dni '"+e.getDni()+"' no dispone de factura");
			}
		}
		else 
			System.out.println("Parametros incorrectos, por favor reingresar informacion correctamente");
	}
	
	private static IFactura clonaFactura(String dni) {
		IFactura clon=null;
		if(dni!=null && !dni.equals("")){
			try {
				clon=Sistema.getInstance().clonacionFactura(dni);
			}
			catch(CloneNotSupportedException e) {
				System.out.println(e.getMessage());
			}
			catch(DniDesconocidoException e) {
				System.out.println("El dni ingresado: '"+e.getDni()+"' no puede indentificarse con ningun abonado asociado factura");
			}
		}
		else
			System.out.println("Parametros incorrectos, por favor reingresar informacion correctamente");
		return clon;
	}
	
	private static void MuestraFactura(String dni) {
		if(dni!=null && !dni.equals("")){
				try {
					System.out.println(Sistema.getInstance().buscaFactura(dni));
				}
				catch(DniDesconocidoException e) {
					System.out.println("El dni ingresado: '"+e.getDni()+"' no puede indentificarse con ningun abonado asociado factura");
				}
		}
		else
			System.out.println("Parametros incorrectos, por favor reingresar informacion correctamente");
	}
	private static void MuestraAbonadoSinFactura(String dni) {
		if(dni!=null && !dni.equals("")){
				try {
					System.out.println(Sistema.getInstance().buscaAbonado(dni));
				}
				catch(DniDesconocidoException e) {
					System.out.println("El dni ingresado: '"+e.getDni()+"' no puede indentificarse con ningun abonado sin factura");
				}
		}
		else
			System.out.println("Parametros incorrectos, por favor reingresar informacion correctamente");
	}
	
	private static void MuestraContratacion(Domicilio domicilio) {
		if(domicilio!=null){
				try {
					System.out.println(Sistema.getInstance().buscaContratacion(domicilio));
				}
				catch(DomicilioSinContratacionException e) {
					System.out.println("El domicilio "+e.getDomicilio()+" no se encuentra con ninguna contratacion vigente");
				}
		}
		else
			System.out.println("Parametros incorrectos, por favor reingresar informacion correctamente");
	}
	
	
}
