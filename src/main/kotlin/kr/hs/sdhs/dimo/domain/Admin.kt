package kr.hs.sdhs.dimo.domain

import kr.hs.sdhs.dimo.adapter.persistence.entity.AccountStatus
import kr.hs.sdhs.dimo.adapter.persistence.entity.Admin
import kr.hs.sdhs.dimo.adapter.persistence.entity.Role

data class Admin(
    val id: Long,
    val name: String,
    var password: String,
    val role: Role,
    var accountStatus: AccountStatus
) {
    fun changeStatus(accountStatus: AccountStatus) {
        this.accountStatus = accountStatus
    }

    fun toEntity(): Admin {
        return Admin(
            id = id,
            name = name,
            password = password,
            role = role,
            accountStatus = accountStatus
        )
    }
}
