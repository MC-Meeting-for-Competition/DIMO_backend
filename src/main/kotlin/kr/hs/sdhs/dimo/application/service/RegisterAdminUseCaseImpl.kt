package kr.hs.sdhs.dimo.application.service

import kr.hs.sdhs.dimo.application.port.input.RegisterAdminUseCase
import kr.hs.sdhs.dimo.application.port.output.AdminRepositoryPort
import kr.hs.sdhs.dimo.domain.Admin
import kr.hs.sdhs.dimo.exception.CustomException
import kr.hs.sdhs.dimo.exception.ErrorCode
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class RegisterAdminUseCaseImpl(
    private val adminRepositoryPort: AdminRepositoryPort,
    private val passwordEncoder: PasswordEncoder
) : RegisterAdminUseCase {
    override fun register(admin: Admin): Admin {
        if(adminRepositoryPort.findByUsername(admin.name) != null) throw CustomException(ErrorCode.EXIST_ADMIN)

        val encodedPassword = passwordEncoder.encode(admin.password)
        admin.password = encodedPassword

        return adminRepositoryPort.save(admin)
    }
}