package kr.hs.sdhs.dimo.adapter.`in`

import jakarta.validation.Valid
import kr.hs.sdhs.dimo.adapter.`in`.dto.RentRequestDTO
import kr.hs.sdhs.dimo.adapter.`in`.dto.RentResponseDTO
import kr.hs.sdhs.dimo.adapter.persistence.entity.RentStatus
import kr.hs.sdhs.dimo.application.port.input.RentEquipmentUseCase
import kr.hs.sdhs.dimo.application.port.input.UpdateRentStatusUseCase
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rent")
class RentController(
    private val rentEquipmentUseCase: RentEquipmentUseCase,
    private val updateRentStatusUseCase: UpdateRentStatusUseCase,
) {
    @PostMapping("/{rentId}/rent")
    fun rentEquipment(@RequestBody @Valid request: RentRequestDTO, @PathVariable rentId: Long): RentResponseDTO {
        val rent = rentEquipmentUseCase.rentEquipment(request, rentId)
        return RentResponseDTO.fromDomain(rent)
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