package kr.hs.sdhs.dimo.application.port.input

import kr.hs.sdhs.dimo.domain.EquipmentType

interface RegisterEquipmentTypeUseCase {
    fun save(equipmentType: EquipmentType): EquipmentType
}