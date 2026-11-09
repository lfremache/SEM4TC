package suda.com.practica.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import suda.com.practica.model.secondary.Categoria
import suda.com.practica.service.CategoriaService
import java.net.URI

@RestController
@RequestMapping("/categorias")
class CategoriaController(private val service: CategoriaService) {

    // GET /categorias — lista todas (DB secundaria)
    @GetMapping
    fun listar(): ResponseEntity<List<Categoria>> =
        ResponseEntity.ok(service.listar())

    // GET /categorias/{id}
    @GetMapping("/{id}")
    fun buscar(@PathVariable id: Int): ResponseEntity<Categoria> {
        val categoria = service.buscar(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(categoria)
    }

    // POST /categorias
    @PostMapping
    fun crear(@RequestBody categoria: Categoria): ResponseEntity<Categoria> {
        val guardada = service.guardar(categoria)
        return ResponseEntity.created(URI.create("/categorias/${guardada.id}")).body(guardada)
    }

    // PUT /categorias/{id}
    @PutMapping("/{id}")
    fun actualizar(@PathVariable id: Int, @RequestBody categoria: Categoria): ResponseEntity<Categoria> {
        service.buscar(id) ?: return ResponseEntity.notFound().build()
        categoria.id = id
        return ResponseEntity.ok(service.guardar(categoria))
    }

    // DELETE /categorias/{id}
    @DeleteMapping("/{id}")
    fun eliminar(@PathVariable id: Int): ResponseEntity<Void> {
        service.buscar(id) ?: return ResponseEntity.notFound().build()
        service.eliminar(id)
        return ResponseEntity.noContent().build()
    }
}
