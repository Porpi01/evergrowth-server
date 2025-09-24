package EverGrowth.com.EverGrowthserver.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import EverGrowth.com.EverGrowthserver.entity.ValoracionEntity;

import EverGrowth.com.EverGrowthserver.exception.ResourceNotFoundException;
import EverGrowth.com.EverGrowthserver.helper.DataGenerationHelper;
import EverGrowth.com.EverGrowthserver.repository.ProductoRepository;
import EverGrowth.com.EverGrowthserver.repository.UsuarioRepository;
import EverGrowth.com.EverGrowthserver.repository.ValoracionRepository;
import jakarta.transaction.Transactional;


@Service
public class ValoracionService {

    @Autowired
    ValoracionRepository valoracionRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    UsuarioService UsuarioService;

    @Autowired
    ProductoService ProductoService;

    @Autowired
    SesionService sesionService;

    public ValoracionEntity get(Long id) {
        return valoracionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Valoracion no encontrada"));
    }

    public Long getTotalValoraciones() {
        sesionService.onlyAdmins();
        return valoracionRepository.count();
    }

    public Long create(ValoracionEntity ValoracionEntity) {
        sesionService.onlyAdminsOrUsersWithIisOwnData(sesionService.getSessionUser().getId());
        ValoracionEntity.setId(null);
        validateFirstLetterUppercase(ValoracionEntity.getTitulo());
        validateFirstLetterUppercase(ValoracionEntity.getMensaje());
        return valoracionRepository.save(ValoracionEntity).getId();
    }

    public ValoracionEntity update(ValoracionEntity ValoracionEntityToSet) {
        sesionService.onlyAdminsOrUsersWithIisOwnData(ValoracionEntityToSet.getId());
        validateFirstLetterUppercase(ValoracionEntityToSet.getTitulo());
        validateFirstLetterUppercase(ValoracionEntityToSet.getMensaje());
        return valoracionRepository.save(ValoracionEntityToSet);
    }

    public Long delete(Long id) {
        ValoracionEntity ValoracionEntity = this.get(id);
        sesionService.onlyAdminsOrUsersWithIisOwnData(ValoracionEntity.getUser().getId());
        if (valoracionRepository.findById(id).isPresent()) {
            valoracionRepository.deleteById(id);
            return id;
        } else {
            throw new ResourceNotFoundException("No existe una valoracion con " + id);
        }
    }


    public Page<ValoracionEntity> getPage(Pageable oPageable, String filter, Long id_usuario, Long id_producto) {

        if (id_usuario == 0) {
            if (id_producto == 0) {
                if (filter == null || filter.isEmpty()) {
                    return valoracionRepository.findAll(oPageable);
                } else {
                    return valoracionRepository.findByMensajeOrTitulo(filter, filter, oPageable);
                }
            } else {

                return valoracionRepository.findByProducto(id_producto, oPageable);
            }
        } else {
            if (id_producto == 0) {
                return valoracionRepository.findByUser(id_usuario, oPageable);
            } else {

                return Page.empty();
            }
        }
    }

  

    public Page<ValoracionEntity> getValoracionesByUser(Long id_usuario, Pageable oPageable) {
        return valoracionRepository.findByUser(id_usuario, oPageable);
    }

    public Page<ValoracionEntity> getValoracionesByProducto(Long id_producto, Pageable oPageable) {
        return valoracionRepository.findByProducto(id_producto, oPageable);
    }

    public Long populate(Integer amount) {

        sesionService.onlyAdmins();

        for (int i = 0; i < amount; i++) {
            ValoracionEntity valoracion = new ValoracionEntity();
            valoracion.setTitulo(DataGenerationHelper.getSpeech(1));
            valoracion.setFecha(LocalDateTime.now());
            valoracion.setMensaje(DataGenerationHelper.getSpeech(1));
            valoracion.setUser(UsuarioService.getOneRandom());
            valoracion.setProducto(ProductoService.getOneRandom());

            valoracionRepository.save(valoracion);
        }
        return valoracionRepository.count();
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
        valoracionRepository.deleteAll();
        valoracionRepository.resetAutoIncrement();
        valoracionRepository.flush();
        return valoracionRepository.count();
    }

}
