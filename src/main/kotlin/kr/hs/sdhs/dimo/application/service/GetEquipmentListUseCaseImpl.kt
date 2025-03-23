package kr.hs.sdhs.dimo.application.service

import kr.hs.sdhs.dimo.adapter.persistence.entity.RentStatus
import kr.hs.sdhs.dimo.application.port.input.GetEquipmentListUseCase
import kr.hs.sdhs.dimo.application.port.output.EquipmentRepositoryPort
import kr.hs.sdhs.dimo.domain.Equipment
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class GetEquipmentListUseCaseImpl(
    private val equipmentRepository: EquipmentRepositoryPort
) : GetEquipmentListUseCase {
    override fun findAll(equipmentTypeId: Long?, rentStatus: RentStatus?, sort: String, direction: String): List<Equipment> {
        val sortDirection = if (direction.lowercase() == "desc") Sort.Direction.DESC else Sort.Direction.ASC
        val sortBy = Sort.by(sortDirection, sort)

        return equipmentRepository.findAllByFilters(equipmentTypeId, rentStatus?.value, sortBy)
    }

}