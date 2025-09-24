package EverGrowth.com.EverGrowthserver.api;

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

import EverGrowth.com.EverGrowthserver.entity.UsuarioEntity;
import EverGrowth.com.EverGrowthserver.service.UsuarioService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/usuario")
public class UsuarioAPI {

    @Autowired
    UsuarioService oUsuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oUsuarioService.get(id));
    }

    @GetMapping("/total")
    public ResponseEntity<Long> getTotalUsuarios() {
        Long totalUsuarios = oUsuarioService.getTotalUsuarios();
        return ResponseEntity.ok(totalUsuarios);
    }

    @GetMapping("/byUsername/{username}")
    public ResponseEntity<UsuarioEntity> get(@PathVariable("username") String username) {
        return ResponseEntity.ok(oUsuarioService.getByUsername(username));
    }

    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody UsuarioEntity oUsuarioEntity) {
        return ResponseEntity.ok(oUsuarioService.create(oUsuarioEntity));
    }
    @PostMapping("/signup")
    public ResponseEntity<Long> signUp(@RequestBody UsuarioEntity nuevoUsuario) {
        try {
            Long userId = oUsuarioService.signUp(nuevoUsuario);
            return ResponseEntity.ok(userId);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("")
    public ResponseEntity<UsuarioEntity> update(@RequestBody UsuarioEntity oUsuarioEntity) {
        return ResponseEntity.ok(oUsuarioService.update(oUsuarioEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oUsuarioService.delete(id));
    }

    @GetMapping("")
    public ResponseEntity<Page<UsuarioEntity>> getPage(
            Pageable oPageable,
            @RequestParam(name = "filter", required = false) String strFilter) {
        return new ResponseEntity<>(oUsuarioService.getPage(oPageable, strFilter), HttpStatus.OK);
    }

    @PostMapping("/populate/{amount}")
    public ResponseEntity<Long> populate(@PathVariable("amount") Integer amount) {
        return ResponseEntity.ok(oUsuarioService.populate(amount));
    }

    @DeleteMapping("/empty")
    public ResponseEntity<Long> empty() {
        return ResponseEntity.ok(oUsuarioService.empty());
    }

}
