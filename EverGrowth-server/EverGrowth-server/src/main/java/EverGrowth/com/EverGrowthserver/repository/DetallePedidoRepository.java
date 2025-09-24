package EverGrowth.com.EverGrowthserver.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import EverGrowth.com.EverGrowthserver.entity.DetallePedidoEntity;

public interface DetallePedidoRepository extends JpaRepository<DetallePedidoEntity, Long> {

    @Query("SELECT dp FROM DetallePedidoEntity dp WHERE dp.producto.id = :productoId")
    Page<DetallePedidoEntity> findByProducto(Long productoId, Pageable pageable);

    @Query("SELECT dp FROM DetallePedidoEntity dp WHERE dp.pedidos.id = :pedidoId")
    Page<DetallePedidoEntity> findByPedido(Long pedidoId, Pageable pageable);

    @Modifying
    @Query(value = "ALTER SEQUENCE detalle_pedido_id_seq RESTART WITH 1", nativeQuery = true)
    void resetAutoIncrement();

}
