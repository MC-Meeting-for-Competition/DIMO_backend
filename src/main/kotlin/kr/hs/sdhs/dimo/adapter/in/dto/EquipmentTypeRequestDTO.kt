package kr.hs.sdhs.dimo.adapter.`in`.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import kr.hs.sdhs.dimo.domain.EquipmentType

data class EquipmentTypeRequestDTO(
    @field:NotEmpty(message = "기기 종류는 빈 칸일 수 없습니다")
    @field:NotBlank(message = "기기 종류는 빈 칸일 수 없습니다")
    val name : String,
) {
    fun toDomain() : EquipmentType {
        return EquipmentType(
            name = name
        )
    }
   }