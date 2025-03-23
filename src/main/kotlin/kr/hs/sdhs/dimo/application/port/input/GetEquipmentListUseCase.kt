package kr.hs.sdhs.dimo.application.port.input

import kr.hs.sdhs.dimo.domain.Equipment

interface GetEquipmentListUseCase {
    fun findAll(equipmentTypeId : Long?) : List<Equipment>
}