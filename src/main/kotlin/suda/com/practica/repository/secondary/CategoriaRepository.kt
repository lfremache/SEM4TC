package suda.com.practica.repository.secondary

import org.springframework.data.jpa.repository.JpaRepository
import suda.com.practica.model.secondary.Categoria

interface CategoriaRepository : JpaRepository<Categoria, Int>
