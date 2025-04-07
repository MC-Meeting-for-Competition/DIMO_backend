package kr.hs.sdhs.dimo.application.service

import kr.hs.sdhs.dimo.adapter.persistence.entity.AccountStatus
import kr.hs.sdhs.dimo.application.port.input.GrantAdminUseCase
import kr.hs.sdhs.dimo.application.port.output.AdminRepositoryPort
import kr.hs.sdhs.dimo.domain.Admin
import kr.hs.sdhs.dimo.exception.CustomException
import kr.hs.sdhs.dimo.exception.ErrorCode
import org.springframework.stereotype.Service

@Service
class GrantAdminUseCaseImpl(
    private val adminRepositoryPort: AdminRepositoryPort
) : GrantAdminUseCase {
    override fun grantAdmin(adminId: Long): Admin {
        val targetAdmin = adminRepositoryPort.findByAdminNo(adminId) ?: throw CustomException(ErrorCode.ADMIN_NOT_FOUND)
        targetAdmin.accountStatus = AccountStatus.ACCEPTED

        return adminRepositoryPort.save(targetAdmin)
    }
}