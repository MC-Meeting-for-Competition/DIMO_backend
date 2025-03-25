package kr.hs.sdhs.dimo.adapter.`in`

import jakarta.validation.Valid
import kr.hs.sdhs.dimo.adapter.`in`.dto.EquipmentTypeRequestDTO
import kr.hs.sdhs.dimo.adapter.`in`.dto.EquipmentTypeResponseDTO
import kr.hs.sdhs.dimo.application.port.input.DeleteEquipmentTypeUseCase
import kr.hs.sdhs.dimo.application.port.input.GetEquipmentTypeListUseCase
import kr.hs.sdhs.dimo.application.port.input.RegisterEquipmentTypeUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/equipment-types")
class EquipmentTypeController(
    private val getEquipmentTypeListUseCase: GetEquipmentTypeListUseCase,
    private val registerEquipmentTypeUseCase: RegisterEquipmentTypeUseCase,
    private val deleteEquipmentTypeUseCase: DeleteEquipmentTypeUseCase,
) {
    @GetMapping("list")
    fun getEquipmentTypes(@RequestParam(required = false) name : String?): List<EquipmentTypeResponseDTO> {
        val equipmentTypeList = getEquipmentTypeListUseCase.findAll(name)
        return equipmentTypeList.map { equipmentType -> EquipmentTypeResponseDTO.fromDomain(equipmentType) }
    }

    @PostMapping("register")
    fun registerEquipmentType(@RequestBody @Valid request : EquipmentTypeRequestDTO) : EquipmentTypeResponseDTO {
        val equipmentType = registerEquipmentTypeUseCase.save(request.toDomain())
        return EquipmentTypeResponseDTO.fromDomain(equipmentType)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) : ResponseEntity<String> {
        deleteEquipmentTypeUseCase.deleteEquipmentType(id)
        return ResponseEntity("품목 분류가 성공적으로 삭제되었습니다.", HttpStatus.OK)
    }
}