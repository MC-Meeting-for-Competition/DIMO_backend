package kr.hs.sdhs.dimo.application.port.input

import kr.hs.sdhs.dimo.domain.EquipmentType

interface GetEquipmentTypeUseCase {
    fun findById(id : Long) : EquipmentType
}