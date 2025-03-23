package kr.hs.sdhs.dimo.adapter.`in`

import jakarta.validation.Valid
import kr.hs.sdhs.dimo.adapter.`in`.dto.EquipmentTypeRequestDTO
import kr.hs.sdhs.dimo.adapter.`in`.dto.EquipmentTypeResponseDTO
import kr.hs.sdhs.dimo.application.port.input.GetEquipmentTypeListUseCase
import kr.hs.sdhs.dimo.application.port.input.RegisterEquipmentTypeUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("equipment-types")
class EquipmentTypeController(
    private val getEquipmentTypeListUseCase: GetEquipmentTypeListUseCase,
    private val registerEquipmentTypeUseCase: RegisterEquipmentTypeUseCase
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
}