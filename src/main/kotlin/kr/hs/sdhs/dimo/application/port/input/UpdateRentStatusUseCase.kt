package kr.hs.sdhs.dimo.application.port.input

import kr.hs.sdhs.dimo.adapter.persistence.entity.RentStatus
import kr.hs.sdhs.dimo.domain.Rent

interface UpdateRentStatusUseCase {
    fun updateRentStatus(rentId : Long, status: RentStatus): Rent
}