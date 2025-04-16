package kr.hs.sdhs.dimo.application.service

import jakarta.transaction.Transactional
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
    override fun updateRentStatus(rentId : Long, status: RentStatus): Rent {
        val rent = rentRepository.findById(rentId) ?: throw CustomException(ErrorCode.RENT_NOT_FOUND)
        val equipment = equipmentRepository.findById(rent.equipment.id) ?: throw CustomException(ErrorCode.EQUIPMENT_NOT_FOUND)

        if(rent.isAlreadyProcessed(status)) {
            throw CustomException(ErrorCode.ALREADY_PROCESSED)
        }
        if(status == RentStatus.RENTED) {
            if(!rent.canBeReturned()) throw CustomException(ErrorCode.RETURN_NOT_ACCEPT)
            rent.isReturn = true
        }

        rent.updateStatus(status)
        equipment.updateStatus(status)

        rentRepository.save(rent)
        equipmentRepository.save(equipment)

        return rent
    }
}