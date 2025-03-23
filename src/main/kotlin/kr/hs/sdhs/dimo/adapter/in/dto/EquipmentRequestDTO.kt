package kr.hs.sdhs.dimo.adapter.`in`.dto

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.PositiveOrZero
import jakarta.validation.constraints.Size
import kr.hs.sdhs.dimo.adapter.persistence.entity.RentStatus
import kr.hs.sdhs.dimo.domain.Equipment
import kr.hs.sdhs.dimo.domain.EquipmentType

data class EquipmentRequestDTO(
    @field:NotNull(message = "품목은 필수입니다.")
    @field:PositiveOrZero(message = "품목은 0부터 시작합니다.")
    val itemNo: Long,

    val serialNo: String?,
    val status : Int,

    @field:Size(max = 200, message = "memo는 최대 200자까지 가능합니다.")
    val memo :  String?,
) {
    fun toDomain(equipmentType: EquipmentType) : Equipment {
        return Equipment(
            type = equipmentType,
            serialNo = serialNo,
            status = RentStatus.fromValue(status),
            memo = memo,
            rents = listOf()
        )
    }
}