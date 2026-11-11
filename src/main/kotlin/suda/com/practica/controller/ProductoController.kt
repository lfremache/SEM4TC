package suda.com.practica.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import suda.com.practica.model.primary.Producto
import suda.com.practica.service.ProductoService
import java.net.URI

@RestController
@RequestMapping("/api/productos")
class ProductoController(private val service: ProductoService) {

    @GetMapping
    fun listar(): ResponseEntity<List<Producto>> =
        ResponseEntity.ok(service.listar())

    @GetMapping("/{id}")
    fun buscar(@PathVariable id: Long): ResponseEntity<Producto> =
        ResponseEntity.ok(service.buscar(id))

    @PostMapping
    fun crear(@RequestBody producto: Producto): ResponseEntity<Producto> {
        val creado = service.crear(producto)
        return ResponseEntity.created(URI.create("/api/productos/${creado.id}")).body(creado)
    }

    @PutMapping("/{id}")
    fun actualizar(@PathVariable id: Long, @RequestBody producto: Producto): ResponseEntity<Producto> =
        ResponseEntity.ok(service.actualizar(id, producto))

    @DeleteMapping("/{id}")
    fun eliminar(@PathVariable id: Long): ResponseEntity<Void> {
        service.eliminar(id)
        return ResponseEntity.noContent().build()
    }
}
