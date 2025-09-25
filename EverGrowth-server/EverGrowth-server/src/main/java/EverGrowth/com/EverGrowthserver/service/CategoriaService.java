package EverGrowth.com.EverGrowthserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import EverGrowth.com.EverGrowthserver.entity.CategoriaEntity;
import EverGrowth.com.EverGrowthserver.entity.ProductoEntity;
import EverGrowth.com.EverGrowthserver.exception.ResourceNotFoundException;
import EverGrowth.com.EverGrowthserver.helper.DataGenerationHelper;
import EverGrowth.com.EverGrowthserver.repository.CategoriaRepository;
import jakarta.transaction.Transactional;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    SesionService sesionService;

    public CategoriaEntity get(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada"));
    }

    public Long getTotalCategorias() {
        sesionService.onlyAdmins();
        return categoriaRepository.count();
    }

    public Long create(CategoriaEntity categoriaEntity) {
        sesionService.onlyAdmins();
        categoriaEntity.setId(null);
        validateFirstLetterUppercase(categoriaEntity.getNombre());
        return categoriaRepository.save(categoriaEntity).getId();
    }

    public CategoriaEntity update(CategoriaEntity categoriaEntityToSet) {
        sesionService.onlyAdmins();
        return categoriaRepository.save(categoriaEntityToSet);
    }

    public Long delete(Long id) {
        sesionService.onlyAdmins();
        if (categoriaRepository.findById(id).isPresent()) {
            categoriaRepository.deleteById(id);
            return id;
        } else {
            throw new ResourceNotFoundException("La categoría con el ID " + id + " no existe");
        }
    }

    public Page<CategoriaEntity> getPage(Pageable oPageable, String filter) {
      Page<CategoriaEntity> page;

        if (filter == null || filter.isEmpty() || filter.trim().isEmpty()) {
            page = categoriaRepository.findAll(oPageable);
        } else {
            page = categoriaRepository.findByName(filter, oPageable); // Assuming findByName exists in your repository
        }
        return page;
    }

    public Long populate(Integer amount) {
        sesionService.onlyAdmins();
        for (int i = 0; i < amount; i++) {

            CategoriaEntity categoria = new CategoriaEntity();
            categoria.setNombre(DataGenerationHelper.getRandomCategoria());
            categoria.setImagen("https://res.cloudinary.com/dtunlhsqn/image/upload/v1758785416/sqzhk65okx4snxyflwfi.jpg");
            categoriaRepository.save(categoria);

        }
        return categoriaRepository.count();
    }

    public CategoriaEntity getOneRandom() {

        Pageable oPageable = PageRequest.of((int) (Math.random() * categoriaRepository.count()), 1);
        return categoriaRepository.findAll(oPageable).getContent().get(0);
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
        categoriaRepository.deleteAll();
        categoriaRepository.resetAutoIncrement();
        categoriaRepository.flush();
        return categoriaRepository.count();
    }
}
