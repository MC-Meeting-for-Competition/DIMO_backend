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

    @Query("SELECT r FROM Rent r WHERE r.student.id = :rentId")
    fun findByStudentId(@Param("rentId") rentId: Long): List<Rent>

    @Query("SELECT r FROM Rent r WHERE r.teacher.id = :teacherId")
    fun findByTeacherId(@Param("teacherId") teacherId: Long): List<Rent>

    @Query("SELECT r FROM Rent r WHERE r.equipment.id = :equipmentId")
    fun findByEquipmentId(equipmentId: Long): List<Rent>
}