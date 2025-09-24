package EverGrowth.com.EverGrowthserver.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import EverGrowth.com.EverGrowthserver.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    @Query(value = "SELECT * FROM usuario WHERE length(?1) >= 3 AND (nombre LIKE %?1% OR apellido1 LIKE %?1% OR apellido2 LIKE %?1% OR username LIKE %?1% OR email LIKE %?1%)", nativeQuery = true)
    Page<UsuarioEntity> findByUserByNameOrSurnameOrLastnameContainingIgnoreCase(String nombre, String apellido1,
            String apellido2, String email, Pageable oPageable);

    Optional<UsuarioEntity> findByUsername(String username);

    Optional<UsuarioEntity> findByUsernameAndPassword(String username, String password);

    Optional<UsuarioEntity> findByEmail(String email);

    @Modifying
    @Query(value = "ALTER SEQUENCE usuario_id_seq RESTART WITH 1", nativeQuery = true)
    void resetAutoIncrement();

}
