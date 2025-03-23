package kr.hs.sdhs.dimo.adapter.`in`

import jakarta.validation.Valid
import kr.hs.sdhs.dimo.adapter.`in`.dto.EquipmentRequestDTO
import kr.hs.sdhs.dimo.adapter.`in`.dto.EquipmentResponseDTO
import kr.hs.sdhs.dimo.adapter.persistence.entity.RentStatus
import kr.hs.sdhs.dimo.application.port.input.*
import kr.hs.sdhs.dimo.exception.CustomException
import kr.hs.sdhs.dimo.exception.ErrorCode
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/equipments")
class EquipmentController(
    private val getEquipmentInfoUseCase: GetEquipmentInfoUseCase,
    private val registerEquipmentUseCase: RegisterEquipmentUseCase,
    private val getEquipmentTypeUseCase: GetEquipmentTypeUseCase,
    private val getEquipmentListUseCase: GetEquipmentListUseCase,
    private val deleteEquipmentUseCase: DeleteEquipmentUseCase
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

    @DeleteMapping("/{id}")
    fun deleteEquipment(@PathVariable id : Long):  ResponseEntity<String> {
        val equipment =  getEquipmentInfoUseCase.getEquipmentById(id)

        // 대여 중인 기자재라면 삭제할 수 없음
        if(equipment.status == RentStatus.RENTED) {
            throw CustomException(ErrorCode.EQUIPMENT_IS_RENTED)
        }

        deleteEquipmentUseCase.deleteById(equipment.id)
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다")
    }

    @PostMapping("/register")
    fun registerEquipment(@RequestBody @Valid request : EquipmentRequestDTO) : EquipmentResponseDTO {
        val equipmentType = getEquipmentTypeUseCase.findById(request.itemNo)
        val equipment = registerEquipmentUseCase.registerEquipment(request.toDomain(equipmentType))
        return EquipmentResponseDTO.fromDomain(equipment)
    }
}