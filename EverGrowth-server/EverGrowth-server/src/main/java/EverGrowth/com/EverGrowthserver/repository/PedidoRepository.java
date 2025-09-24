package EverGrowth.com.EverGrowthserver.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import EverGrowth.com.EverGrowthserver.entity.PedidoEntity;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {

    @Query("SELECT p FROM PedidoEntity p WHERE p.user.id = :userId")
    Page<PedidoEntity> findByUser(Long userId, Pageable pageable);

    @Query("SELECT p FROM PedidoEntity p WHERE MONTH(p.fecha_pedido) = :mes")
    List<PedidoEntity> findByMes(int mes);

    @Query("SELECT MAX(p.id_factura) FROM PedidoEntity p")
    Long findMaxCodigoFactura();

    @Modifying
    @Query(value = "ALTER SEQUENCE pedido_id_seq RESTART WITH 1", nativeQuery = true)
    void resetAutoIncrement();

}
