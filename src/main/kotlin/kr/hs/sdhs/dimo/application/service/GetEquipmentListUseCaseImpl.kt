package kr.hs.sdhs.dimo.application.service

import kr.hs.sdhs.dimo.application.port.input.GetEquipmentListUseCase
import kr.hs.sdhs.dimo.application.port.output.EquipmentRepositoryPort
import kr.hs.sdhs.dimo.domain.Equipment
import org.springframework.stereotype.Service

@Service
class GetEquipmentListUseCaseImpl(
    private val equipmentRepository: EquipmentRepositoryPort
) : GetEquipmentListUseCase {
    override fun findAll(equipmentTypeId : Long?) : List<Equipment> {
        return equipmentRepository.findAll(equipmentTypeId)
    }
}