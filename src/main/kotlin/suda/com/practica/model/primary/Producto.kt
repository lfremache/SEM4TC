package suda.com.practica.model.primary

import jakarta.persistence.*

@Entity
@Table(name = "producto")
class Producto(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false)
    var nombre: String = "",

    @Column
    var descripcion: String? = null,

    @Column(nullable = false)
    var stock: Int = 0
)
