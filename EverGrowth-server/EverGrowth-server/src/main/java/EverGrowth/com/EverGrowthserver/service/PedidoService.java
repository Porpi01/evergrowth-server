package EverGrowth.com.EverGrowthserver.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import EverGrowth.com.EverGrowthserver.entity.CarritoEntity;
import EverGrowth.com.EverGrowthserver.entity.DetallePedidoEntity;
import EverGrowth.com.EverGrowthserver.entity.PedidoEntity;
import EverGrowth.com.EverGrowthserver.entity.ProductoEntity;
import EverGrowth.com.EverGrowthserver.entity.UsuarioEntity;
import EverGrowth.com.EverGrowthserver.exception.ResourceNotFoundException;
import EverGrowth.com.EverGrowthserver.helper.DataGenerationHelper;
import EverGrowth.com.EverGrowthserver.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import EverGrowth.com.EverGrowthserver.repository.DetallePedidoRepository;
import EverGrowth.com.EverGrowthserver.repository.PedidoRepository;

@Service
public class PedidoService {

    private Long contadorFactura = 0L;

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    UsuarioRepository UsuarioRepository;

    @Autowired
    UsuarioService UsuarioService;

    @Autowired
    SesionService sesionService;

    @Autowired
    ProductoService oProductoService;

    @Autowired
    DetallePedidoRepository detallePedidoRepository;

    @Autowired
    CarritoService oCarritoService;

    public PedidoEntity get(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pedido not found"));
    }

    public Long create(PedidoEntity oPedidoEntity) {
        oPedidoEntity.setId(null);
        return pedidoRepository.save(oPedidoEntity).getId();
    }

    public Long getTotalPedidos() {
        sesionService.onlyAdmins();
        return pedidoRepository.count();
    }

    public PedidoEntity update(PedidoEntity oPedidoEntityToSet) {
        sesionService.onlyAdmins();
        return pedidoRepository.save(oPedidoEntityToSet);
    }

    public Long delete(Long id) {
        if (pedidoRepository.findById(id).isPresent()) {
            pedidoRepository.deleteById(id);
            return id;
        } else {
            throw new ResourceNotFoundException("El pedido con el ID " + id + " no existe");
        }
    }

    public Page<PedidoEntity> getPage(Pageable oPageable, Long id_usuario) {
        sesionService.onlyAdminsOrUsers();

        if (id_usuario == 0) {
            return pedidoRepository.findAll(oPageable);
        } else {
            return pedidoRepository.findByUser(id_usuario, oPageable);
        }
    }

    public Long populate(Integer cantidad) {
        sesionService.onlyAdmins();

        // Variable booleana para alternar entre dos y tres días
        boolean alternar = false;

        for (int i = 0; i < cantidad; i++) {
            PedidoEntity pedido = new PedidoEntity();
            pedido.setUser(UsuarioService.getOneRandom());
            pedido.setEstado_pedido(false);

            // Obtener la fecha actual
            LocalDateTime fechaActual = LocalDateTime.now();

            // Calcular la cantidad de días para la entrega (dos o tres días alternando)
            int diasEntrega = alternar ? 3 : 2;

            // Calcular la fecha de entrega sumando los días correspondientes
            LocalDateTime fechaEntrega = fechaActual.plusDays(diasEntrega);

            pedido.setFecha_entrega(fechaEntrega);
            pedido.setFecha_pedido(fechaActual);

            pedidoRepository.save(pedido);

            // Alternar el valor booleano para el próximo pedido
            alternar = !alternar;
        }
        return pedidoRepository.count();
    }

    public PedidoEntity getOneRandom() {

        Pageable oPageable = PageRequest.of((int) (Math.random() * pedidoRepository.count()), 1);
        return pedidoRepository.findAll(oPageable).getContent().get(0);
    }

    @Transactional
    public Long empty() {

        sesionService.onlyAdmins();
        pedidoRepository.deleteAll();
        pedidoRepository.resetAutoIncrement();
        pedidoRepository.flush();
        return pedidoRepository.count();
    }

    @Transactional
    public PedidoEntity realizarCompraProducto(ProductoEntity oProductoEntity, UsuarioEntity oUsuarioEntity,
            int cantidad) {
    
        sesionService.onlyAdminsOrUsersWithIisOwnData(oUsuarioEntity.getId());
    
        PedidoEntity oPedidoEntity = new PedidoEntity();
    
        oPedidoEntity.setUser(oUsuarioEntity);
        oPedidoEntity.setFecha_pedido(LocalDateTime.now());
        oPedidoEntity.setEstado_pedido(false);
    
        // Obtener la fecha actual
        LocalDateTime fechaActual = LocalDateTime.now();
    
        // Variable booleana para alternar entre dos y tres días
        boolean alternar = (fechaActual.getDayOfMonth() % 2 == 0); // Alternar basado en el día del mes actual
    
        // Calcular la cantidad de días para la entrega (dos o tres días alternando)
        int diasEntrega = alternar ? 3 : 2;
        
        // Calcular la fecha de entrega sumando los días correspondientes
        LocalDateTime fechaEntrega = fechaActual.plusDays(diasEntrega);
    
        oPedidoEntity.setFecha_entrega(fechaEntrega);
    
        pedidoRepository.save(oPedidoEntity);
    
        DetallePedidoEntity oDetallePedidoEntity = new DetallePedidoEntity();
        oDetallePedidoEntity.setId(null);
        oDetallePedidoEntity.setPrecio_unitario(oProductoEntity.getprecio());
        oDetallePedidoEntity.setCantidad(cantidad);
        oDetallePedidoEntity.setPedidos(oPedidoEntity);
        oDetallePedidoEntity.setProductos(oProductoEntity);
        oDetallePedidoEntity.setIva(oProductoEntity.getIva());
    
        detallePedidoRepository.save(oDetallePedidoEntity);
    
        oProductoService.actualizarStock(oProductoEntity, cantidad);
    
        return oPedidoEntity;
    }

    @Transactional
    public PedidoEntity realizarCompraUnicoCarrito(CarritoEntity oCarritoEntity, UsuarioEntity oUsuarioEntity) {
        sesionService.onlyAdminsOrUsersWithIisOwnData(oUsuarioEntity.getId());
    
        PedidoEntity oPedidoEntity = new PedidoEntity();
    
        oPedidoEntity.setUser(oUsuarioEntity);
        oPedidoEntity.setFecha_pedido(LocalDateTime.now());
        oPedidoEntity.setEstado_pedido(false);
    
        // Obtener la fecha actual
        LocalDateTime fechaActual = LocalDateTime.now();
    
        // Variable booleana para alternar entre dos y tres días
        boolean alternar = (fechaActual.getDayOfMonth() % 2 == 0); // Alternar basado en el día del mes actual
    
        // Calcular la cantidad de días para la entrega (dos o tres días alternando)
        int diasEntrega = alternar ? 3 : 2;
        
        // Calcular la fecha de entrega sumando los días correspondientes
        LocalDateTime fechaEntrega = fechaActual.plusDays(diasEntrega);
    
        oPedidoEntity.setFecha_entrega(fechaEntrega);
    
        pedidoRepository.save(oPedidoEntity);
    
        DetallePedidoEntity oDetallePedidoEntity = new DetallePedidoEntity();
        oDetallePedidoEntity.setId(null);
        oDetallePedidoEntity.setPrecio_unitario(oCarritoEntity.getProducto().getprecio());
        oDetallePedidoEntity.setCantidad(oCarritoEntity.getCantidad());
        oDetallePedidoEntity.setPedidos(oPedidoEntity);
        oDetallePedidoEntity.setIva(oCarritoEntity.getProducto().getIva());
        oDetallePedidoEntity.setProductos(oCarritoEntity.getProducto());
    
        detallePedidoRepository.save(oDetallePedidoEntity);
        ProductoEntity producto = oCarritoEntity.getProducto();
        oProductoService.actualizarStock(producto, oCarritoEntity.getCantidad());
    
        oCarritoService.delete(oCarritoEntity.getId());
    
        return oPedidoEntity;
    }
    
    @Transactional
    public PedidoEntity realizarCompraTodosCarritos(Page<CarritoEntity> carritos, UsuarioEntity oUsuarioEntity) {
        sesionService.onlyAdminsOrUsersWithIisOwnData(oUsuarioEntity.getId());
    
        PedidoEntity oPedidoEntity = new PedidoEntity();
    
        oPedidoEntity.setUser(oUsuarioEntity);
        oPedidoEntity.setFecha_pedido(LocalDateTime.now());
        oPedidoEntity.setEstado_pedido(false);
    
        // Obtener la fecha actual
        LocalDateTime fechaActual = LocalDateTime.now();
    
        // Variable booleana para alternar entre dos y tres días
        boolean alternar = (fechaActual.getDayOfMonth() % 2 == 0); // Alternar basado en el día del mes actual
    
        // Calcular la cantidad de días para la entrega (dos o tres días alternando)
        int diasEntrega = alternar ? 3 : 2;
        
        // Calcular la fecha de entrega sumando los días correspondientes
        LocalDateTime fechaEntrega = fechaActual.plusDays(diasEntrega);
    
        oPedidoEntity.setFecha_entrega(fechaEntrega);
    
        // Generar el código de factura
        Long codigoFactura = generarCodigoFactura();
    
        // Asignar el código de factura al pedido
        oPedidoEntity.setId_factura(codigoFactura);
    
        // Guardar el pedido en la base de datos
        pedidoRepository.save(oPedidoEntity);
    
        // Procesar cada carrito
        carritos.forEach(carrito -> {
            DetallePedidoEntity oDetallePedidoEntity = new DetallePedidoEntity();
            oDetallePedidoEntity.setId(null);
            oDetallePedidoEntity.setPrecio_unitario(carrito.getProducto().getprecio());
            oDetallePedidoEntity.setCantidad(carrito.getCantidad());
            oDetallePedidoEntity.setPedidos(oPedidoEntity);
            oDetallePedidoEntity.setProductos(carrito.getProducto());
            oDetallePedidoEntity.setIva(carrito.getProducto().getIva());
            detallePedidoRepository.save(oDetallePedidoEntity);
    
            // Actualizar el stock del producto
            ProductoEntity producto = carrito.getProducto();
            oProductoService.actualizarStock(producto, carrito.getCantidad());
        });
    
        // Eliminar los carritos del usuario
        oCarritoService.deleteByUsuario(oUsuarioEntity.getId());
    
        // Retornar el pedido que contiene el código de factura generado
        return oPedidoEntity;
    }

    private Long generarCodigoFactura() {
        Long ultimoCodigo = pedidoRepository.findMaxCodigoFactura();
        if (ultimoCodigo == null) {
            contadorFactura = 1L;
        } else {
            contadorFactura = ultimoCodigo + 1L;
        }
        return contadorFactura;
    }

    public Long cancelarCompra(Long id) {
        PedidoEntity pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Error: Compra no encontrada."));
        sesionService.onlyAdminsOrUsersWithIisOwnData(pedido.getUser().getId());
        if (pedidoRepository.existsById(id)) {
            Page<DetallePedidoEntity> detallesCompra = detallePedidoRepository.findByPedido(id,
                    PageRequest.of(0, 1000));
            for (DetallePedidoEntity detalleCompra : detallesCompra) {
                ProductoEntity productos = detalleCompra.getProductos();
                int cantidad = detalleCompra.getCantidad();
                oProductoService.actualizarStock(productos, -cantidad);
            }
            detallePedidoRepository.deleteAll(detallesCompra);
            pedidoRepository.deleteById(id);
            return id;
        } else {
            throw new ResourceNotFoundException("Error: La compra no existe.");
        }
    }

    // Encontrar a las compras de un usuario
    public Page<PedidoEntity> getComprasUsuario(Long usuario_id, Pageable oPageable) {
        sesionService.onlyAdminsOrUsersWithIisOwnData(usuario_id);
        return pedidoRepository.findByUser(usuario_id, oPageable);
    }

    public Map<String, Integer> obtenerCantidadPedidosPorMes() {
        Map<String, Integer> cantidadPedidosPorMes = new LinkedHashMap<>();

        // Crear un array con los nombres de los meses en el orden correcto
        String[] mesesOrdenados = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" };

        // Iterar sobre cada mes para obtener la cantidad de pedidos
        for (int mes = 1; mes <= 12; mes++) {
            // Realizar la consulta para obtener los pedidos por mes
            List<PedidoEntity> pedidos = pedidoRepository.findByMes(mes);
            // Contar la cantidad de pedidos para este mes
            int cantidadPedidos = pedidos.size();
            // Agregar la cantidad de pedidos al mapa
            cantidadPedidosPorMes.put(mesesOrdenados[mes - 1], cantidadPedidos);
        }

        return cantidadPedidosPorMes;
    }
}
