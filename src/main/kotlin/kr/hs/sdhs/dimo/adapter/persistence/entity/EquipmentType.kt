package kr.hs.sdhs.dimo.adapter.persistence.entity

import jakarta.persistence.*
import kr.hs.sdhs.dimo.domain.EquipmentType as EquipmentTypeDomain

@Entity
@Table(name = "equipment_type")
data class EquipmentType(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_no")
    val id: Long = 0,

    @Column(name = "item_name", nullable = false, length = 230)
    val name: String = ""
) {
    fun toDomain() :EquipmentTypeDomain {
        return EquipmentTypeDomain(
            id = this.id,
            name = this.name
        )
    }
}