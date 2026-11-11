package suda.com.practica.model.primary

// Request para crear un pedido
data class CrearPedidoRequest(
    val cliente: String,
    val items: List<ItemRequest>
)

data class ItemRequest(
    val productoId: Long,
    val cantidad: Int
)

// Request para marcar como recibido
data class RecibirPedidoRequest(
    val codigoVerificacion: String
)
