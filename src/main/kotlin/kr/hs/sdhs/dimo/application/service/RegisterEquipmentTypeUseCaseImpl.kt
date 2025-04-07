package kr.hs.sdhs.dimo.application.service

import kr.hs.sdhs.dimo.application.port.input.RegisterEquipmentTypeUseCase
import kr.hs.sdhs.dimo.application.port.output.EquipmentTypeRepositoryPort
import kr.hs.sdhs.dimo.domain.EquipmentType
import org.springframework.stereotype.Service

@Service
class RegisterEquipmentTypeUseCaseImpl(
    private val equipmentTypeRepositoryPort: EquipmentTypeRepositoryPort
) : RegisterEquipmentTypeUseCase {
    override fun save(equipmentType: EquipmentType): EquipmentType {
        return equipmentTypeRepositoryPort.save(equipmentType)
    }
}