package suda.com.practica.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import suda.com.practica.service.ProductService

@RestController
class ProductController(
    private val productService: ProductService
) {
    @GetMapping("/hola")
    fun saludo(): String = "Hola mundo"

    @GetMapping("/adios")
    fun despedida(): String = "Adios mundo"

    @GetMapping("/SaludoServicio")
    fun saludoServicio(): String = productService.saludoServicio()
}
