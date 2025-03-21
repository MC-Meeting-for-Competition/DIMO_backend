package kr.hs.sdhs.dimo.adapter.persistence.repository

import kr.hs.sdhs.dimo.adapter.persistence.entity.Equipment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional

interface JpaEquipmentRepository : JpaRepository<Equipment, Long> {
    @Query("SELECT e FROM Equipment e LEFT JOIN FETCH e.rents WHERE e.id = :id")
    override fun findById(id : Long) : Optional<Equipment>
}