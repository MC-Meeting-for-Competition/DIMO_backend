package kr.hs.sdhs.dimo.application.port.input

import kr.hs.sdhs.dimo.domain.Admin

interface RegisterAdminUseCase {
    fun register(admin: Admin) : Admin
}