package kr.hs.sdhs.dimo.application.service

import kr.hs.sdhs.dimo.application.port.input.AddEquipmentMemoUseCase
import kr.hs.sdhs.dimo.application.port.output.EquipmentRepositoryPort
import kr.hs.sdhs.dimo.domain.Equipment
import kr.hs.sdhs.dimo.exception.CustomException
import kr.hs.sdhs.dimo.exception.ErrorCode
import org.springframework.stereotype.Service

@Service
class AddEquipmentMemoUseCaseImpl(
    val equipmentRepositoryPort: EquipmentRepositoryPort
)  : AddEquipmentMemoUseCase {
    override fun addEquipmentMemo(id: Long, memo: String): Equipment {
        val targetEquipment = equipmentRepositoryPort.findById(id) ?: throw CustomException(ErrorCode.EQUIPMENT_NOT_FOUND)
        targetEquipment.memo = memo
        return equipmentRepositoryPort.save(targetEquipment)
    }
}