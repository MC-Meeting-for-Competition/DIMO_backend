package kr.hs.sdhs.dimo.adapter.`in`.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import kr.hs.sdhs.dimo.domain.Rent

data class ReturnEquipmentRequestDTO(
    @field:NotBlank(message = "학생 또는 선생님의 구분이 필요합니다.")
    val userType: String,  // "student" 또는 "teacher"로 구분

    @field:NotBlank(message = "이메일을 입력해주세요.")
    @field:Email(message = "이메일 형식이 올바르지 않아요.")
    val email : String,
)