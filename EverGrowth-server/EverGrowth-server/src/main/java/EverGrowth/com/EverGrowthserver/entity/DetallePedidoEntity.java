package EverGrowth.com.EverGrowthserver.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "detalle_pedido")
public class DetallePedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cantidad;

    private Float precio_unitario;

    @NotNull
 
    private double iva;
  
    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private ProductoEntity producto;

     @ManyToOne
    @JoinColumn(name = "id_pedido")
    private PedidoEntity pedidos;

    public DetallePedidoEntity() {

    }

    public DetallePedidoEntity(int cantidad, float precio_unitario) {
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Float getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(Float precio_unitario) {
        this.precio_unitario = precio_unitario;
    }


    public PedidoEntity getPedidos() {
        return pedidos;
    }

    public void setPedidos(PedidoEntity pedidos) {
        this.pedidos = pedidos;
    }

    public ProductoEntity getProductos() {
        return producto;
    }

    public void setProductos(ProductoEntity producto) {
        this.producto = producto;
    }
}
