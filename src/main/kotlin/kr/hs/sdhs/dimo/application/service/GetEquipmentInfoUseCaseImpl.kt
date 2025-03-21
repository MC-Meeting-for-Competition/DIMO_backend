package kr.hs.sdhs.dimo.application.service

import kr.hs.sdhs.dimo.application.port.input.GetEquipmentInfoUseCase
import kr.hs.sdhs.dimo.application.port.output.EquipmentRepositoryPort
import kr.hs.sdhs.dimo.domain.Equipment
import kr.hs.sdhs.dimo.exception.CustomException
import kr.hs.sdhs.dimo.exception.ErrorCode
import org.springframework.stereotype.Service

@Service
class GetEquipmentInfoUseCaseImpl(
    private val equipmentRepository: EquipmentRepositoryPort
) : GetEquipmentInfoUseCase {
    override fun getEquipmentById(id: Long) : Equipment {
        return equipmentRepository.findById(id)?: throw CustomException(ErrorCode.EQUIPMENT_NOT_FOUND)
    }
}