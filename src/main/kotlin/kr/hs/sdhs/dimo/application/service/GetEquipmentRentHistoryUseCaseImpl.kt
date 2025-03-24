package kr.hs.sdhs.dimo.application.service

import kr.hs.sdhs.dimo.adapter.`in`.dto.RentResponseDTO
import kr.hs.sdhs.dimo.application.port.input.GetEquipmentRentHistoryUseCase
import kr.hs.sdhs.dimo.application.port.output.RentRepositoryPort
import org.springframework.stereotype.Service

@Service
class GetEquipmentRentHistoryUseCaseImpl(
    private val rentRepositoryPort: RentRepositoryPort,
) : GetEquipmentRentHistoryUseCase {
    override fun getEquipmentRentHistory(equipmentId: Long): List<RentResponseDTO> {
        return rentRepositoryPort.findAllByEquipmentId(equipmentId).map { rent -> RentResponseDTO.fromDomain(rent) }
    }
}