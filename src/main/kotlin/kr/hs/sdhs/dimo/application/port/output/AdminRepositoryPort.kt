package kr.hs.sdhs.dimo.application.port.output

import kr.hs.sdhs.dimo.adapter.persistence.entity.AccountStatus
import kr.hs.sdhs.dimo.domain.Admin


interface AdminRepositoryPort {
    fun save(admin: Admin) : Admin
    fun findByUsername(username : String): Admin?
    fun findByAdminNo(adminId : Long) : Admin?
}