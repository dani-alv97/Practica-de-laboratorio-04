package ec.edu.ups.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: PedidoCabecera
 *
 */
@Entity

public class PedidoCabecera implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Date fecha;
	private float subtotal;
	private float total;
	private float iva;
	private String estado;
	@Transient
	private int idB;
	@ManyToOne
	@JoinColumn

	private Persona persona;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pedidoCabecera")

	private List<PedidoDetalle> pedidosDetale;

	@Override
	public String toString() {
		return "PedidoCabecera [id=" + id + ", fecha=" + fecha + ", subtotal=" + subtotal + ", total=" + total
				+ ", iva=" + iva + ", estado=" + estado + ", persona=" + persona + ", pedidosDetale=" + pedidosDetale
				+ "]";
	}

	public PedidoCabecera() {
	
	}
	
	public PedidoCabecera(int id, String estado, Date fecha, float iva, float subtotal, float total, Persona persona) {
        super();
        this.id = id;
        this.estado = estado;
        this.fecha = fecha;
        this.iva = iva;
        this.subtotal = subtotal;
        this.total = total;
        this.persona = persona;
    }
 
	public int getIdB() {
		return idB;
	}

	public void setIdB(int idB) {
		this.idB = idB;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public float getIva() {
		return iva;
	}

	public void setIva(float iva) {
		this.iva = iva;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<PedidoDetalle> getPedidosDetale() {
		return pedidosDetale;
	}

	public void setPedidosDetale(List<PedidoDetalle> pedidosDetale) {
		this.pedidosDetale = pedidosDetale;
	}
	
	public void addPedidosDetale(PedidoDetalle pedidosDetale) {
		this.pedidosDetale.add(pedidosDetale);
	}
   
}
