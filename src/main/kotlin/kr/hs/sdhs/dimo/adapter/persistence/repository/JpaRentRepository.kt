package kr.hs.sdhs.dimo.adapter.persistence.repository

import kr.hs.sdhs.dimo.adapter.persistence.entity.Rent
import kr.hs.sdhs.dimo.adapter.persistence.entity.RentStatus
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface JpaRentRepository : JpaRepository<Rent, Long> {
    @Modifying
    @Query("UPDATE Rent r SET r.rentStatus = :status WHERE r.id = :rentId")
    fun updateStatus(@Param("status") status: RentStatus, @Param("rentId") rentId: Long): Int

    @Query(
        """SELECT r FROM Rent r 
           WHERE (:studentId IS NULL OR r.student.id = :studentId)
           AND (:teacherId IS NULL OR r.teacher.id = :teacherId)
           AND (:equipmentId IS NULL OR r.equipment.id = :equipmentId)
           AND (:rentStatus IS NULL OR r.rentStatus = :rentStatus)"""
    )
    fun findAllFiltered(
        @Param("studentId") studentId: String?,
        @Param("teacherId") teacherId: String?,
        @Param("equipmentId") equipmentId: Long?,
        @Param("rentStatus") rentStatus: RentStatus?,
        pageable: Pageable
    ): Page<Rent>
}