package kr.hs.sdhs.dimo.adapter.persistence.repository

import kr.hs.sdhs.dimo.adapter.persistence.entity.Equipment
import kr.hs.sdhs.dimo.adapter.persistence.entity.EquipmentType
import kr.hs.sdhs.dimo.adapter.persistence.entity.RentStatus
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface JpaEquipmentRepository : JpaRepository<Equipment, Long> {
    @Query("SELECT e FROM Equipment e LEFT JOIN FETCH e.rents WHERE e.id = :id")
    override fun findById(id : Long) : Optional<Equipment>

    @Query("SELECT e FROM Equipment e LEFT JOIN FETCH e.rents")
    override fun findAll(): List<Equipment>

    @Query(
        "SELECT e FROM Equipment e LEFT JOIN FETCH e.rents " +
                "WHERE (:typeId IS NULL OR e.type.id = :typeId) " +
                "AND (:rentStatus IS NULL OR e.status = :rentStatus)"
    )
    fun findAllByFilters(
        @Param("typeId") typeId: Long?,
        @Param("rentStatus") rentStatus: RentStatus?,
        sort: Sort
    ): List<Equipment>

    override fun deleteById(id: Long)

    @Query("SELECT COUNT(e) FROM Equipment e WHERE e.type.id = :typeId")
    fun countByTypeId(@Param("typeId") typeId: Long): Long

    @Query("""
        SELECT COUNT(e) 
        FROM Equipment e 
        WHERE e.serialNo = :serialNo AND e.type = :type
    """)
    fun countBySerialNoAndType(serialNo: String, type: EquipmentType): Long

    @Modifying
    @Query("UPDATE Equipment e SET e.status = :status WHERE e.id = :equipmentId")
    fun updateStatus(@Param("equipmentId") equipmentId: Long, status: RentStatus): Int
}