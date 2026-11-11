package suda.com.practica.model.secondary

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "categoria")
class Categoria(
    @Id
    @Column(name = "id")
    var id: Int = 0,

    @Column(name = "nombre")
    var nombre: String? = null,

    @Column(name = "descripcion")
    var descripcion: String? = null
)
