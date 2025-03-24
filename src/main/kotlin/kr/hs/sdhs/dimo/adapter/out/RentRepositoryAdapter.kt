package kr.hs.sdhs.dimo.adapter.out

import kr.hs.sdhs.dimo.adapter.persistence.repository.JpaRentRepository
import kr.hs.sdhs.dimo.application.port.output.RentRepositoryPort
import kr.hs.sdhs.dimo.domain.Rent
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
}