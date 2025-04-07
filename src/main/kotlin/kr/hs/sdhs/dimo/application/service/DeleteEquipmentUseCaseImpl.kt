package kr.hs.sdhs.dimo.application.service

import kr.hs.sdhs.dimo.adapter.persistence.entity.RentStatus
import kr.hs.sdhs.dimo.application.port.input.DeleteEquipmentUseCase
import kr.hs.sdhs.dimo.application.port.output.EquipmentRepositoryPort
import kr.hs.sdhs.dimo.exception.CustomException
import kr.hs.sdhs.dimo.exception.ErrorCode
import org.springframework.stereotype.Service

@Service
class DeleteEquipmentUseCaseImpl(
    private val equipmentRepository: EquipmentRepositoryPort
) : DeleteEquipmentUseCase {
    override fun deleteById(id: Long): Unit {
        val equipment = equipmentRepository.findById(id) ?: throw CustomException(ErrorCode.EQUIPMENT_NOT_FOUND)

        // 대여 중인 기자재라면 삭제할 수 없음
        if(equipment.status == RentStatus.RENTED) {
            throw CustomException(ErrorCode.EQUIPMENT_IS_RENTED)
        }
        equipmentRepository.deleteById(id)
    }
}