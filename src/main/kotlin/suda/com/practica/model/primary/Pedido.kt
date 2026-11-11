package suda.com.practica.model.primary

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

enum class EstadoPedido {
    PENDIENTE, ENVIADO, RECIBIDO
}

@Entity
@Table(name = "pedido")
class Pedido(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false)
    var cliente: String = "",

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var estado: EstadoPedido = EstadoPedido.PENDIENTE,

    @Column(name = "codigo_verificacion")
    var codigoVerificacion: String? = null,

    @OneToMany(mappedBy = "pedido", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
    var items: MutableList<ItemPedido> = mutableListOf()
)

@Entity
@Table(name = "item_pedido")
class ItemPedido(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    var pedido: Pedido? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "producto_id", nullable = false)
    var producto: Producto? = null,

    @Column(nullable = false)
    var cantidad: Int = 0
)
