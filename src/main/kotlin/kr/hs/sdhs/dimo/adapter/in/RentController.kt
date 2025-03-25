package kr.hs.sdhs.dimo.adapter.`in`

import jakarta.validation.Valid
import kr.hs.sdhs.dimo.adapter.`in`.dto.RentRequestDTO
import kr.hs.sdhs.dimo.adapter.`in`.dto.RentResponseDTO
import kr.hs.sdhs.dimo.adapter.persistence.entity.RentStatus
import kr.hs.sdhs.dimo.application.port.input.GetEquipmentRentHistoryUseCase
import kr.hs.sdhs.dimo.application.port.input.RentEquipmentUseCase
import kr.hs.sdhs.dimo.application.port.input.UpdateRentStatusUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/rent")
class RentController(
    private val rentEquipmentUseCase: RentEquipmentUseCase,
    private val updateRentStatusUseCase: UpdateRentStatusUseCase,
    private val getEquipmentRentHistoryUseCase: GetEquipmentRentHistoryUseCase
) {
    @PostMapping("/{rentId}/rent")
    fun rentEquipment(@RequestBody @Valid request: RentRequestDTO, @PathVariable rentId: Long): RentResponseDTO {
        val rent = rentEquipmentUseCase.rentEquipment(request, rentId)
        return RentResponseDTO.fromDomain(rent)
    }

    @GetMapping("equipment/{equipmentId}/history")
    fun getEquipmentRentHistory(@PathVariable equipmentId: Long) : List<RentResponseDTO> {
        return getEquipmentRentHistoryUseCase.getEquipmentRentHistory(equipmentId)
    }

    @PatchMapping("/{rentId}/return")
    fun returnEquipment(@PathVariable rentId: Long): RentResponseDTO {
        val rent = updateRentStatusUseCase.updateRentStatus(rentId, RentStatus.CHECKING)
        return RentResponseDTO.fromDomain(rent)
    }

    @PatchMapping("/{rentId}/damage")
    fun markAsDamaged(@PathVariable rentId: Long): RentResponseDTO {
        val rent = updateRentStatusUseCase.updateRentStatus(rentId, RentStatus.DAMAGED)
        return RentResponseDTO.fromDomain(rent)
    }

    @PatchMapping("/{rentId}/available")
    fun markAsReturn(@PathVariable rentId: Long): RentResponseDTO {
        val rent = updateRentStatusUseCase.updateRentStatus(rentId, RentStatus.AVAILABLE)
        return RentResponseDTO.fromDomain(rent)
    }
}