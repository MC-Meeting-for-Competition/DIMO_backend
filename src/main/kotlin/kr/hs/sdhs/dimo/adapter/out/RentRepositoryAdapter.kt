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
        studentId: String?,
        teacherId: String?,
        equipmentId: Long?,
        rentStatus: RentStatus?,
        pageable: Pageable
    ): MutableList<Rent> {
        return jpaRentRepository.findAllFiltered(studentId, teacherId, equipmentId, rentStatus, pageable).map { it.toDomain() }.toMutableList()
    }
}