package suda.com.practica.service

import org.springframework.stereotype.Service
import suda.com.practica.model.primary.Producto
import suda.com.practica.repository.primary.ProductoRepository

@Service
class ProductoService(
    private val productoRepository: ProductoRepository
) {
    fun listar(): List<Producto> = productoRepository.findAll()

    fun buscar(id: Long): Producto =
        productoRepository.findById(id).orElseThrow {
            IllegalArgumentException("No existe el producto con id $id")
        }

    fun crear(producto: Producto): Producto = productoRepository.save(producto)

    fun actualizar(id: Long, producto: Producto): Producto {
        buscar(id)
        producto.id = id
        return productoRepository.save(producto)
    }

    fun eliminar(id: Long) {
        buscar(id)
        productoRepository.deleteById(id)
    }
}
