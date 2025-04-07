package kr.hs.sdhs.dimo.adapter.out

import kr.hs.sdhs.dimo.adapter.persistence.entity.AccountStatus
import kr.hs.sdhs.dimo.adapter.persistence.repository.JpaAdminRepository
import kr.hs.sdhs.dimo.application.port.output.AdminRepositoryPort
import kr.hs.sdhs.dimo.domain.Admin
import kr.hs.sdhs.dimo.exception.CustomException
import org.springframework.stereotype.Repository

@Repository
class AdminRepositoryAdapter(
    private val jpaAdminRepository: JpaAdminRepository
) : AdminRepositoryPort {
    override fun save(admin: Admin) : Admin {
        return jpaAdminRepository.save(admin.toEntity()).toDomain()
    }

    override fun findByUsername(username : String): Admin? {
        return jpaAdminRepository.findByUsername(username).map { it.toDomain() }.orElse(null)
    }

    override fun findByAdminNo(adminId: Long): Admin? {
        return jpaAdminRepository.findByAdminNo(adminId).map { it.toDomain() }.orElse(null)
    }
}