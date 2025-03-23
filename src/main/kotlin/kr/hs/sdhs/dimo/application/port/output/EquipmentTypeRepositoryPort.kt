package kr.hs.sdhs.dimo.application.port.output

import kr.hs.sdhs.dimo.domain.EquipmentType

interface EquipmentTypeRepositoryPort {
    fun findById(id: Long): EquipmentType?
    fun findAll(name : String?): MutableList<EquipmentType>
    fun save(equipmentType: EquipmentType): EquipmentType
}