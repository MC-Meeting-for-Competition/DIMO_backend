package kr.hs.sdhs.dimo.application.port.input

import kr.hs.sdhs.dimo.domain.Admin

interface GrantAdminUseCase {
    fun grantAdmin(adminId : Long) : Admin
}