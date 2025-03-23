package kr.hs.sdhs.dimo.application.port.output

import kr.hs.sdhs.dimo.domain.Rent

interface RentRepositoryPort {
    fun findById(id: Long): Rent?
    fun findAll() : MutableList<Rent>
    fun save(rent: Rent) : Rent
}