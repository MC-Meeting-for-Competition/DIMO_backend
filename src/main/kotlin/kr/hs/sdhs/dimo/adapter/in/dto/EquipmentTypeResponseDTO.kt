package kr.hs.sdhs.dimo.adapter.`in`.dto

import kr.hs.sdhs.dimo.domain.EquipmentType

data class EquipmentTypeResponseDTO(
    val id : Long,
    val name : String,
) {
    companion object {
        fun fromDomain(equipmentType: EquipmentType) : EquipmentTypeResponseDTO {
            return EquipmentTypeResponseDTO(
                id = equipmentType.id,
                name = equipmentType.name,
            )
        }
    }
}