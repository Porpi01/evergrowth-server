package EverGrowth.com.EverGrowthserver.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import EverGrowth.com.EverGrowthserver.entity.CarritoEntity;
import EverGrowth.com.EverGrowthserver.entity.UsuarioEntity;

public interface CarritoRepository extends JpaRepository<CarritoEntity, Long> {

  Long countByUser(UsuarioEntity user);

   @Query("SELECT c FROM CarritoEntity c WHERE c.user.id = :userId AND c.producto.id = :productoId")
  Optional<CarritoEntity> findByUserAndProducto(@Param("userId") Long userId, @Param("productoId") Long productoId);

  @Query("SELECT c FROM CarritoEntity c WHERE c.producto.id = :productoId")
  Page<CarritoEntity> findByProducto(@Param("productoId")Long productoId, Pageable pageable);

  @Query("SELECT c FROM CarritoEntity c WHERE c.user.id = :userId")
  Page<CarritoEntity> findByUser(@Param("userId") Long userId, Pageable pageable);
  

  @Query(value = "SELECT SUM(c.cantidad * p.precio) FROM carrito c JOIN producto p ON c.id_producto = p.id WHERE c.id_usuario = ?1", nativeQuery = true)
  Double calcularCosteCarrito(Long id);
  
  @Query(value = "SELECT SUM(c.cantidad * p.precio) FROM carrito c JOIN producto p ON c.id_producto = p.id WHERE c.id_usuario = ?1", nativeQuery = true)
  Double calculateTotalCartCost(Long usuario_id);

  @Modifying
  @Query(value = "ALTER SEQUENCE carrito_id_seq RESTART WITH 1", nativeQuery = true)
  void resetAutoIncrement();

  @Modifying
  @Query(value = "DELETE FROM carrito WHERE id_usuario = ?1", nativeQuery = true)
  void deleteByUsuarioId(Long usuario_id);


}