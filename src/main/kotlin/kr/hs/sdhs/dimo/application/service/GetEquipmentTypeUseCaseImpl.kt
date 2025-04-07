package kr.hs.sdhs.dimo.application.service

import kr.hs.sdhs.dimo.application.port.input.GetEquipmentTypeUseCase
import kr.hs.sdhs.dimo.application.port.output.EquipmentTypeRepositoryPort
import kr.hs.sdhs.dimo.domain.EquipmentType
import kr.hs.sdhs.dimo.exception.CustomException
import kr.hs.sdhs.dimo.exception.ErrorCode
import org.springframework.stereotype.Service

@Service
class GetEquipmentTypeUseCaseImpl(
    private val equipmentTypeRepositoryPort: EquipmentTypeRepositoryPort
) : GetEquipmentTypeUseCase {
    override fun findById(id: Long): EquipmentType {
        return equipmentTypeRepositoryPort.findById(id)?: throw CustomException(ErrorCode.EQUIPMENT_TYPE_NOT_FOUND)
    }
}