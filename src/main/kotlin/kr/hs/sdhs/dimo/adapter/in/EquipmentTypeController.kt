package kr.hs.sdhs.dimo.adapter.`in`

import kr.hs.sdhs.dimo.adapter.`in`.dto.EquipmentTypeResponseDTO
import kr.hs.sdhs.dimo.application.port.input.GetEquipmentTypeListUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/equipment-types")
class EquipmentTypeController(
    private val getEquipmentTypeListUseCase: GetEquipmentTypeListUseCase
) {
    @GetMapping("list")
    fun getEquipmentTypes(@RequestParam(required = false) name : String?): List<EquipmentTypeResponseDTO> {
        val equipmentTypeList = getEquipmentTypeListUseCase.findAll(name)
        return equipmentTypeList.map { equipmentType -> EquipmentTypeResponseDTO.fromDomain(equipmentType) }
    }
}