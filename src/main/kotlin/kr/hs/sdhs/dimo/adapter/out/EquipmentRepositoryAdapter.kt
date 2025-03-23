package kr.hs.sdhs.dimo.adapter.out

import kr.hs.sdhs.dimo.adapter.persistence.repository.JpaEquipmentRepository
import kr.hs.sdhs.dimo.application.port.output.EquipmentRepositoryPort
import kr.hs.sdhs.dimo.domain.Equipment
import kr.hs.sdhs.dimo.domain.EquipmentType
import org.springframework.data.domain.Sort
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

    override fun findAllByFilters(typeId: Long?, rentStatus: Int?, sort: Sort): List<Equipment> {
        return jpaEquipmentRepository.findAllByFilters(typeId, rentStatus, sort).map { it.toDomain() }
    }

    override fun deleteById(id: Long): Unit {
        jpaEquipmentRepository.deleteById(id)
    }

    override fun countByTypeId(typeId: Long): Long {
        return jpaEquipmentRepository.countByTypeId(typeId)
    }

    override fun countBySerialNoAndType(serialNo: String, type: EquipmentType): Long {
        return jpaEquipmentRepository.countBySerialNoAndType(serialNo, type.toEntity())
    }
}