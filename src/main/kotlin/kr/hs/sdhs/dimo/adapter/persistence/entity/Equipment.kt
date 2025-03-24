package kr.hs.sdhs.dimo.adapter.persistence.entity

import jakarta.persistence.*
import kr.hs.sdhs.dimo.domain.Equipment

@Entity
@Table(name = "EQUIPMENT")
data class Equipment(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equip_no")
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "item_no", nullable = false)
    val type: EquipmentType,

    @Column(name = "serial_no")
    val serialNo: String,

    @Column(name = "status", nullable = false)
    var status: RentStatus,

    @Column(name = "memo", length = 200)
    val memo: String? = null,

    @OneToMany(mappedBy = "equipment", cascade = [CascadeType.ALL])
    val rents: List<Rent> = listOf()
) {
    fun toDomain(): Equipment {
        return Equipment(
            id = this.id,
            type = type.toDomain(),
            serialNo = this.serialNo,
            status = this.status,
            memo = this.memo,
            rents = this.rents.map { it.toDomain() } // Rent 도메인 모델로 변환
        )
    }
}
