package kr.hs.sdhs.dimo.adapter.`in`.dto

import kr.hs.sdhs.dimo.adapter.persistence.entity.RentStatus
import kr.hs.sdhs.dimo.domain.Equipment

data class EquipmentResponseDTO(
    val id: Long,
    val serialNo: String?,
    val status: RentStatus,
    val memo: String?,
    val type: String,
) {
    companion object {
         fun fromDomain(equipment: Equipment) : EquipmentResponseDTO {
            return  EquipmentResponseDTO(
                id = equipment.id,
                serialNo = equipment.serialNo,
                status = equipment.status,
                memo = equipment.memo,
                type = equipment.type.name
            )
         }
    }
}