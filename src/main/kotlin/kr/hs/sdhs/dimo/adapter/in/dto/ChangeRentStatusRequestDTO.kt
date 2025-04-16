package kr.hs.sdhs.dimo.adapter.`in`.dto

import jakarta.validation.constraints.NotNull
import kr.hs.sdhs.dimo.adapter.persistence.entity.RentStatus

data class ChangeRentStatusRequestDTO(
    @field:NotNull(message="기기 상태는 필수 항목입니다")
    val currentStatus : RentStatus,

    @field:NotNull(message= "변경 상태는 필수 항목입니다")
    val newStatus : RentStatus
) {
}