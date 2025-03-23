package kr.hs.sdhs.dimo.adapter.persistence.repository

import kr.hs.sdhs.dimo.adapter.persistence.entity.Equipment
import kr.hs.sdhs.dimo.adapter.persistence.entity.EquipmentType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional

interface JpaEquipmentRepository : JpaRepository<Equipment, Long> {
    @Query("SELECT e FROM Equipment e LEFT JOIN FETCH e.rents WHERE e.id = :id")
    override fun findById(id : Long) : Optional<Equipment>

    @Query("SELECT e FROM Equipment e LEFT JOIN FETCH e.rents")
    override fun findAll(): List<Equipment>

    @Query("SELECT e FROM Equipment e LEFT JOIN FETCH e.rents WHERE e.type.id = :id")
    fun findAllByEquipmentTypeId(id: Long): List<Equipment>
}