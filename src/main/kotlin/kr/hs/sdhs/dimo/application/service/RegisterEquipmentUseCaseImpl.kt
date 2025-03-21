package kr.hs.sdhs.dimo.application.service

import kr.hs.sdhs.dimo.application.port.input.RegisterEquipmentUseCase
import kr.hs.sdhs.dimo.application.port.output.EquipmentRepositoryPort
import kr.hs.sdhs.dimo.domain.Equipment
import org.springframework.stereotype.Service

@Service
class RegisterEquipmentUseCaseImpl(
    private val equipmentRepository: EquipmentRepositoryPort
) : RegisterEquipmentUseCase {
    override fun registerEquipment(equipment : Equipment): Equipment {
        return equipmentRepository.save(equipment)
    }
}