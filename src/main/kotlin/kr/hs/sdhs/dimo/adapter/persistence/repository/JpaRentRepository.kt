package kr.hs.sdhs.dimo.adapter.persistence.repository

import kr.hs.sdhs.dimo.adapter.persistence.entity.Rent
import kr.hs.sdhs.dimo.adapter.persistence.entity.RentStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface JpaRentRepository : JpaRepository<Rent, Long> {
    @Modifying
    @Query("UPDATE Rent r SET r.rentStatus = :status WHERE r.id = :rentId")
    fun updateStatus(@Param("status") status: RentStatus, @Param("rentId") rentId: Long): Int
}