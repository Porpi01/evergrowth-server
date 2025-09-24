package EverGrowth.com.EverGrowthserver.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.data.web.PageableDefault;
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

import EverGrowth.com.EverGrowthserver.entity.ValoracionEntity;

import EverGrowth.com.EverGrowthserver.service.ValoracionService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/valoracion")
public class ValoracionAPI {

    @Autowired
    ValoracionService valoracionService;

    private static final int PAGE_SIZE = 10;

    @GetMapping("/{id}")
    public ResponseEntity<ValoracionEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(valoracionService.get(id));
    }

    @GetMapping("/total")
    public ResponseEntity<Long> getTotalValoraciones() {
        Long totalValoraciones = valoracionService.getTotalValoraciones();
        return ResponseEntity.ok(totalValoraciones);
    }

    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody ValoracionEntity valoracionEntity) {
        return ResponseEntity.ok(valoracionService.create(valoracionEntity));
    }

    @PutMapping("")
    public ResponseEntity<ValoracionEntity> update(@RequestBody ValoracionEntity ValoracionEntity) {
        return ResponseEntity.ok(valoracionService.update(ValoracionEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(valoracionService.delete(id));
    }


    @GetMapping("")
    public ResponseEntity<Page<ValoracionEntity>> getPage(
            Pageable oPageable,
            @RequestParam(name = "usuario",defaultValue = "0" , required=false) Long id_usuario ,
            @RequestParam(name = "producto", defaultValue = "0", required=false ) Long id_producto,
            @RequestParam(name = "filter", required = false) String strFilter) {
                return ResponseEntity.ok(valoracionService.getPage(oPageable,  strFilter, id_usuario, id_producto));
            }

    @GetMapping("/byusuario/{id}")
    public ResponseEntity<Page<ValoracionEntity>> getByUser(@PathVariable("id")  @PageableDefault(size = PAGE_SIZE, sort = {
        "id" }, direction = Sort.Direction.ASC) Long id, Pageable oPageable) {
        return ResponseEntity.ok(valoracionService.getValoracionesByUser(id, oPageable));
    }

    @GetMapping("/byproducto/{id}")
    public ResponseEntity<Page<ValoracionEntity>> getByProducto(@PathVariable("id")  @PageableDefault(size = PAGE_SIZE, sort = {
            "id" }, direction = Sort.Direction.ASC) Long id, Pageable oPageable) {
        return ResponseEntity.ok(valoracionService.getValoracionesByProducto(id, oPageable));
    }
 
    @PostMapping("/populate/{amount}")
    public ResponseEntity<Long> populate(@PathVariable("amount") Integer amount) {
        return ResponseEntity.ok(valoracionService.populate(amount));
    }

    @DeleteMapping("/empty")
    public ResponseEntity<Long> empty() {
        return ResponseEntity.ok(valoracionService.empty());
    }

}
