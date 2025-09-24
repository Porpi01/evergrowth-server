package EverGrowth.com.EverGrowthserver.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

import EverGrowth.com.EverGrowthserver.entity.CarritoEntity;
import EverGrowth.com.EverGrowthserver.entity.PedidoEntity;
import EverGrowth.com.EverGrowthserver.entity.ProductoEntity;
import EverGrowth.com.EverGrowthserver.entity.UsuarioEntity;
import EverGrowth.com.EverGrowthserver.service.PedidoService;
import EverGrowth.com.EverGrowthserver.service.ProductoService;
import EverGrowth.com.EverGrowthserver.service.UsuarioService;
import EverGrowth.com.EverGrowthserver.service.CarritoService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/pedido")
public class PedidoAPI {

    @Autowired
    private PedidoService oPedidoService;

    @Autowired
    private ProductoService ProductoService;

    @Autowired
    private UsuarioService UsuarioService;

    @Autowired
    private CarritoService CarritoService;

    @GetMapping("/{id}")
    public ResponseEntity<PedidoEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oPedidoService.get(id));
    }

    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody PedidoEntity oPedidoEntity) {
        return ResponseEntity.ok(oPedidoService.create(oPedidoEntity));
    }

 

    @PutMapping("")
    public ResponseEntity<PedidoEntity> update(@RequestBody PedidoEntity oPedidoEntity) {
        return ResponseEntity.ok(oPedidoService.update(oPedidoEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oPedidoService.delete(id));
    }

    @GetMapping("")
    public ResponseEntity<Page<PedidoEntity>> getPage(Pageable oPageable,
            @RequestParam(name = "usuario", defaultValue = "0", required = false) Long id_usuario) {
        return ResponseEntity.ok(oPedidoService.getPage(oPageable, id_usuario));
    }

    @GetMapping("/cantidadPedidosPorMes")
    public Map<String, Integer> obtenerCantidadPedidosPorMes() {
        return oPedidoService.obtenerCantidadPedidosPorMes();
    }

    @GetMapping("/total")
    public ResponseEntity<Long> getTotalUsuarios() {
        Long totalUsuarios = oPedidoService.getTotalPedidos();
        return ResponseEntity.ok(totalUsuarios);
    }
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<Page<PedidoEntity>> getComprasByUsuarioId(@PathVariable("usuarioId") Long usuarioId, Pageable oPageable) {
        return ResponseEntity.ok(oPedidoService.getComprasUsuario(usuarioId, oPageable));
    }

    @PostMapping("/populate/{cantidad}")
    public ResponseEntity<Long> populate(@PathVariable("cantidad") Integer cantidad) {
        return ResponseEntity.ok(oPedidoService.populate(cantidad));
    }

    @DeleteMapping("/empty")
    public ResponseEntity<Long> empty() {
        return ResponseEntity.ok(oPedidoService.empty());
    }

    @PostMapping("/realizarCompraUnicoCarrito/{usuarioId}/{carritoId}")
    public ResponseEntity<PedidoEntity> realizarpedidoUnicoCarrito(@PathVariable Long usuarioId,
            @PathVariable Long carritoId) {
        UsuarioEntity usuario = UsuarioService.get(usuarioId);
        CarritoEntity carrito = CarritoService.get(carritoId);

        PedidoEntity pedido = oPedidoService.realizarCompraUnicoCarrito(carrito, usuario);

        return new ResponseEntity<>(pedido, HttpStatus.CREATED);
    }

    @PostMapping("/realizarCompraTodosCarritos/{usuarioId}")
    public ResponseEntity<PedidoEntity> realizarpedidoTodosCarritos(
            @PathVariable Long usuarioId, @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        UsuarioEntity usuario = UsuarioService.get(usuarioId);
        Page<CarritoEntity> carritos = CarritoService.getCarritoByUsuario(usuarioId, PageRequest.of(page, size));
        PedidoEntity pedido = oPedidoService.realizarCompraTodosCarritos(carritos, usuario);
        return new ResponseEntity<>(pedido, HttpStatus.CREATED);
    }

    @PostMapping("/realizarCompraProducto/{productoId}/{usuarioId}/{cantidad}")
    public ResponseEntity<PedidoEntity> realizarpedidoProducto(@PathVariable Long productoId,
            @PathVariable Long usuarioId, @PathVariable int cantidad) {
        UsuarioEntity usuario = UsuarioService.get(usuarioId);
        ProductoEntity producto = ProductoService.get(productoId);
        PedidoEntity pedido = oPedidoService.realizarCompraProducto(producto, usuario, cantidad);
        return new ResponseEntity<>(pedido, HttpStatus.CREATED);
    }

    @DeleteMapping("/borrarCarrito/{carrito_id}")
    public ResponseEntity<Long> cancelarCompra(@PathVariable("carrito_id") Long carrito_id) {
        Long cancelarCompra = oPedidoService.cancelarCompra(carrito_id);
        return new ResponseEntity<>(cancelarCompra, HttpStatus.OK);
    }

}
