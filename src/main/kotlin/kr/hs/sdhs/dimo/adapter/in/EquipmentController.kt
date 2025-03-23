package kr.hs.sdhs.dimo.adapter.`in`

import jakarta.validation.Valid
import kr.hs.sdhs.dimo.adapter.`in`.dto.EquipmentRequestDTO
import kr.hs.sdhs.dimo.adapter.`in`.dto.EquipmentResponseDTO
import kr.hs.sdhs.dimo.adapter.persistence.entity.RentStatus
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

    @GetMapping
    fun getEquipmentList(
        @RequestParam(required = false) typeId: Long?,
        @RequestParam(required = false) rentStatus: Int?,
        @RequestParam(defaultValue = "id") sort: String,
        @RequestParam(defaultValue = "asc") direction: String
    ): List<EquipmentResponseDTO> {
        val statusEnum = rentStatus?.let {
            try {
                RentStatus.fromValue(it)
            } catch (e: NoSuchElementException) {
                throw IllegalArgumentException("잘못된 rentStatus 값입니다: $it")
            }
        }

        val equipmentList = getEquipmentListUseCase.findAll(typeId, statusEnum, sort, direction)
        return equipmentList.map { equipment -> EquipmentResponseDTO.fromDomain(equipment) }
    }


    @PostMapping("/register")
    fun registerEquipment(@RequestBody @Valid request : EquipmentRequestDTO) : EquipmentResponseDTO {
        val equipmentType = getEquipmentTypeUseCase.findById(request.itemNo)
        val equipment = registerEquipmentUseCase.registerEquipment(request.toDomain(equipmentType))
        return EquipmentResponseDTO.fromDomain(equipment)
    }
}