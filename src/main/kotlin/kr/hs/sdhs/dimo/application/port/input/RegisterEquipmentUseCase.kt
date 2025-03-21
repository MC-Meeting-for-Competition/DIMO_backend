package kr.hs.sdhs.dimo.application.port.input

import kr.hs.sdhs.dimo.domain.Equipment

interface RegisterEquipmentUseCase {
    fun registerEquipment(equipment: Equipment) : Equipment
}