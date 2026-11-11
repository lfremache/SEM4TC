package suda.com.practica.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import suda.com.practica.model.primary.CrearPedidoRequest
import suda.com.practica.model.primary.Pedido
import suda.com.practica.model.primary.RecibirPedidoRequest
import suda.com.practica.service.PedidoService
import java.net.URI

@RestController
@RequestMapping("/api/pedidos")
class PedidoController(private val service: PedidoService) {

    // GET /api/pedidos — listar todos
    @GetMapping
    fun listar(): ResponseEntity<List<Pedido>> =
        ResponseEntity.ok(service.listar())

    // GET /api/pedidos/{id} — detalle
    @GetMapping("/{id}")
    fun buscar(@PathVariable id: Long): ResponseEntity<Pedido> =
        ResponseEntity.ok(service.buscar(id))

    // POST /api/pedidos — crear pedido
    @PostMapping
    fun crear(@RequestBody request: CrearPedidoRequest): ResponseEntity<Pedido> {
        val pedido = service.crear(request)
        return ResponseEntity.created(URI.create("/api/pedidos/${pedido.id}")).body(pedido)
    }

    // PATCH /api/pedidos/{id}/enviar — cambiar a ENVIADO
    @PatchMapping("/{id}/enviar")
    fun enviar(@PathVariable id: Long): ResponseEntity<Pedido> =
        ResponseEntity.ok(service.enviar(id))

    // PATCH /api/pedidos/{id}/recibir — cambiar a RECIBIDO con código
    @PatchMapping("/{id}/recibir")
    fun recibir(
        @PathVariable id: Long,
        @RequestBody request: RecibirPedidoRequest
    ): ResponseEntity<Pedido> =
        ResponseEntity.ok(service.recibir(id, request))
}
