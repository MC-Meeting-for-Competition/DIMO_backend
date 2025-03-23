package kr.hs.sdhs.dimo.application.service

import kr.hs.sdhs.dimo.application.port.input.DeleteEquipmentTypeUseCase
import kr.hs.sdhs.dimo.application.port.output.EquipmentRepositoryPort
import kr.hs.sdhs.dimo.application.port.output.EquipmentTypeRepositoryPort
import kr.hs.sdhs.dimo.exception.CustomException
import kr.hs.sdhs.dimo.exception.ErrorCode
import org.springframework.stereotype.Service

@Service
class DeleteEquipmentTypeUseCaseImpl(
    private val equipmentTypeRepositoryPort: EquipmentTypeRepositoryPort,
    private val equipmentRepositoryPort: EquipmentRepositoryPort
) : DeleteEquipmentTypeUseCase {
    override fun deleteEquipmentType(id: Long) : Unit {
        val hasEquipment = equipmentRepositoryPort.countByTypeId(id) > 0
        if (hasEquipment) {
            throw CustomException(ErrorCode.TYPE_HAS_EQUIPMENT)
        }

        equipmentTypeRepositoryPort.deleteById(id)
    }
}