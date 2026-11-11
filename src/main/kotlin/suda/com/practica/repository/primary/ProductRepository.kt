package suda.com.practica.repository.primary

import org.springframework.data.jpa.repository.JpaRepository
import suda.com.practica.model.primary.Product

interface ProductRepository : JpaRepository<Product, Int>
