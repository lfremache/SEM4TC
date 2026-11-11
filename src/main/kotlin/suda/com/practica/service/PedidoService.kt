package suda.com.practica.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import suda.com.practica.model.primary.*
import suda.com.practica.repository.primary.PedidoRepository
import suda.com.practica.repository.primary.ProductoRepository
import java.util.UUID

@Service
class PedidoService(
    private val pedidoRepository: PedidoRepository,
    private val productoRepository: ProductoRepository
) {

    // Listar todos los pedidos
    fun listar(): List<Pedido> = pedidoRepository.findAll()

    // Buscar pedido por id
    fun buscar(id: Long): Pedido =
        pedidoRepository.findById(id).orElseThrow {
            IllegalArgumentException("No existe el pedido con id $id")
        }

    // Crear un nuevo pedido
    @Transactional
    fun crear(request: CrearPedidoRequest): Pedido {
        // Regla: cliente no puede tener otro pedido PENDIENTE
        if (pedidoRepository.existsByClienteAndEstado(request.cliente, EstadoPedido.PENDIENTE))
            throw IllegalStateException("El cliente '${request.cliente}' ya tiene un pedido pendiente")

        val pedido = Pedido(cliente = request.cliente)

        for (itemReq in request.items) {
            // Regla: el producto debe existir
            val producto = productoRepository.findById(itemReq.productoId).orElseThrow {
                IllegalArgumentException("No existe el producto con id ${itemReq.productoId}")
            }

            // Regla: stock suficiente
            if (producto.stock < itemReq.cantidad)
                throw IllegalStateException(
                    "Stock insuficiente para '${producto.nombre}'. Disponible: ${producto.stock}, solicitado: ${itemReq.cantidad}"
                )

            // Bajar el stock
            producto.stock -= itemReq.cantidad
            productoRepository.save(producto)

            val item = ItemPedido(pedido = pedido, producto = producto, cantidad = itemReq.cantidad)
            pedido.items.add(item)
        }

        return pedidoRepository.save(pedido)
    }

    // Marcar como ENVIADO (solo si está PENDIENTE)
    @Transactional
    fun enviar(id: Long): Pedido {
        val pedido = buscar(id)

        if (pedido.estado != EstadoPedido.PENDIENTE)
            throw IllegalStateException("Solo se puede enviar un pedido en estado PENDIENTE. Estado actual: ${pedido.estado}")

        // Generar código de verificación al enviar
        pedido.codigoVerificacion = UUID.randomUUID().toString().take(8).uppercase()
        pedido.estado = EstadoPedido.ENVIADO

        return pedidoRepository.save(pedido)
    }

    // Marcar como RECIBIDO (solo si está ENVIADO y el código es correcto)
    @Transactional
    fun recibir(id: Long, request: RecibirPedidoRequest): Pedido {
        val pedido = buscar(id)

        // Regla: debe estar en estado ENVIADO
        if (pedido.estado != EstadoPedido.ENVIADO)
            throw IllegalStateException("Solo se puede recibir un pedido en estado ENVIADO. Estado actual: ${pedido.estado}")

        // Regla: código de verificación correcto
        if (pedido.codigoVerificacion != request.codigoVerificacion)
            throw IllegalArgumentException("Código de verificación incorrecto")

        pedido.estado = EstadoPedido.RECIBIDO

        return pedidoRepository.save(pedido)
    }
}
