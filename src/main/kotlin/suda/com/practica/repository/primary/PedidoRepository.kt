package suda.com.practica.repository.primary

import org.springframework.data.jpa.repository.JpaRepository
import suda.com.practica.model.primary.EstadoPedido
import suda.com.practica.model.primary.Pedido

interface PedidoRepository : JpaRepository<Pedido, Long> {
    // Verifica si un cliente tiene algún pedido pendiente
    fun existsByClienteAndEstado(cliente: String, estado: EstadoPedido): Boolean
}
