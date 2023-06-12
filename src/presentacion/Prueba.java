package presentacion;

import java.util.GregorianCalendar;

import negocio.*;

	public class Prueba {
		public static void main(String[] args)  {
			Sistema sistema=Sistema.getInstance();
			
			System.out.println("Prueba Ingreso Abonados");
			ingresaAbonado("Mateo", "44235283", "Juridica");	// Cargas correctas
			ingresaAbonado("Joaquin", "3434534354", "Fisica");
			ingresaAbonado("Augusto", "44231231", "Juridica");
			ingresaAbonado("Nicolas", "44667826", "Fisica");
			// Errores
			ingresaAbonado(null, "44667826", "Fisica");	// Nombre null
			ingresaAbonado("", "44667826", "Fisica");	// Nombre vacio
			ingresaAbonado("Nicolas", null, "Fisica");	// DNI null
			ingresaAbonado("Nicolas", "", "Fisica");	// DNI vacio
			ingresaAbonado("Nicolas", "44667826", null);// Tipo persona null
			ingresaAbonado("Nicolas", "44667826", "");	// Tipo persona vacio
			ingresaAbonado("Nicolas", "44667826", "Fisica");	//carga por 2da vez el abonado
			ingresaAbonado("Mariana", "3215123", "Astronauta");	//carga con tipo desconocido
			
			
			System.out.println("\nPrueba Ingreso Facturas");
			ingresaFactura("44667826", "Efectivo", null);	// Ingresa la factura de Nicolas
			ingresaFactura("44235283", "Tarjeta", null);	// Ingresa la facutura de Mateo
			// Errores
			ingresaFactura(null, "Tarjeta", null);			// Ingresa DNI nulo
			ingresaFactura("", "Tarjeta", null);			// Ingresa DNI vacio
			ingresaFactura("44235283", null, null);			// Ingresa metodo de pago nulo
			ingresaFactura("44235283", "", null);			// Ingresa metodo de pago vacio
			ingresaFactura("44667826", "Efectivo", null);	// Ingresa por 2da vez la factura de nicolas
			ingresaFactura("2", "Efectivo", null);			// Ingresa dni no cargado como cliente
			ingresaFactura("44231231", "Uala", null);		// Ingresa metodo de pago incorrecto
			ingresaFactura("3434534354", "Tarjeta", "A");	// Ingresa tipo de factura incorrecto
			
			
			System.out.println("\nPrueba Ingreso Contrataciones");
			ingresaContratacion("44667826", 1, 3, false,Domicilio.generaDomicilio("Moreno",2410), "Comercio");//carga correcta
			ingresaContratacion("44235283", 1, 3, false,Domicilio.generaDomicilio("Garay",4504), "Comercio");
			

			ingresaContratacion(null, 1, 3, false,Domicilio.generaDomicilio("Garay",4504), "Comercio");	// Ingresa DNI nulo
			ingresaContratacion("", 1, 3, false,Domicilio.generaDomicilio("Garay",4504), "Comercio");	// Ingresa DNI vacio
			ingresaContratacion("44235283", -1, 3, false,Domicilio.generaDomicilio("Garay",4504), "Comercio");	// Ingresa camaras negativas
			ingresaContratacion("44235283", 1, -3, false, null, "Comercio");									// Ingresa domicilio nulo
			ingresaContratacion("44235283", 1, 3, false,Domicilio.generaDomicilio("Garay",4504), null);	// Ingresa tipo de contratacion nulo
			ingresaContratacion("44235283", 1, 3, false,Domicilio.generaDomicilio("Garay",4504), "");	// Ingresa tipo de contratacion vacio
			
			ingresaContratacion("44231231", 1, 3, false,Domicilio.generaDomicilio("Moreno",2410), "Comercio");//no existe factura para el dni
			ingresaContratacion("44667826", 1, 3, false,Domicilio.generaDomicilio("Moreno",2410), "lol");//tipo incorrecto de contratacion
			ingresaContratacion("44235283", 1, 3, false,Domicilio.generaDomicilio("Moreno",2410), "Comercio");//ingresa 2 veces el mismo domicilio para contratacion
			
			
			System.out.println("\nPrueba eliminar factura");
			eliminaFactura("44667826");	//eliminacion correcta

			eliminaFactura("");			// DNI vacio
			eliminaFactura(null);		// DNI nulo
			eliminaFactura("3434534354"); //no existe este dni con factura
			
			ingresaFactura("44667826", "Efectivo", null);	// Se carga otra vez a la lista para verificar que se elimino
			ingresaFactura("44667826", "Efectivo", null);	// Como se carga 2 veces error xq ya esta en factura
			ingresaFactura(null, "Efectivo", null);		// DNI nulo
			ingresaFactura("", "Efectivo", null);		// DNI vacio
			ingresaFactura("44667826", null, null);		// tipo nulo 
			ingresaFactura("44667826", "", null);		// tipo vacio
			
			
			
			System.out.println("\nPrueba eliminar abonado sin contratacion");
			eliminaAbonadoSinContratacion("44231231"); //correcta eliminacion
			eliminaAbonadoSinContratacion(null); 	// DNI nulo
			eliminaAbonadoSinContratacion(""); 		// DNI vacio
			
			ingresaAbonado("Augusto","44231231","Juridica"); //se lo carga de nuevo para comprobar que si se elimino
			ingresaAbonado("Augusto","44231231","Juridica"); //se carga por 2da vez y tira error xq ya esta cargado
			
			eliminaAbonado("44667826"); //abonado que tiene una factura vigente
			eliminaAbonado("2"); //abonado no existente
			
			
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
			

			FacturaAPagar(null,true);	// DNI nulo
			FacturaAPagar("",true);		// DNI vacio
			aplicaPromocion(null, new PromoDorada());	// Domicilio nulo
			aplicaPromocion(Domicilio.generaDomicilio("Santa Fe",2410), null);	// Promocion nula
			
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
	
		
	/** Metodo que envia a sistema el mensaje de cargar un abonado.
	* @param nombre : el nombre del abonado que se ingresa.<br>
	* @param dni : el dni del abonado que se ingresa.<br>
	* @param tipo: tipo de abonado ingresante.<br>
	* <b> Pre: </b>  No cuenta con precondiciones(esto xq se controlan con excepciones o condicionales) <br>
	* <b> Post: </b> Se agrega el abonado a la lista de abonados sin contratacion o sino se imprime por pantalla el problema surgido<br>
	*/	
	private static void ingresaAbonado(String nombre,String dni, String tipo) {
		if(nombre!=null && !nombre.equals("") && dni!=null && !dni.equals("") && tipo != null&& !tipo.equals("")) { //tipo si puede ser null, ya que implica que es la factura default
			try {
				Sistema.getInstance().nuevoAbonado(nombre, dni, tipo);
			}
			catch(AbonadoYaCargadoException e){
				String print="El abonado "+e.getNombre()+" con dni "+e.getDni()+" ya se encuentra cargado";
				System.out.println(print);
			}
			catch(TipoIncorrectoPersonaException e) {
				System.out.println("El tipo de persona ingresado ('"+e.getTipo()+"') no se reconoce como uno de los tipos permitidos en el sistema");
			}
		}
		else
			System.out.println("Parametros incorrectos, por favor reingresar informacion correctamente");
	}
	
	
	/** Metodo que envia a sistema el mensaje de cargar una contratacion a una factura existente.
	* @param dni : el dni del abonado a cuya factura quiere agregarse la contratacion.<br>
	* @param camaras: cantidad de camaras contratadas en el servicio.<br>
	* @param botonesAntipanico: cantidad de botones antipanico contratados en el servicio.<br>
	* @param movilAcompanamiento: booleano que responde a si se contrata un movil de acompanamiento.<br>
	* @param domicilio: direccion de la propiedad sobre la que se contrata el servicio.<br>
	* @param tipo: el tipo de la contratacion que se ingresa.<br>
	* <b> Pre: </b> No cuenta con precondiciones(esto xq se controlan con excepciones o condicionales)<br>
	* <b> Post: </b> Se agrega la contratacion a la lista de contrataciones de la factura correspondiente al dni ingresado o sino se imprime por pantalla el problema surgido<br>
	*/	
	private static void ingresaContratacion(String dni,int camaras, int botonesAntipanico, boolean movilAcompanamiento, Domicilio domicilio, String tipo) {
		if(dni!=null && !dni.equals("") && camaras>=0 && botonesAntipanico>=0 && domicilio!=null && tipo!=null && !tipo.equals("")) {
			try {
				Sistema.getInstance().nuevaContratacion(dni, camaras, botonesAntipanico, movilAcompanamiento, domicilio, tipo);
			}
			catch(DomicilioYaConContratacionExcepcion e) {
				System.out.println(e.getDomicilio().toString()+" ya se encuentra con una servicio contratado");
			}
			catch(DniDesconocidoException e) { 
				System.out.println("Ningun cliente registrado posee como dni "+e.getDni());
			}
			catch(TipoIncorrectoServicioException e){
				System.out.println("El tipo de servicio ingresado ('"+e.getTipo()+"') no coincide con ninguno de los disponibles");
			}
		}
		else
			System.out.println("Parametros incorrectos, por favor reingresar informacion correctamente");	
	}
	
	/**Metodo que envia a sistema el mensaje de eliminar un abonado sin contratacion de su base de datos.
	* @param dni: el dni del abonado que se quiere eliminar.<br>
	* <b> Pre: </b>  No cuenta con precondiciones(esto xq se controlan con excepciones o condicionales) <br>
	* <b> Post: </b>Se elimina el abonado seleccionado de la lista de abonados sin contratacion en base al dni recibido por parametro o sino se imprime por pantalla el problema surgido  <br>
	*/
	private static void eliminaAbonado(String dni) {
		if(dni!=null && !dni.equals("")) {
			try {
				Sistema.getInstance().eliminaAbonado(dni);
			}
			catch(DniDesconocidoException e) {
				System.out.println("El dni ingresado: '"+e.getDni()+"' no se encuentra cargado como cliente sin contratacion");
			}
			catch(AbonadoConFacturaException e) {
				System.out.println("El dni ingresado para eliminar: '"+e.getDni()+"' corresponde a un abonado con una factura pendiente de pago ");
			}
		}
		else {
			System.out.println("Parametros incorrectos, por favor reingresar informacion correctamente");
		}
	}
	/**Metodo que envia a sistema el mensaje de eliminar una contratacion de un abonado.
	* @param dni: el dni del abonado del que se quiere eliminar la contratacion.<br>
	* @param domicilio: el domicilio que posee la contratacion que se desea eliminar.<br>
	* <b> Pre: </b>  No cuenta con precondiciones(esto xq se controlan con excepciones o condicionales) <br>
	* <b> Post: </b>Se elimina la contratacion (seleccionada por su domicilio) del abonado especificado por dni o sino se imprime por pantalla el problema surgido  <br>
	*/
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
	
	
	/**Metodo que envia a sistema el mensaje de aplicar una promocion a un domicilio.
	* @param domicilio: domicilio sobre el cual se quiere aplicar la promocion.<br>
	* @param promocion: promocion (dorada o plateada) que se aplicara sobre el domicilio.<br>
	* <b> Pre: </b>  No cuenta con precondiciones(esto xq se controlan con excepciones o condicionales) <br>
	* <b> Post: </b> Se aplica el descuento sobre el precio del servicio del domicilio, dado por el tipo de promocion especificada o sino se imprime por pantalla el problema surgido  <br>
	*/
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
	/**Metodo que pide a sistema el precio de una factura y lo muestra por pantalla.
	* @param dni:el dni del abonado asociado a esa factura.<br>
	* @param descuento: booleano que indica si el precio se quiere con o sin el descuento por metodo de pago.<br>
	* <b> Pre: </b> No cuenta con precondiciones(esto xq se controlan con excepciones o condicionales) <br>
	* <b> Post: </b> Imprime por pantalla el precio (con o sin descuento) de la factura asociada al dni o sino se imprime por pantalla el problema surgido<br>
	*/
	private static void FacturaX(String dni,int mes,int anio) {
		if(dni!=null && !dni.equals("")){
			try {
				System.out.println(Sistema.getInstance().buscaFactura(dni, new GregorianCalendar(anio,mes,0)));
			}
			catch(DniDesconocidoException e) {
				System.out.println("El dni '"+e.getDni()+"' no dispone de factura");
			}
		}
		else 
			System.out.println("Parametros incorrectos, por favor reingresar informacion correctamente");
	}
	
	/**Metodo que solicita un clon de una factura para luego retornarlo.
	* @param dni: el dni del abonado asociado a esa factura a clonar.<br>
	* <b> Pre: </b>  No cuenta con precondiciones(esto xq se controlan con excepciones o condicionales) <br>
	* <b> Post: </b> Imprime por pantalla el precio (con o sin descuento) de la factura asociada al dni  o sino muestra por pantalla el problema surgido<br>
	*/
	private static IFactura clonaFactura(String dni,int mes, int anio) {
		IFactura clon=null;
		if(dni!=null && !dni.equals("")){
			try {
				clon=Sistema.getInstance().clonacionFactura(dni,new GregorianCalendar(anio,mes,0));
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
	/**Metodo solicita al sistema una factura, para luego mostrarla por pantalla.
	* @param dni: el dni del abonado asociado a la factura a clonar.<br>
	* <b> Pre: </b>  No cuenta con precondiciones(esto xq se controlan con excepciones o condicionales) <br>
	* <b> Post: </b> Imprime por pantalla la factura asociada al dni especificado o sino muestra por pantalla el problema surgido<br>
	*/
	private static void MuestraFactura(String dni,int mes, int anio) {
		if(dni!=null && !dni.equals("")){
				try {
					System.out.println(Sistema.getInstance().buscaFactura(dni,new GregorianCalendar(anio,mes,0)));
				}
				catch(DniDesconocidoException e) {
					System.out.println("El dni ingresado: '"+e.getDni()+"' no puede indentificarse con ningun abonado asociado factura");
				}
		}
		else
			System.out.println("Parametros incorrectos, por favor reingresar informacion correctamente");
	}
	/**Metodo solicita al sistema un abonado sin factura, para luego mostrarlo por pantalla.
	* @param dni: el dni del abonado que se quiere mostrar.<br>
	* <b> Pre: </b>  No cuenta con precondiciones(esto xq se controlan con excepciones o condicionales) <br>
	* <b> Post: </b> Imprime por pantalla el abonado sin factura especificado por su dni o sino muestra por pantalla el problema surgido<br>
	*/
	private static void MuestraAbonado(String dni) {
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
	/**Metodo solicita al sistema una contratacion, para luego mostrarla por pantalla.
	* @param domicilio: domicilio sobre el cual se tiene la contratacion a mostrar.<br>
	* <b> Pre: </b>  No cuenta con precondiciones(esto xq se controlan con excepciones o condicionales) <br>
	* <b> Post: </b> Imprime por pantalla la contratacion especificada por su domicilio o sino muestra por pantalla el problema surgido<br>
	*/
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
