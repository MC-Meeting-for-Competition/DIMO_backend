package kr.hs.sdhs.dimo.application.port.input

import kr.hs.sdhs.dimo.adapter.`in`.dto.ChangeRentStatusRequestDTO
import kr.hs.sdhs.dimo.adapter.persistence.entity.RentStatus
import kr.hs.sdhs.dimo.domain.Rent

interface UpdateRentStatusUseCase {
    fun updateRentStatus(equipmentId : Long, request : ChangeRentStatusRequestDTO): Rent
}