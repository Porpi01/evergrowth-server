package EverGrowth.com.EverGrowthserver.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

import EverGrowth.com.EverGrowthserver.entity.DetallePedidoEntity;
import EverGrowth.com.EverGrowthserver.entity.ValoracionEntity;
import EverGrowth.com.EverGrowthserver.service.DetallePedidoService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/detallePedido")
public class DetallePedidoAPI {

    @Autowired
    DetallePedidoService detallePedidoService;

    @GetMapping("/{id}")
    public ResponseEntity<DetallePedidoEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(detallePedidoService.get(id));
    }

    @GetMapping("/total")
    public ResponseEntity<Long> getTotalUsuarios() {
        Long totalUsuarios = detallePedidoService.getTotalDetallesPedidos();
        return ResponseEntity.ok(totalUsuarios);
    }

    @GetMapping("/pedido/{id_pedido}")
    public ResponseEntity<Page<DetallePedidoEntity>> getDetallesPorPedido(
            Pageable oPageable,
            @PathVariable("id_pedido") Long id_pedido) {
        return ResponseEntity.ok(detallePedidoService.getDetallesPorPedido(oPageable, id_pedido));
    }

    @GetMapping("/bypedido/{id_pedido}")
    public ResponseEntity<Page<DetallePedidoEntity>> getDetallesPorPedido(
            @PathVariable Long id_pedido,
            @PageableDefault(size = 20, sort = { "id" }, direction = Sort.Direction.ASC) Pageable oPageable) {
        return ResponseEntity.ok(detallePedidoService.getDetallesPorPedido(oPageable, id_pedido));
    }

    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody DetallePedidoEntity oDetallePedidoEntity) {
        return ResponseEntity.ok(detallePedidoService.create(oDetallePedidoEntity));
    }

    @PutMapping("")
    public ResponseEntity<DetallePedidoEntity> update(@RequestBody DetallePedidoEntity oDetallePedidoEntity) {
        return ResponseEntity.ok(detallePedidoService.update(oDetallePedidoEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(detallePedidoService.delete(id));
    }

    @GetMapping("")
    public ResponseEntity<Page<DetallePedidoEntity>> getPage(
            Pageable oPageable,
            @RequestParam(name = "pedido", defaultValue = "0", required = false) Long id_pedido,
            @RequestParam(name = "producto", defaultValue = "0", required = false) Long id_producto) {
        return ResponseEntity.ok(detallePedidoService.getPage(oPageable, id_pedido, id_producto));
    }

    @PostMapping("/populate/{amount}")
    public ResponseEntity<Long> populate(@PathVariable("amount") Integer amount) {
        return ResponseEntity.ok(detallePedidoService.populate(amount));
    }

    @DeleteMapping("/empty")
    public ResponseEntity<Long> empty() {
        return ResponseEntity.ok(detallePedidoService.empty());
    }

}
