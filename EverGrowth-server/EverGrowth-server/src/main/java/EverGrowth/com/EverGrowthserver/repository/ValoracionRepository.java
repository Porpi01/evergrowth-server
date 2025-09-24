package EverGrowth.com.EverGrowthserver.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import EverGrowth.com.EverGrowthserver.entity.ValoracionEntity;

public interface ValoracionRepository extends JpaRepository<ValoracionEntity, Long> {

  @Query("SELECT v FROM ValoracionEntity v WHERE v.user.id = :userId")
  Page<ValoracionEntity> findByUser(Long userId, Pageable pageable);

  @Query("SELECT v FROM ValoracionEntity v WHERE v.producto.id = :productoId")
  Page<ValoracionEntity> findByProducto(Long productoId, Pageable pageable);

  @Modifying
  @Query(value = "ALTER SEQUENCE valoracion_id_seq RESTART WITH 1", nativeQuery = true)
  void resetAutoIncrement();

  @Query(value = "SELECT * FROM valoracion WHERE length(?1) >= 3 AND (mensaje LIKE %?1% OR titulo LIKE %?1%)", nativeQuery = true)
  Page<ValoracionEntity> findByMensajeOrTitulo(String titulo, String mensaje, Pageable oPageable);

}
