package kr.hs.sdhs.dimo.application.port.input

import kr.hs.sdhs.dimo.domain.EquipmentType

interface GetEquipmentTypeListUseCase {
    fun findAll(name : String?) : List<EquipmentType>
}