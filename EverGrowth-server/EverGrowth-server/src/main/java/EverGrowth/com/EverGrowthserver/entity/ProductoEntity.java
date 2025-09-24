package EverGrowth.com.EverGrowthserver.entity;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "producto")
public class ProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    String nombre;

    @NotNull
    @PositiveOrZero
    Float precio;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 2048)
    String imagen;

    @NotNull
    private double iva;

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    @NotNull
    @PositiveOrZero
    int stock;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 2048)
    String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private CategoriaEntity categoria;

    @OneToMany(mappedBy = "producto", fetch = jakarta.persistence.FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<CarritoEntity> carritos;

    @OneToMany(mappedBy = "producto", fetch = jakarta.persistence.FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<ValoracionEntity> valoracion;

    @OneToMany(mappedBy = "producto", fetch = jakarta.persistence.FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<DetallePedidoEntity> detallePedido;

    public ProductoEntity() {
        carritos = new ArrayList<>();
        valoracion = new ArrayList<>();
        detallePedido = new ArrayList<>();
    }

    public ProductoEntity(String nombre, Float precio, int stock, String imagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.imagen = imagen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getnombre() {
        return nombre;
    }

    public void setnombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getprecio() {
        return precio;
    }

    public void setprecio(Float precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getCarritos() {
        return carritos.size();
    }

    public int getValoracion() {
        return valoracion.size();
    }

    public CategoriaEntity getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEntity categoria) {
        this.categoria = categoria;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
