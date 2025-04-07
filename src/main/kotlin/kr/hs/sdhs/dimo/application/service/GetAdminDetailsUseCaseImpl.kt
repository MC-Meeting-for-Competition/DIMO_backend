package kr.hs.sdhs.dimo.application.service

import kr.hs.sdhs.dimo.application.port.output.AdminRepositoryPort
import kr.hs.sdhs.dimo.exception.CustomException
import kr.hs.sdhs.dimo.exception.ErrorCode
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class GetAdminDetailsUseCaseImpl(
    private val adminRepositoryPort: AdminRepositoryPort
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = adminRepositoryPort.findByUsername(username) ?: throw CustomException(ErrorCode.ADMIN_NOT_FOUND)
        return User.withUsername(user.name).password(user.password).roles(user.role.name).build()
    }
}