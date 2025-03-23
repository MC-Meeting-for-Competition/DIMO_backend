package kr.hs.sdhs.dimo.application.port.output

import kr.hs.sdhs.dimo.domain.Equipment
import org.springframework.data.domain.Sort
import org.springframework.data.repository.query.Param

interface EquipmentRepositoryPort {
    fun findById(id : Long) : Equipment?
    fun save(equipment: Equipment): Equipment
    fun findAllByFilters(
        @Param("typeId") typeId: Long?,
        @Param("rentStatus") rentStatus: Int?,
        sort: Sort
    ): List<Equipment>
}