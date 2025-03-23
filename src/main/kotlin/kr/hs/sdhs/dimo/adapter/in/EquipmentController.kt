package kr.hs.sdhs.dimo.adapter.`in`

import jakarta.validation.Valid
import kr.hs.sdhs.dimo.adapter.`in`.dto.EquipmentRequestDTO
import kr.hs.sdhs.dimo.adapter.`in`.dto.EquipmentResponseDTO
import kr.hs.sdhs.dimo.application.port.input.*
import kr.hs.sdhs.dimo.domain.Equipment
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/equipments")
class EquipmentController(
    private val getEquipmentInfoUseCase: GetEquipmentInfoUseCase,
    private val registerEquipmentUseCase: RegisterEquipmentUseCase,
    private val getEquipmentTypeUseCase: GetEquipmentTypeUseCase,
    private val getEquipmentListUseCase: GetEquipmentListUseCase
) {
    @GetMapping("/{id}")
    fun getEquipment(@PathVariable id : Long) : EquipmentResponseDTO {
        val equipment = getEquipmentInfoUseCase.getEquipmentById(id)
        return EquipmentResponseDTO.fromDomain(equipment)
    }

    @GetMapping()
    fun getEquipmentList(@RequestParam(required = false) typeId : Long?) : List<EquipmentResponseDTO> {
        val equipmentList = getEquipmentListUseCase.findAll(typeId)
        return equipmentList.map { equipment: Equipment -> EquipmentResponseDTO.fromDomain(equipment) }
    }

    @PostMapping("/register")
    fun registerEquipment(@RequestBody @Valid request : EquipmentRequestDTO) : EquipmentResponseDTO {
        val equipmentType = getEquipmentTypeUseCase.findById(request.itemNo)
        val equipment = registerEquipmentUseCase.registerEquipment(request.toDomain(equipmentType))
        return EquipmentResponseDTO.fromDomain(equipment)
    }
}