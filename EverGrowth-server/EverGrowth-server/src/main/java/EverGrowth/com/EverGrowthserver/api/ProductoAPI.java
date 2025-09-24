package EverGrowth.com.EverGrowthserver.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import EverGrowth.com.EverGrowthserver.entity.ProductoEntity;
import EverGrowth.com.EverGrowthserver.entity.UsuarioEntity;
import EverGrowth.com.EverGrowthserver.service.ProductoService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/producto")
public class ProductoAPI {

    @Autowired
    private ProductoService oProductoService;

    @GetMapping("masStock")
    public List<ProductoEntity> getTop10ProductosMasStock() {
        return oProductoService.getTop10ProductosMasStock();
    }

    @GetMapping("/menosStock")
    public List<ProductoEntity> getTop10ProductosMenosStock() {
        return oProductoService.getTop10ProductosMenosStock();
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductoEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oProductoService.get(id));
    }
    @GetMapping("/total")
    public ResponseEntity<Long> getTotalUsuarios() {
        Long totalUsuarios = oProductoService.getTotalProductos();
        return ResponseEntity.ok(totalUsuarios);
    }
    
    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody ProductoEntity oProductoEntity) {
        try {
            return ResponseEntity.ok(oProductoService.create(oProductoEntity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

 
@PutMapping("")
    public ResponseEntity<ProductoEntity> update(@RequestBody ProductoEntity oProductoEntity) {
        return ResponseEntity.ok(oProductoService.update(oProductoEntity));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oProductoService.delete(id));
    }

   @GetMapping("")
    public ResponseEntity<Page<ProductoEntity>> getPage(
            Pageable oPageable,
            @RequestParam(name = "categoria", defaultValue = "0", required=false ) Long id_categoria,
            @RequestParam(name = "filter", required = false) String strFilter) {
        return new ResponseEntity<>(oProductoService.getPage(oPageable, strFilter, id_categoria), HttpStatus.OK);
    }

    @PostMapping("/populate/{amount}")
    public ResponseEntity<Long> populate(@PathVariable("amount") Integer amount) {
        return ResponseEntity.ok(oProductoService.populate(amount));
    }

    @DeleteMapping("/empty")
    public ResponseEntity<Long> empty() {
        return ResponseEntity.ok(oProductoService.empty());
    }

    


}
