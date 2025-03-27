package kr.hs.sdhs.dimo.application.service

import kr.hs.sdhs.dimo.adapter.persistence.entity.AccountStatus
import kr.hs.sdhs.dimo.application.port.input.LoginAdminUseCase
import kr.hs.sdhs.dimo.application.port.output.AdminRepositoryPort
import kr.hs.sdhs.dimo.config.security.JwtUtil
import kr.hs.sdhs.dimo.domain.Admin
import kr.hs.sdhs.dimo.exception.CustomException
import kr.hs.sdhs.dimo.exception.ErrorCode
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class LoginAdminUseCaseImpl(
    private val adminRepositoryPort: AdminRepositoryPort,
    private val passwordEncoder: PasswordEncoder,
    private val jwtUtil: JwtUtil
) : LoginAdminUseCase {
    override fun login(admin : Admin): String {
        val foundAdmin = adminRepositoryPort.findByUsername(admin.name) ?: throw CustomException(ErrorCode.WRONG_USERNAME_OR_PASSWORD)
        if(foundAdmin.accountStatus != AccountStatus.ACCEPTED) throw CustomException(ErrorCode.NOT_ACCEPTED_ADMIN_ACCOUNT)
        if (!passwordEncoder.matches(admin.password, foundAdmin.password)) {
            throw CustomException(ErrorCode.WRONG_USERNAME_OR_PASSWORD)
        }
        return jwtUtil.generateToken(foundAdmin.name)
    }
}