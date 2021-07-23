package ec.edu.ups.controlador;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class Navigator {
	
	public Navigator() {
		
	}
	
	public String reglaRedir( String page ) {
		if(page.equals("CreacionFactura")) {
			return "crearFactura";
		}else if(page.equals("listarFactura")) {
			return "listarFactura"; 
		}else if(page.equals("crearusu")) {
			return "crearusu";
		}else if(page.equals("crearCliente")) {
			return "crearCliente";
		}else if(page.equals("listarClientes")) {
			return "listarClientes";
		}else if(page.equals("menuprincipal")) {
			return "menuprincipal";
		}else if(page.equals("bodega1")) {
			return "bodega1";
		}else if(page.equals("bodega2")) {
			return "bodega2";
		}else if(page.equals("bodega3")) {
			return "bodega3";
		}else if(page.equals("informeGeneral")) {
			return "informeGeneral"; 
		}else if(page.equals("gestionBodegas")) {
			return "GestionBodega";  
		}else if(page.equals("GestionarProductos")) {
			return "GestionarProductos";
		}else if(page.equals("salir")) {
			LoguinBean log=new LoguinBean();
			log.logout();
			return "salir";
		}
		return page;
	}
}
