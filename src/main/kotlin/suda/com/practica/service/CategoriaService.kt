package suda.com.practica.service

import org.springframework.stereotype.Service
import suda.com.practica.model.secondary.Categoria
import suda.com.practica.repository.secondary.CategoriaRepository

@Service
class CategoriaService(
    private val categoriaRepository: CategoriaRepository
) {
    fun listar(): List<Categoria> = categoriaRepository.findAll()

    fun buscar(id: Int): Categoria? = categoriaRepository.findById(id).orElse(null)

    fun guardar(categoria: Categoria): Categoria = categoriaRepository.save(categoria)

    fun eliminar(id: Int) = categoriaRepository.deleteById(id)
}
