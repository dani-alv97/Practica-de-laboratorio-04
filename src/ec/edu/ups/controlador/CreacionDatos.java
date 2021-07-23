package ec.edu.ups.controlador;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

import ec.edu.ups.ejb.BodegaFacade;
import ec.edu.ups.ejb.CategoriaFacade;
import ec.edu.ups.ejb.CiudadFacade;
import ec.edu.ups.ejb.PedidoCabeceraFacade;
import ec.edu.ups.ejb.PedidoDetalleFacade;
import ec.edu.ups.ejb.PersonaFacade;
import ec.edu.ups.ejb.ProductoFacade;
import ec.edu.ups.ejb.ProvinciaFacade;
import ec.edu.ups.entidades.Bodega;
import ec.edu.ups.entidades.Categoria;
import ec.edu.ups.entidades.Ciudad;
import ec.edu.ups.entidades.PedidoCabecera;
import ec.edu.ups.entidades.PedidoDetalle;
import ec.edu.ups.entidades.Persona;
import ec.edu.ups.entidades.Producto;
import ec.edu.ups.entidades.Provincia;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@RequestScoped
public class CreacionDatos implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@EJB
    private CategoriaFacade ejbCategoria;
	@EJB
	private ProductoFacade ejbProducto;
	@EJB
	private BodegaFacade ejbBodega;
	@EJB
	private ProvinciaFacade ejbProvincia;
	@EJB
	private CiudadFacade ejbCiudad;
	@EJB
	private PersonaFacade ejbPersona;
	@EJB
	private PedidoCabeceraFacade ejbPedidoCabecera;
	@EJB
	private PedidoDetalleFacade ejbPedidoDetalle;
	
	public void creacionPrincipal() {
		
		Categoria cat=new Categoria(1, "Limpieza");
		Categoria cat2=new Categoria(2, "Jardineria");
		Categoria cat3=new Categoria(3, "Cuidado personal");
		Categoria cat4=new Categoria(4, "Cocina");
		Categoria cat5=new Categoria(5, "Electricidad");
		Categoria cat6=new Categoria(6, "Herramientas");
		Categoria cat7=new Categoria(7, "Electrodomesticos");
		
		ejbCategoria.create(cat);
		ejbCategoria.create(cat2);
		ejbCategoria.create(cat3);
		ejbCategoria.create(cat4);
		ejbCategoria.create(cat5);
		ejbCategoria.create(cat6);
		ejbCategoria.create(cat7);
		
		Producto pord=new Producto(1, "Detergente Deja",(float) 5.99, 30, 'S', cat);
		Producto pord2=new Producto(2, "Lavavajilla",(float) 1.99, 40, 'S', cat);
		Producto pord3=new Producto(3, "Papel Hijienico",(float) 0.89, 50, 'S', cat);
		Producto pord4=new Producto(4, "Creolina",(float) 2.59, 60, 'S', cat);
		
		Producto pord5=new Producto(5, "Pala",(float) 145, 10, 'S', cat2);
		Producto pord6=new Producto(6, "Pico",(float) 26, 10, 'S', cat2);
		Producto pord7=new Producto(7, "Carretilla",(float) 8, 15, 'S', cat2);
		
		Producto pord8=new Producto(8, "Shampoo Ego ",(float) 4.99, 10, 'S', cat3);
		Producto pord9=new Producto(9, "Desodorante",(float) 2.59, 10, 'S', cat3);
		Producto pord10=new Producto(10,"Cepillo de Dientes",(float) 3.35, 10, 'S', cat3);
		
		Producto pord11=new Producto(11, "Exprimidor de limon",(float) 6, 100, 'S', cat4);
		Producto pord12=new Producto(12, "Platos",(float) 1.50, 100, 'S', cat4);
		Producto pord13=new Producto(13, "Cuchillos",(float) 3, 70, 'S', cat4);
		
		Producto pord14=new Producto(14, "Lampara de Dormitorio",(float) 1.50, 50, 'S', cat5);
		Producto pord15=new Producto(15, "Tomacorriente",(float) 2.50, 80, 'S', cat5);
		Producto pord16=new Producto(16, "Boquilla",(float) 0.75, 100, 'S', cat5);
		
		Producto pord17=new Producto(17, "Amoladora",(float) 3.75, 70, 'S', cat6);
		Producto pord18=new Producto(15, "Destornillador plano",(float) 0.5, 100, 'S', cat6);
		Producto pord19=new Producto(19, "Destornillador estrella",(float) 0.5, 100, 'S', cat6);
		
		Producto pord20=new Producto(20, "Lavadora",(float) 769.99, 10, 'S', cat7);
		Producto pord21=new Producto(21, "Cocina",(float) 454.99, 10, 'S', cat7);
		Producto pord22=new Producto(22, "Refrigeradora",(float) 2400, 5, 'S', cat7);
		
		Provincia provi = new Provincia(1, "Azuay");
		Provincia provi2 = new Provincia(2, "Pichincha");
		Provincia provi3 = new Provincia(3, "Guayas");
		
		ejbProvincia.create(provi);
		ejbProvincia.create(provi2);
		ejbProvincia.create(provi3);
		
		Ciudad ciudad = new Ciudad(1, "Cuenca", provi); 
		Ciudad ciudad2 = new Ciudad(2, "Quito", provi2);
		Ciudad ciudad3 = new Ciudad(3, "Guayaquil", provi3);
		
		ejbCiudad.create(ciudad);
		ejbCiudad.create(ciudad2);
		ejbCiudad.create(ciudad3);
		
		Bodega bodega = new Bodega(1, "Bodega 1", "Don Bosco y Loja", ciudad);
		Bodega bodega2 = new Bodega(2, "Bodega 2", "Panecillo y Buses", ciudad2);
		Bodega bodega3 = new Bodega(3, "Bodega 3", "Rio hediendo y Zamborondon", ciudad3);
		
		bodega.addProductos(pord);
		bodega.addProductos(pord2);
		bodega.addProductos(pord6);
		bodega.addProductos(pord9);
		bodega.addProductos(pord10);
		bodega.addProductos(pord11);
		bodega.addProductos(pord14);
		bodega.addProductos(pord20);
		bodega.addProductos(pord21);
		
		bodega2.addProductos(pord3);
		bodega2.addProductos(pord7);
		bodega2.addProductos(pord12);
		bodega2.addProductos(pord13);
		bodega2.addProductos(pord15);
		bodega2.addProductos(pord22);
		
		bodega3.addProductos(pord4);
		bodega3.addProductos(pord5);
		bodega3.addProductos(pord8);
		bodega3.addProductos(pord16);
		bodega3.addProductos(pord17);
		bodega3.addProductos(pord18);
		bodega3.addProductos(pord19);
		
		ejbBodega.create(bodega);
		ejbBodega.create(bodega2);
		ejbBodega.create(bodega3);
		
		Persona persona = new Persona(1, "Carlos", "Alvarez","0105100606", "Cuenca", "0983232969", "daniel@gmail.com", "cuenca", "E");
		Persona persona2 = new Persona(2, "David", "Sarumeño","0104477869", "Quito", "0983590338", "david@gmail.com", "cuenca", "A");
		Persona persona3 = new Persona(3, "Juan", "Quiñones","0118845969","Azogues", "0912345678", "juan@gmail.com", "cuenca", "C");
		
		ejbPersona.create(persona);
		ejbPersona.create(persona2);
		ejbPersona.create(persona3);

	}
	
	public void listarProductosBodega() {

        List<Producto> sta = ejbBodega.find(1).getProductos();

        for (Producto producto : sta) {
            System.out.println(producto.getNombre());
        }
    }
	
}
