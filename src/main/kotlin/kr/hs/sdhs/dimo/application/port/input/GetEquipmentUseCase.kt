package kr.hs.sdhs.dimo.application.port.input

import kr.hs.sdhs.dimo.domain.Equipment

interface GetEquipmentInfoUseCase {
    fun getEquipmentById(id : Long) : Equipment
}