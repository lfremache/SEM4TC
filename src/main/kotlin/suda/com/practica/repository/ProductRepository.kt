package suda.com.practica.repository

import org.springframework.data.jpa.repository.JpaRepository
import suda.com.practica.model.Product

interface ProductRepository : JpaRepository<Product, Int>
