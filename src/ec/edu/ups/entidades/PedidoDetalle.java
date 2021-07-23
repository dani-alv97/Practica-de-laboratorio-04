package ec.edu.ups.entidades;

import java.io.Serializable;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: PedidoDetalle
 *
 */
@Entity

public class PedidoDetalle implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int cantidad;
	private double total;

	@ManyToOne
	@JoinColumn
	@JsonbTransient
	private PedidoCabecera pedidoCabecera;

	@OneToOne
	private Producto producto;
	@Transient
	private int idbodif;
	
	public PedidoDetalle() {

	}

	public PedidoDetalle(int id, int cantidad, double total, PedidoCabecera pedidoCabecera, Producto producto) {
		this.setId(id);
		this.setCantidad(cantidad);
		this.setTotal(total);
		this.setPedidoCabecera(pedidoCabecera);
		this.setProducto(producto);
	}

	public int getIdbodif() {
		return idbodif;
	}

	public void setIdbodif(int idbodif) {
		this.idbodif = idbodif;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total2) {
		this.total = total2;
	}

	public PedidoCabecera getPedidoCabecera() {
		return pedidoCabecera;
	}

	public void setPedidoCabecera(PedidoCabecera pedidoCabecera) {
		this.pedidoCabecera = pedidoCabecera;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
}
