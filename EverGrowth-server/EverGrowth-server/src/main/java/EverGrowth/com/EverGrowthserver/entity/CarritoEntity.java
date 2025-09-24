package EverGrowth.com.EverGrowthserver.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "carrito")
public class CarritoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    
    private int cantidad;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity user;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private ProductoEntity producto;

    public CarritoEntity(Long id, int cantidad) {
        this.id = id;
        this.cantidad = cantidad;

    }

    public CarritoEntity(int cantidad) {
        this.cantidad = cantidad;

    }

    public CarritoEntity() {

    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioEntity getUser() {
        return user;
    }

    public void setUser(UsuarioEntity user) {
        this.user = user;
    }

    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }
}
