package kr.hs.sdhs.dimo.adapter.`in`

import jakarta.validation.Valid
import kr.hs.sdhs.dimo.adapter.`in`.dto.RentRequestDTO
import kr.hs.sdhs.dimo.adapter.`in`.dto.RentResponseDTO
import kr.hs.sdhs.dimo.application.port.input.RentEquipmentUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rent")
class RentController(
    private val rentEquipmentUseCase: RentEquipmentUseCase
) {
    @PostMapping
    fun rentEquipment(@RequestBody @Valid request : RentRequestDTO) : RentResponseDTO {
        val rent = rentEquipmentUseCase.rentEquipment(request)
        return RentResponseDTO.fromDomain(rent)
    }
}