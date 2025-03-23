package kr.hs.sdhs.dimo.application.service

import kr.hs.sdhs.dimo.application.port.input.RegisterEquipmentUseCase
import kr.hs.sdhs.dimo.application.port.output.EquipmentRepositoryPort
import kr.hs.sdhs.dimo.domain.Equipment
import kr.hs.sdhs.dimo.exception.CustomException
import kr.hs.sdhs.dimo.exception.ErrorCode
import org.springframework.stereotype.Service

@Service
class RegisterEquipmentUseCaseImpl(
    private val equipmentRepository: EquipmentRepositoryPort
) : RegisterEquipmentUseCase {
    override fun registerEquipment(equipment : Equipment): Equipment {
        val duplicateCount = equipmentRepository.countBySerialNoAndType(equipment.serialNo, equipment.type)
        if (duplicateCount > 0) {
            throw CustomException(ErrorCode.DUPLICATE_EQUIPMENT)
        }

        return equipmentRepository.save(equipment)
    }
}