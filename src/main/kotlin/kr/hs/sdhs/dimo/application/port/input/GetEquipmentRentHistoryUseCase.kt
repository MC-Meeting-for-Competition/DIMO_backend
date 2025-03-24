package kr.hs.sdhs.dimo.application.port.input

import kr.hs.sdhs.dimo.adapter.`in`.dto.RentResponseDTO

interface GetEquipmentRentHistoryUseCase {
    fun getEquipmentRentHistory(equipmentId : Long) : List<RentResponseDTO>
}