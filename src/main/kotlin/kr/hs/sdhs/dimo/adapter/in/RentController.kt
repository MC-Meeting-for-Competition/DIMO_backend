package kr.hs.sdhs.dimo.adapter.`in`

import jakarta.validation.Valid
import kr.hs.sdhs.dimo.adapter.`in`.dto.RentRequestDTO
import kr.hs.sdhs.dimo.adapter.`in`.dto.RentResponseDTO
import kr.hs.sdhs.dimo.adapter.persistence.entity.RentStatus
import kr.hs.sdhs.dimo.application.port.input.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/rent")
class RentController(
    private val rentEquipmentUseCase: RentEquipmentUseCase,
    private val updateRentStatusUseCase: UpdateRentStatusUseCase,
    private val getRentListUseCase: GetRentListUseCase,
) {
    @PostMapping("/{rentId}/rent")
    fun rentEquipment(@RequestBody @Valid request: RentRequestDTO, @PathVariable rentId: Long): RentResponseDTO {
        val rent = rentEquipmentUseCase.rentEquipment(request, rentId)
        return RentResponseDTO.fromDomain(rent)
    }

    @PatchMapping("/{rentId}/return")
    fun markIsPending(@PathVariable rentId: Long): RentResponseDTO {
        val rent = updateRentStatusUseCase.updateRentStatus(rentId, RentStatus.CHECKING)
        return RentResponseDTO.fromDomain(rent)
    }

    @GetMapping
    fun getRentList(
        @RequestParam(required = false) studentId: String?,
        @RequestParam(required = false) teacherId: String?,
        @RequestParam(required = false) equipmentId: Long?,
        @RequestParam(required = false) rentStatus: Int?,
        @RequestParam(defaultValue = "id") sort: String,
        @RequestParam(defaultValue = "asc") direction: String,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): List<RentResponseDTO> {
        return getRentListUseCase.getRentList(studentId, teacherId, equipmentId, rentStatus, sort, direction, page, size)
    }
}