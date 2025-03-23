package kr.hs.sdhs.dimo.domain

import kr.hs.sdhs.dimo.adapter.persistence.entity.EquipmentType as EquipmentTypeEntity

data class EquipmentType(
    val id: Long = 0,
    val name: String,
) {
    fun toEntity(): EquipmentTypeEntity {
        return EquipmentTypeEntity(
            id=id,
            name=name,
        )
    }
}