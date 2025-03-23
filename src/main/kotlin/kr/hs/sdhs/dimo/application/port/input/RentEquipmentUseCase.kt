package kr.hs.sdhs.dimo.application.port.input

import kr.hs.sdhs.dimo.adapter.`in`.dto.RentRequestDTO
import kr.hs.sdhs.dimo.domain.Rent

interface RentEquipmentUseCase {
    fun rentEquipment(rent : RentRequestDTO): Rent
}