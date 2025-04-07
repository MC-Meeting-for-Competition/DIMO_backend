package kr.hs.sdhs.dimo.adapter.persistence.repository

import kr.hs.sdhs.dimo.adapter.persistence.entity.EquipmentType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface JpaEquipmentTypeRepository : JpaRepository<EquipmentType, Long> {
    override fun findById(id: Long): Optional<EquipmentType>

    override fun findAll(): MutableList<EquipmentType>

    @Query("SELECT et FROM EquipmentType et WHERE LOWER(et.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    fun searchEquipmentType(@Param("name") name: String): MutableList<EquipmentType>
}