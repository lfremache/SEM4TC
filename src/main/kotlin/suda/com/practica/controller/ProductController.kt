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
import suda.com.practica.model.primary.Product
import suda.com.practica.service.ProductService
import java.net.URI

@RestController
@RequestMapping("/productos")
class ProductController(
    private val productService: ProductService
) {
    // GET /productos — lista todos (DB primaria)
    @GetMapping
    fun listar(): ResponseEntity<List<Product>> =
        ResponseEntity.ok(productService.listarProductos())

    // GET /productos/{id}
    @GetMapping("/{id}")
    fun buscar(@PathVariable id: Int): ResponseEntity<Product> {
        val product = productService.buscarProducto(id)
            ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(product)
    }

    // POST /productos
    @PostMapping
    fun crear(@RequestBody product: Product): ResponseEntity<Product> {
        val guardado = productService.guardarProducto(product)
        return ResponseEntity.created(URI.create("/productos/${guardado.id}")).body(guardado)
    }

    // PUT /productos/{id}
    @PutMapping("/{id}")
    fun actualizar(@PathVariable id: Int, @RequestBody product: Product): ResponseEntity<Product> {
        productService.buscarProducto(id) ?: return ResponseEntity.notFound().build()
        product.id = id
        return ResponseEntity.ok(productService.guardarProducto(product))
    }

    // DELETE /productos/{id}
    @DeleteMapping("/{id}")
    fun eliminar(@PathVariable id: Int): ResponseEntity<Void> {
        productService.buscarProducto(id) ?: return ResponseEntity.notFound().build()
        productService.eliminarProducto(id)
        return ResponseEntity.noContent().build()
    }
}
