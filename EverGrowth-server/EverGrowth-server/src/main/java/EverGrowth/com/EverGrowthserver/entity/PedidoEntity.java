package EverGrowth.com.EverGrowthserver.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedido")
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime fecha_pedido;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime fecha_entrega;

 
    private boolean estado_pedido;
   
    private Long id_factura = 0L;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity user;

    @OneToMany(mappedBy = "pedidos", fetch = jakarta.persistence.FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<DetallePedidoEntity> detallepedidos;

    public PedidoEntity() {
        detallepedidos = new java.util.ArrayList<>();
    }

    public PedidoEntity(Long id, LocalDateTime fecha_pedido, LocalDateTime fecha_entrega, boolean estado_pedido) {
        this.id = id;
        this.fecha_pedido = fecha_pedido;
        this.fecha_entrega = fecha_entrega;
        this.estado_pedido = estado_pedido;
    }

    public PedidoEntity( LocalDateTime fecha_pedido, LocalDateTime fecha_entrega, boolean estado_pedido) {
      
        this.fecha_pedido = fecha_pedido;
        this.fecha_entrega = fecha_entrega;
        this.estado_pedido = estado_pedido;
    }


    public void generarFactura(Long idFacturaGenerada) {
        this.id_factura = idFacturaGenerada;
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

    public int getDetallePedidos() {
        return detallepedidos.size();
    }

    
    public Long getId_factura() {
        return id_factura;
    }

    public void setId_factura(Long id_factura) {
        this.id_factura = id_factura;
    }


    public LocalDateTime getFecha_pedido() {
        return fecha_pedido;
    }

    public void setFecha_pedido(LocalDateTime fecha_pedido) {
        this.fecha_pedido = fecha_pedido;
    }

    public LocalDateTime getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(LocalDateTime fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }
    public boolean isEstado_pedido() {
        return estado_pedido;
    }

    public void setEstado_pedido(boolean estado_pedido) {
        this.estado_pedido = estado_pedido;
    }


}
