package kr.hs.sdhs.dimo.application.service

import kr.hs.sdhs.dimo.application.port.input.DeleteEquipmentUseCase
import kr.hs.sdhs.dimo.application.port.output.EquipmentRepositoryPort
import org.springframework.stereotype.Service

@Service
class DeleteEquipmentUseCaseImpl(
    private val equipmentRepository: EquipmentRepositoryPort
) : DeleteEquipmentUseCase {
    override fun deleteById(id: Long): Unit {
        equipmentRepository.deleteById(id)
    }
}