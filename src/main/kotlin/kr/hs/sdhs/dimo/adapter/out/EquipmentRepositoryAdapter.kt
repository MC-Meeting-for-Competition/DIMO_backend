package kr.hs.sdhs.dimo.adapter.out

import kr.hs.sdhs.dimo.adapter.persistence.repository.JpaEquipmentRepository
import kr.hs.sdhs.dimo.application.port.output.EquipmentRepositoryPort
import kr.hs.sdhs.dimo.domain.Equipment
import org.springframework.stereotype.Repository

@Repository
class EquipmentRepositoryAdapter(
    private val jpaEquipmentRepository : JpaEquipmentRepository
) : EquipmentRepositoryPort {
    override fun findById(id: Long) : Equipment? {
        return jpaEquipmentRepository.findById(id).map { it.toDomain() }.orElse(null)
    }

    override fun save(equipment: Equipment): Equipment {
        return jpaEquipmentRepository.save(equipment.toEntity()).toDomain()
    }
}