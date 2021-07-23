package ec.edu.ups.rest;

import java.io.IOException;
import javax.ejb.EJB;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ec.edu.ups.ejb.PersonaFacade;
import ec.edu.ups.entidades.Persona;

@Path("/GestionCuentas/")
public class GestionUsuarioResource {    
	
	@EJB
	private PersonaFacade ejbPersona;
	
    public GestionUsuarioResource() {
        
    }
    
    @POST
    @Path("/inicioSesion")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(@FormParam("usuario") String usuario, @FormParam("clave") String clave) throws IOException {		
    	Persona sta = ejbPersona.inicioSesion(usuario, clave);
    	Jsonb json = JsonbBuilder.create();
    	if(sta==null) {
    		return Response.status(404).entity(false).build();
    	}else {
    		return Response.status(200).entity(json.toJson(sta)).build();
    	}    	
    }
    
    @PUT
	@Path("/crearCuenta")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response post(@FormParam("usuario") String usuario, @FormParam("clave") String clave,
			@FormParam("cedula") String cedula) throws IOException {
		Persona persona2 = ejbPersona.buscarPorCedula(cedula);
		if (persona2 != null) {
			persona2.setCorreo(usuario);
			persona2.setPassword(clave);
			persona2.setRol("C");
			ejbPersona.edit(persona2);
			return Response.ok(true).build();
		} else {
			return Response.ok(false).build();
		}

	}
    
    @PUT
    @Path("/modificar")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response post(@FormParam("usuario") String usuario, @FormParam("clave") String clave, @FormParam("cedula") String cedula, @FormParam("nombre") String nombre, @FormParam("apellido") String apellido, @FormParam("direccion") String direccion,@FormParam("telefono") String telefono) throws IOException {
    	Persona aux=ejbPersona.buscarPorCedula(cedula);
    	if(aux==null) {
    		return Response.ok(false).build();
    	}else {
    		aux.setApellido(apellido);    		
    		aux.setCorreo(usuario);
    		aux.setDireccion(direccion);
    		aux.setNombre(nombre);
    		aux.setPassword(clave);
    		aux.setTelefono(telefono);
    		ejbPersona.edit(aux);
    		return Response.ok(true).build();
    	}
    }
    
    @PUT
    @Path("/anularCuenta")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response post(@FormParam("cedula") String cedula) throws IOException {
    	Persona aux=ejbPersona.buscarPorCedula(cedula);
    	if(aux==null) {
    		return Response.ok(false).build();
    	}else {
    		aux.setRol("X");
    		ejbPersona.edit(aux);
    		return Response.ok(true).build();
    	}
    	
    }
    
}