package kr.hs.sdhs.dimo.adapter.`in`.dto

import kr.hs.sdhs.dimo.domain.Equipment

data class EquipmentResponseDTO(
    val id: Long,
    val itemNo: Int,
    val serialNo: String?,
    val status: Int,
    val memo: String?,
) {
    companion object {
         fun fromDomain(equipment: Equipment) : EquipmentResponseDTO {
            return  EquipmentResponseDTO(
                id = equipment.id,
                itemNo = equipment.itemNo,
                serialNo = equipment.serialNo,
                status = equipment.status,
                memo = equipment.memo,
            )
         }
    }
}