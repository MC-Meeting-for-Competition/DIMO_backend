package kr.hs.sdhs.dimo.application.service

import jakarta.transaction.Transactional
import kr.hs.sdhs.dimo.adapter.`in`.dto.ChangeRentStatusRequestDTO
import kr.hs.sdhs.dimo.adapter.persistence.entity.RentStatus
import kr.hs.sdhs.dimo.application.port.input.UpdateRentStatusUseCase
import kr.hs.sdhs.dimo.application.port.output.EquipmentRepositoryPort
import kr.hs.sdhs.dimo.application.port.output.RentRepositoryPort
import kr.hs.sdhs.dimo.domain.Rent
import kr.hs.sdhs.dimo.exception.CustomException
import kr.hs.sdhs.dimo.exception.ErrorCode
import org.springframework.stereotype.Service

@Service
class UpdateRentStatusUseCaseImpl(
    private val rentRepository : RentRepositoryPort,
    private val equipmentRepository : EquipmentRepositoryPort
) : UpdateRentStatusUseCase {
    @Transactional
    override fun updateRentStatus(equipmentId : Long, request : ChangeRentStatusRequestDTO): Rent {
//        val rent = rentRepository.findById(rentId) ?: throw CustomException(ErrorCode.RENT_NOT_FOUND)
        val equipment = equipmentRepository.findById(equipmentId) ?: throw CustomException(ErrorCode.EQUIPMENT_NOT_FOUND)
        val rentList = rentRepository.findByEquipmentIdAndRentStatus(equipmentId, request.currentStatus)

        if(rentList.isEmpty()) throw CustomException(ErrorCode.RENT_NOT_FOUND)
        val rent = rentList.first()

        if(rent.isAlreadyProcessed(request.newStatus)) {
            throw CustomException(ErrorCode.ALREADY_PROCESSED)
        }
        if(request.newStatus == RentStatus.RENTED) {
            if(!rent.canBeReturned()) throw CustomException(ErrorCode.RETURN_NOT_ACCEPT)
            rent.isReturn = true
        }

        rent.updateStatus(request.newStatus)
        equipment.updateStatus(request.newStatus)

        rentRepository.save(rent)
        equipmentRepository.save(equipment)

        return rent
    }
}