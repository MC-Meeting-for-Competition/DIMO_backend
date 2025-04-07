package kr.hs.sdhs.dimo.application.port.input

import kr.hs.sdhs.dimo.adapter.persistence.entity.RentStatus
import kr.hs.sdhs.dimo.domain.Equipment

interface GetEquipmentListUseCase {
    fun findAll(equipmentTypeId: Long?, rentStatus: RentStatus?, sort: String, direction: String): List<Equipment>
}