package kr.hs.sdhs.dimo.adapter.`in`.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class AddMemoRequestDTO(
    @field:NotBlank(message = "이메일은 필수 항목입니다.")
    @field:Email(message = "유효한 이메일 형식이 아닙니다.")
    val email: String,

    val memo : String
)