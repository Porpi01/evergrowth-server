package EverGrowth.com.EverGrowthserver.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import EverGrowth.com.EverGrowthserver.entity.UsuarioEntity;
import EverGrowth.com.EverGrowthserver.exception.ResourceNotFoundException;
import EverGrowth.com.EverGrowthserver.helper.DataGenerationHelper;
import EverGrowth.com.EverGrowthserver.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService {
    private final String tiendaOnlinePassword = "dedb63fbd1f3c4bce46a6e29be0700dda4fe2eec46c79b79fa0c5704d96e308d";

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    SesionService sesionService;

    public Long signUp(UsuarioEntity nuevoUsuario) {
        if (nuevoUsuario == null) {
            throw new IllegalArgumentException("El objeto nuevoUsuario no puede ser nulo");
        }
    
        if (usuarioRepository.findByUsername(nuevoUsuario.getusername()).isPresent()) {
            throw new RuntimeException("El nombre de usuario ya está en uso");
        }
    
        if (usuarioRepository.findByEmail(nuevoUsuario.getEmail()).isPresent()) {
            throw new RuntimeException("El correo electrónico ya está registrado");
        }
    
        nuevoUsuario.setrol(true); // Supongo que esta línea está correctamente
    
        // No establecer el campo apellido2 si está vacío
        if (nuevoUsuario.getapellido2() == null || nuevoUsuario.getapellido2().isEmpty()) {
            nuevoUsuario.setapellido2(null);
        }
        validateFirstLetterUppercase(nuevoUsuario.getnombre());
        validateFirstLetterUppercase(nuevoUsuario.getapellido1());
        validateFirstLetterUppercase(nuevoUsuario.getapellido2());
        validateFirstLetterUppercase(nuevoUsuario.getdireccion());
        // Guarda el nuevo usuario en la base de datos
        return usuarioRepository.save(nuevoUsuario).getId();
    }

    public UsuarioEntity get(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public UsuarioEntity getByUsername(String username) {
        return usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found by username"));
    }

    public Long getTotalUsuarios() {
        sesionService.onlyAdmins();
        return usuarioRepository.count();
    }

    public Long create(UsuarioEntity oUsuarioEntity) {
        sesionService.onlyAdmins();
        oUsuarioEntity.setId(null);
        oUsuarioEntity.setPassword(tiendaOnlinePassword);
        validateFirstLetterUppercase(oUsuarioEntity.getnombre());
        validateFirstLetterUppercase(oUsuarioEntity.getapellido1());
        validateFirstLetterUppercase(oUsuarioEntity.getapellido2());
        validateFirstLetterUppercase(oUsuarioEntity.getdireccion());

        return usuarioRepository.save(oUsuarioEntity).getId();
    }

    public UsuarioEntity update(UsuarioEntity oUsuarioEntityToSet) {
        sesionService.onlyAdminsOrUsersWithIisOwnData(oUsuarioEntityToSet.getId());
        validateFirstLetterUppercase(oUsuarioEntityToSet.getnombre());
        validateFirstLetterUppercase(oUsuarioEntityToSet.getapellido1());
        validateFirstLetterUppercase(oUsuarioEntityToSet.getapellido2());
        validateFirstLetterUppercase(oUsuarioEntityToSet.getdireccion());
        return usuarioRepository.save(oUsuarioEntityToSet);

    }

    public Long delete(Long id) {
        sesionService.onlyAdmins();

        if (usuarioRepository.findById(id).isPresent()) {
            usuarioRepository.deleteById(id);
            return id;
        } else {
            throw new ResourceNotFoundException("El usuario con el ID " + id + " no existe");
        }
    }

    public Page<UsuarioEntity> getPage(Pageable oPageable, String filter) {
        sesionService.onlyAdmins();

        Page<UsuarioEntity> page;

        if (filter == null || filter.isEmpty() || filter.trim().isEmpty()) {
            page = usuarioRepository.findAll(oPageable);
        } else {
            page = usuarioRepository.findByUserByNameOrSurnameOrLastnameContainingIgnoreCase(
                    filter, filter, filter, filter, oPageable);
        }
        return page;
    }

    public Long populate(Integer amount) {
        sesionService.onlyAdmins();

        for (int i = 0; i < amount; i++) {
            String nombre = DataGenerationHelper.getRandomName();
            String apellido1 = DataGenerationHelper.getRandomSurname();
            String apellido2 = DataGenerationHelper.getRandomSurname();
            String emailNamePart = DataGenerationHelper.doNormalizeString(
                    nombre.substring(0, 3) + apellido1.substring(0, 3) + apellido2.substring(0, 2) + i);

            String email = emailNamePart.toLowerCase() + "@gmail.com";
            String telefono = DataGenerationHelper.generateRandomPhone();
            String direccion = DataGenerationHelper.generateRandomAddress();
            String username = DataGenerationHelper
                    .doNormalizeString(
                            nombre.substring(0, 3) + apellido1.substring(1, 3) + apellido2.substring(1, 2) + i)
                    .toLowerCase();
            UsuarioEntity usuario = new UsuarioEntity(nombre, apellido1, apellido2, email, telefono, direccion,
                    username,
                    "dedb63fbd1f3c4bce46a6e29be0700dda4fe2eec46c79b79fa0c5704d96e308d", true);

            usuarioRepository.save(usuario);

        }
        return usuarioRepository.count();// Devuelve el número de usuarios que hay en la base de datos

    }

    public UsuarioEntity getOneRandom() {

        Pageable oPageable = PageRequest.of((int) (Math.random() * usuarioRepository.count()), 1);
        return usuarioRepository.findAll(oPageable).getContent().get(0);
    }

    private void validateFirstLetterUppercase(String value) {
        if (value != null && !value.isEmpty()) {
            char firstChar = value.charAt(0);
            if (!Character.isLetter(firstChar) || !Character.isUpperCase(firstChar)) {
                throw new RuntimeException("La primera letra de " + value + " debe estar en mayúscula");
            }
        }
    }

    @Transactional
    public Long empty() {
        sesionService.onlyAdmins();

        usuarioRepository.deleteAll();
        usuarioRepository.resetAutoIncrement();
        UsuarioEntity oUserEntity1 = new UsuarioEntity(1L, "Ana ", "Pérez", "Roca",
                "anita@gmail.com", "632156987", "Calle del Cerezo Nº 17", "anita17", tiendaOnlinePassword, false);
        usuarioRepository.save(oUserEntity1);
        oUserEntity1 = new UsuarioEntity(2L, "Monica", "Alcañiz", "Puig", "monica@gmail.com", "642156657", "Alameda",
                "moni01", tiendaOnlinePassword, true);
        usuarioRepository.save(oUserEntity1);
        return usuarioRepository.count();
    }

}
