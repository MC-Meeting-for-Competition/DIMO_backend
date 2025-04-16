package kr.hs.sdhs.dimo.adapter.out

import kr.hs.sdhs.dimo.adapter.persistence.entity.RentStatus
import kr.hs.sdhs.dimo.adapter.persistence.repository.JpaRentRepository
import kr.hs.sdhs.dimo.application.port.output.RentRepositoryPort
import kr.hs.sdhs.dimo.domain.Rent
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class RentRepositoryAdapter(private val jpaRentRepository : JpaRentRepository) :RentRepositoryPort {
    override fun findById(id: Long): Rent? {
        return jpaRentRepository.findById(id).map { it.toDomain() }.orElse(null)
    }

    override fun findAll(): MutableList<Rent> {
        TODO("Not yet implemented")
    }

    override fun save(rent: Rent): Rent {
        return jpaRentRepository.save(rent.toEntity()).toDomain()
    }

    override fun findAllFiltered(
        studentEmail: String?,
        teacherEmail: String?,
        equipmentId: Long?,
        rentStatus: RentStatus?,
        pageable: Pageable
    ): MutableList<Rent> {
        return jpaRentRepository.findAllFiltered(studentEmail, teacherEmail, equipmentId, rentStatus, pageable).map { it.toDomain() }.toMutableList()
    }

    override fun findByEquipmentIdAndRentStatus(
        equipmentId: Long,
        status: RentStatus
    ): MutableList<Rent> {
        return jpaRentRepository.findByEquipmentIdAndRentStatus(equipmentId, status).map { it.toDomain()}.toMutableList()
    }
}