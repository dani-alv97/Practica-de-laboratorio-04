package ec.edu.ups.rest;

import java.io.IOException;
//import java.math.BigDecimal;
//import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.Date;
import javax.ejb.EJB;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ec.edu.ups.ejb.BodegaFacade;
import ec.edu.ups.ejb.FacturaDetalleFacade;
import ec.edu.ups.ejb.PedidoCabeceraFacade;
import ec.edu.ups.ejb.PedidoDetalleFacade;
import ec.edu.ups.ejb.PersonaFacade;
import ec.edu.ups.ejb.ProductoFacade;
import ec.edu.ups.ejb.StockFacade;
import ec.edu.ups.entidades.Bodega;
import ec.edu.ups.entidades.PedidoCabecera;
import ec.edu.ups.entidades.PedidoDetalle;
import ec.edu.ups.entidades.Persona;
import ec.edu.ups.entidades.Producto;
import ec.edu.ups.entidades.Stock;

@Path("/cliente/")
public class PedidosResource {

	@EJB
	PedidoCabeceraFacade ejbPedidoCabecera;
	@EJB
	PedidoDetalleFacade ejbPedidoDetalle;

	@EJB
	PersonaFacade ejbPersona;

	@EJB
	ProductoFacade ejbProducto;

	@EJB
	FacturaDetalleFacade ejbFactura;

	@EJB
	BodegaFacade ejbBodega;

	@EJB
	StockFacade ejbStock;

	@GET
	@Path("/verEstados/{cedula}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response verEstados(@PathParam("cedula") String cedula) throws IOException {

		List<PedidoCabecera> sta = ejbPedidoCabecera.pedidosCabeceraFiltrada(cedula);

		Jsonb jsonb = JsonbBuilder.create();
		return Response.ok(jsonb.toJson(sta)).build();
	}

	@POST
	@Path("pedido")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response postPedido(String pedido) throws IOException {
		Jsonb jsonb = JsonbBuilder.create();
		PedidoCabecera pedidoc = jsonb.fromJson(pedido, PedidoCabecera.class);
		double subtotal = 0.0;
		double iva = 0.0;
		double total = 0.0;
		Persona persona = ejbPersona.buscarPorCedula(pedidoc.getPersona().getCedula());
		pedidoc.setPersona(persona);

		for (int i = 0; i < pedidoc.getPedidosDetale().size(); i++) {
			
			PedidoDetalle ped = pedidoc.getPedidosDetale().get(i);
			List<Stock> stk = ejbStock.findby(ped.getIdbodif());
			Producto pro = ped.getProducto();
			ped.setTotal(ped.getCantidad()*ped.getProducto().getPrecio());
			ped.setPedidoCabecera(pedidoc);
			for (int j = 0; j < stk.size(); j++) {
				Stock stock = stk.get(j);
				if (stock.getProducto().getId() == pro.getId()) {
					subtotal = (double) pro.getPrecio() * ped.getCantidad();
					iva = subtotal * 0.12;
					total = subtotal + iva;
					pedidoc.setSubtotal((float) (pedidoc.getSubtotal() + subtotal));
					pedidoc.setIva((float) (pedidoc.getIva() + iva));
					pedidoc.setTotal((float) (pedidoc.getTotal() + total));
					stock.setCantidad(stock.getCantidad() - ped.getCantidad());
					pro.setStock(pro.getStock() - ped.getCantidad());
					
					pedidoc.getPedidosDetale().set(i,ped);
					
					ejbStock.edit(stock);
					ejbProducto.edit(pro);
					break;
				}
			}
		}
		pedidoc.setEstado("Enviado");	
		ejbPedidoCabecera.create(pedidoc);
		
		return Response.ok(true).build();

	}

	@DELETE
	@Path("delete/{id}")
	public Response delete(@PathParam("id") Integer id) {
		System.out.println("REST/client:delete-->" + id);
		return Response.status(204).entity("Usuario borrado..." + id).build();
	}

	@GET
	@Path("/listprods/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response listprods(@PathParam("id") Integer id) {
		List<Producto> productos = new ArrayList<Producto>();
		List<Stock> lista = ejbStock.findby(id);
		for (int i = 0; i < lista.size(); i++) {
			productos.add(lista.get(i).getProducto());
		}
		Jsonb jsonb = JsonbBuilder.create();
		productos = productos.stream().sorted((a, b) -> a.getCategoria().compareTo(b.getCategoria())).collect(Collectors.toList());
		return Response.ok(jsonb.toJson(productos)).build();
	}
}