package suda.com.practica.service

import org.springframework.stereotype.Service
import suda.com.practica.model.primary.Product
import suda.com.practica.repository.primary.ProductRepository

@Service
class ProductService(
    private val productRepository: ProductRepository
) {
    fun saludoServicio(): String = "Hola mundo desde el servicio"

    fun listarProductos(): List<Product> = productRepository.findAll()

    fun buscarProducto(id: Int): Product? = productRepository.findById(id).orElse(null)

    fun guardarProducto(product: Product): Product = productRepository.save(product)

    fun eliminarProducto(id: Int) = productRepository.deleteById(id)
}
