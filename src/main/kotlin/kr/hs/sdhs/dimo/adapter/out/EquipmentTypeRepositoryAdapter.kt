package kr.hs.sdhs.dimo.adapter.out

import kr.hs.sdhs.dimo.adapter.persistence.repository.JpaEquipmentTypeRepository
import kr.hs.sdhs.dimo.application.port.output.EquipmentTypeRepositoryPort
import kr.hs.sdhs.dimo.domain.EquipmentType
import org.springframework.stereotype.Repository

@Repository
class EquipmentTypeRepositoryAdapter(private val jpaEquipmentTypeRepository : JpaEquipmentTypeRepository) :
    EquipmentTypeRepositoryPort {
    override fun findById(id: Long): EquipmentType? {
        return jpaEquipmentTypeRepository.findById(id).map { it.toDomain() }.orElse(null)
    }

    override fun findAll(name : String?): MutableList<EquipmentType> {
        return if(name.isNullOrBlank()) {
            jpaEquipmentTypeRepository.findAll().map { it.toDomain() }.toMutableList()
        } else {
            jpaEquipmentTypeRepository.searchEquipmentType(name).map { it.toDomain() }.toMutableList()
        }
    }

    override fun save(equipmentType: EquipmentType): EquipmentType {
        return jpaEquipmentTypeRepository.save(equipmentType.toEntity()).toDomain()
    }

    override fun deleteById(id: Long) : Unit {
        jpaEquipmentTypeRepository.deleteById(id)
    }

}