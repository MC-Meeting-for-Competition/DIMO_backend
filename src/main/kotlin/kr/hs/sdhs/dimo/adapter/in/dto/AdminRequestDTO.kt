package kr.hs.sdhs.dimo.adapter.`in`.dto

import jakarta.validation.constraints.NotEmpty
import kr.hs.sdhs.dimo.adapter.persistence.entity.AccountStatus
import kr.hs.sdhs.dimo.adapter.persistence.entity.Role
import kr.hs.sdhs.dimo.domain.Admin

data class AdminRequestDTO(
    @field:NotEmpty(message = "이름을 필수로 입력해주세요")
    val name : String,

    @field:NotEmpty(message = "비밀번호를 필수로 입력해주세요")
    val password : String,
) {
    fun toDomain() : Admin {
        return Admin(
            id = 0,
            name = name,
            password = password,
            role = Role.ADMIN,
            accountStatus = AccountStatus.PENDING
        )
    }
}