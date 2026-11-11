package suda.com.practica.model.primary

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "product")
class Product(
    @Id
    @Column(name = "id")
    var id: Int = 0,

    @Column(name = "description")
    var description: String? = null,

    @Column(name = "price")
    var price: String? = null,

    @Column(name = "stock")
    var stock: Int? = null
)
