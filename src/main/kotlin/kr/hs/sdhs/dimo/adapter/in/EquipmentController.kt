package kr.hs.sdhs.dimo.adapter.`in`

import jakarta.validation.Valid
import kr.hs.sdhs.dimo.adapter.`in`.dto.EquipmentRequestDTO
import kr.hs.sdhs.dimo.adapter.`in`.dto.EquipmentResponseDTO
import kr.hs.sdhs.dimo.application.port.input.GetEquipmentInfoUseCase
import kr.hs.sdhs.dimo.application.port.input.RegisterEquipmentUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/equipments")
class EquipmentController(
    private val getEquipmentInfoUseCase: GetEquipmentInfoUseCase,
    private val registerEquipmentUseCase: RegisterEquipmentUseCase
) {
    @GetMapping("/{id}")
    fun getEquipment(@PathVariable id : Long) : EquipmentResponseDTO {
        val equipment = getEquipmentInfoUseCase.getEquipmentById(id)
        return EquipmentResponseDTO.fromDomain(equipment)
    }

    @PostMapping("/register")
    fun registerEquipment(@RequestBody @Valid request : EquipmentRequestDTO) : EquipmentResponseDTO {
        val equipment = registerEquipmentUseCase.registerEquipment(request.toDomain())
        return EquipmentResponseDTO.fromDomain(equipment)
    }
}