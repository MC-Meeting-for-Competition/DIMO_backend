package kr.hs.sdhs.dimo.adapter.`in`.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import kr.hs.sdhs.dimo.adapter.persistence.entity.Gender
import kr.hs.sdhs.dimo.adapter.persistence.entity.RentStatus
import kr.hs.sdhs.dimo.domain.Student

data class StudentRequestDTO(
    @field:NotBlank(message = "이름은 필수 항목입니다.")
    val name: String,

    @field:NotBlank(message = "전화번호는 필수 항목입니다.")
    @field:Size(min = 10, max = 13, message = "전화번호는 10자 이상, 13자 이하로 입력해야 합니다.")
    val phone: String,

    @field:NotNull(message = "학번을 입력해주세요")
    val studentNo: Long,

    @field:NotBlank(message = "이메일은 필수 항목입니다.")
    @field:Email(message = "유효한 이메일 형식이 아닙니다.")
    val email: String,

    val rentStatus: RentStatus = RentStatus.AVAILABLE,

    @field:Size(max = 200, message = "대여 메모는 최대 200자까지 입력할 수 있습니다.")
    val rentMemo: String?,

    @field:NotNull(message = "정책 동의 여부는 필수 항목입니다.")
    val policy: Boolean,

    @field:NotNull(message = "성별은 필수 항목입니다.")
    val gender: Gender
) {
    fun toDomain() : Student {
        return Student(
            id = studentNo,
            name = name,
            phone = phone,
            email = email,
            rentMemo = rentMemo,
            policy = policy,
            gender = gender
        )
    }
}