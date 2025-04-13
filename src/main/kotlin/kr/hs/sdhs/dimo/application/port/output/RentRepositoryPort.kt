package kr.hs.sdhs.dimo.application.port.output

import kr.hs.sdhs.dimo.adapter.persistence.entity.RentStatus
import kr.hs.sdhs.dimo.domain.Rent
import org.springframework.data.domain.Pageable

interface RentRepositoryPort {
    fun findById(id: Long): Rent?
    fun findAll() : MutableList<Rent>
    fun save(rent: Rent) : Rent
    fun findAllFiltered(studentId: String?, teacherId : Long?, equipmentId: Long?,rentStatus: RentStatus?, pageable: Pageable) : MutableList<Rent>
}