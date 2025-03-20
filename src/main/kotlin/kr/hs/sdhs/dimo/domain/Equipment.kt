package kr.hs.sdhs.dimo.domain

import jakarta.persistence.*

@Entity
@Table(name = " EQUIPMENT")
data class Equipment(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equip_no")
    val id: Long = 0,

    @Column(name = "item_no", nullable = false)
    val itemNo: Int,

    @Column(name = "serial_no")
    val serialNo: Int? = null,

    @Column(name = "status", nullable = false)
    val status: Int,

    @Column(name = "memo", length = 200)
    val memo: String? = null,

    @OneToMany(mappedBy = "equipment", cascade = [CascadeType.ALL])
    val rents: List<Rent> = listOf()
)
