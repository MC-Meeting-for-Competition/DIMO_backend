package kr.hs.sdhs.dimo.application.service

import kr.hs.sdhs.dimo.application.port.input.GetEquipmentTypeListUseCase
import kr.hs.sdhs.dimo.application.port.output.EquipmentTypeRepositoryPort
import kr.hs.sdhs.dimo.domain.EquipmentType
import org.springframework.stereotype.Service

@Service
class GetEquipmentTypeListUseCaseImpl(
    private val equipmentTypeRepositoryPort: EquipmentTypeRepositoryPort
) : GetEquipmentTypeListUseCase {
    override fun findAll(name: String?): List<EquipmentType> {
        return equipmentTypeRepositoryPort.findAll(name)
    }
}