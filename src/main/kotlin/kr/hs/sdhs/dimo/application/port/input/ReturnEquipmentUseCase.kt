package kr.hs.sdhs.dimo.application.port.input

import kr.hs.sdhs.dimo.adapter.`in`.dto.ReturnEquipmentRequestDTO
import kr.hs.sdhs.dimo.domain.Rent

interface ReturnEquipmentUseCase {
    fun returnEquipment(equipmentId : Long, request : ReturnEquipmentRequestDTO): Rent
}