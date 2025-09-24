package EverGrowth.com.EverGrowthserver.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import org.springframework.data.domain.Sort;

import EverGrowth.com.EverGrowthserver.entity.CarritoEntity;

import EverGrowth.com.EverGrowthserver.entity.UsuarioEntity;
import EverGrowth.com.EverGrowthserver.exception.ResourceNotFoundException;
import EverGrowth.com.EverGrowthserver.repository.UsuarioRepository;
import EverGrowth.com.EverGrowthserver.service.CarritoService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/carrito")
public class CarritoAPI {

    @Autowired
    CarritoService carritoService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/{id}")
    public ResponseEntity<CarritoEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(carritoService.get(id));
    }

    @GetMapping("/total")
    public ResponseEntity<Long> getTotalUsuarios() {
        Long totalUsuarios = carritoService.getTotalCarritos();
        return ResponseEntity.ok(totalUsuarios);
    }

    @GetMapping("/usuario/{userId}")
    public ResponseEntity<Page<CarritoEntity>> getCarritoByUsuario(@PathVariable("userId") Long usuarioId,
            @PageableDefault(size = 20, sort = { "id" }, direction = Sort.Direction.ASC) Pageable oPageable) {
        return ResponseEntity.ok(carritoService.getCarritoByUsuario(usuarioId, oPageable));
    }

    @GetMapping("/usuario/{userId}/producto/{productoId}")
    public ResponseEntity<CarritoEntity> getCarritoByUsuarioAndProducto(@PathVariable("userId") Long usuarioId,
            @PathVariable("productoId") Long productoId) {
        return ResponseEntity.ok(carritoService.getCarritoByUsuarioAndProducto(usuarioId, productoId));
    }

    @GetMapping("/coste/{carritoId}")
    public ResponseEntity<Double> getCosteCarrito(@PathVariable("carritoId") Long carritoId) {
        return ResponseEntity.ok(carritoService.calcularCosteCarrito(carritoId));
    }

    @GetMapping("/costetotal/{userId}")
    public ResponseEntity<Double> getCosteTotalCarrito(@PathVariable("userId") Long usuarioId) {
        Pageable pageable = PageRequest.of(0, 20); // Aquí especificas la página y el tamaño de página que necesites
        Double costeTotal = carritoService.calculateTotalCartCost(pageable, usuarioId);
        return ResponseEntity.ok(costeTotal);
    }

    @GetMapping("/cantidad")
    public ResponseEntity<Long> countProductsInCart(@RequestParam("userId") Long userId) {
        UsuarioEntity user = usuarioRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con el ID: " + userId));

        Long count = carritoService.count(user);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Page<CarritoEntity>> getPage(Pageable oPageable,

            @RequestParam(name = "usuario", defaultValue = "0", required = false) Long id_usuario,
            @RequestParam(name = "producto", defaultValue = "0", required = false) Long id_producto) {
        return ResponseEntity.ok(carritoService.getPage(oPageable, id_usuario, id_producto));
    }

    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody CarritoEntity orritoEntity) {
        return ResponseEntity.ok(carritoService.create(orritoEntity));
    }

    @PostMapping("/populate/{amount}")
    public ResponseEntity<Long> populate(@PathVariable("amount") Integer amount) {
        return ResponseEntity.ok(carritoService.populate(amount));
    }

    @PutMapping("")
    public ResponseEntity<CarritoEntity> update(@RequestBody CarritoEntity CarritoEntity) {
        return ResponseEntity.ok(carritoService.update(CarritoEntity));
    }

    @DeleteMapping("/{carritoId}")
    public ResponseEntity<Long> deleteCarrito(@PathVariable("carritoId") Long carritoId) {
        return ResponseEntity.ok(carritoService.delete(carritoId));
    }

    @DeleteMapping("/usuario/{usuarioId}")
    public ResponseEntity<Long> deleteCarritoByUsuario(@PathVariable("usuarioId") Long usuarioId) {
        carritoService.deleteByUsuario(usuarioId);
        return ResponseEntity.ok(usuarioId);
    }

    @DeleteMapping("/empty")
    public ResponseEntity<Long> empty() {
        return ResponseEntity.ok(carritoService.empty());
    }

}
