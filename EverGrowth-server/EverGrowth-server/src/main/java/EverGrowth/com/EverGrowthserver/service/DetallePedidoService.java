package EverGrowth.com.EverGrowthserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import EverGrowth.com.EverGrowthserver.entity.DetallePedidoEntity;
import EverGrowth.com.EverGrowthserver.entity.DetallePedidoEntity;
import EverGrowth.com.EverGrowthserver.exception.ResourceNotFoundException;
import EverGrowth.com.EverGrowthserver.repository.DetallePedidoRepository;
import EverGrowth.com.EverGrowthserver.repository.PedidoRepository;
import EverGrowth.com.EverGrowthserver.repository.ProductoRepository;
import jakarta.transaction.Transactional;

@Service
public class DetallePedidoService {

    @Autowired
    DetallePedidoRepository detallePedidoRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    PedidoService PedidoService;

    @Autowired
    ProductoService ProductoService;

    @Autowired
    SesionService sesionService;

    public DetallePedidoEntity get(Long id) {
        return detallePedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Detalle pedido no encontrado"));
    }

    public Long getTotalDetallesPedidos() {
        sesionService.onlyAdmins();
        return detallePedidoRepository.count();
    }

    public Long create(DetallePedidoEntity DetallePedidoEntity) {
        sesionService.onlyAdmins();
        DetallePedidoEntity.setId(null);
        return detallePedidoRepository.save(DetallePedidoEntity).getId();
    }

    public DetallePedidoEntity update(DetallePedidoEntity DetallePedidoEntityToSet) {
        sesionService.onlyAdmins();
        return detallePedidoRepository.save(DetallePedidoEntityToSet);
    }


    public Long delete(Long id) {
        sesionService.onlyAdmins();
        if (detallePedidoRepository.findById(id).isPresent()) {
            detallePedidoRepository.deleteById(id);
            return id;
        } else {
            throw new ResourceNotFoundException("No existe un detalle pedido con " + id);
        }
    }

    public Page<DetallePedidoEntity> getDetallesPorPedido(Pageable oPageable, Long id_pedido) {
        return detallePedidoRepository.findByPedido(id_pedido, oPageable);
    }

    public Page<DetallePedidoEntity> getPage(Pageable oPageable, Long id_pedido, Long id_producto) {
        if (id_pedido == 0) {
            if (id_producto == 0) {
                return detallePedidoRepository.findAll(oPageable); 
            } else {
                return detallePedidoRepository.findByProducto(id_producto, oPageable);
            }
        } else {
            if (id_producto == 0) {
                return detallePedidoRepository.findByPedido(id_pedido, oPageable);
            } else {
                return Page.empty();
            }
        }
    }
    public Long populate(Integer amount) {
        sesionService.onlyAdmins();
        for (int i = 0; i < amount; i++) {
            DetallePedidoEntity detallePedido = new DetallePedidoEntity();
            detallePedido.setCantidad(12);
            detallePedido.setPrecio_unitario((float) 0.45);
            detallePedido.setPedidos(PedidoService.getOneRandom());
            detallePedido.setProductos(ProductoService.getOneRandom());
            detallePedido.setIva(0);

            detallePedidoRepository.save(detallePedido);
        }
        return detallePedidoRepository.count();
    }

    @Transactional
    public Long empty() {
        sesionService.onlyAdmins();
        detallePedidoRepository.deleteAll();
        detallePedidoRepository.resetAutoIncrement();
        detallePedidoRepository.flush();
        return detallePedidoRepository.count();
    }

}
