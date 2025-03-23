package kr.hs.sdhs.dimo.application.port.output

import kr.hs.sdhs.dimo.domain.Equipment

interface EquipmentRepositoryPort {
    fun findById(id : Long) : Equipment?
    fun save(equipment: Equipment): Equipment
    fun findAll(equipmentTypeId: Long?) : List<Equipment>
}