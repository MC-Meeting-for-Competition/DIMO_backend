package kr.hs.sdhs.dimo.adapter.`in`.dto

import kr.hs.sdhs.dimo.adapter.persistence.entity.AccountStatus
import kr.hs.sdhs.dimo.domain.Admin

data class AdminResponseDTO(
    val id : Long,
    val name : String,
    val accountStatus : AccountStatus,
) {
    companion object {
        fun fromDomain(admin : Admin) : AdminResponseDTO {
            return AdminResponseDTO(
                id = admin.id,
                name = admin.name,
                accountStatus = admin.accountStatus,
            )
        }
    }
}