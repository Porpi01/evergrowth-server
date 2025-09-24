package EverGrowth.com.EverGrowthserver.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import EverGrowth.com.EverGrowthserver.entity.CategoriaEntity;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {

    CategoriaEntity findByNombre(String nombre);


    Optional<CategoriaEntity> findById(Long id);

    @Modifying
    @Query(value = "ALTER SEQUENCE categoria_id_seq RESTART WITH 1", nativeQuery = true)
    void resetAutoIncrement();

    @Query(value = "SELECT * FROM categoria WHERE length(?1) >= 3 AND (nombre LIKE %?1% )", nativeQuery = true)
    Page<CategoriaEntity> findByName(String nombre, Pageable oPageable);
}
