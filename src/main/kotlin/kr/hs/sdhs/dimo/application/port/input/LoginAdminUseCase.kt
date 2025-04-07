package kr.hs.sdhs.dimo.application.port.input

import kr.hs.sdhs.dimo.domain.Admin

interface LoginAdminUseCase {
    fun login(admin: Admin) : String
}