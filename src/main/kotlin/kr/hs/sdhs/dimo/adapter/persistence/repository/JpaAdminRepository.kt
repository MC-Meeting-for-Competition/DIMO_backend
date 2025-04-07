package kr.hs.sdhs.dimo.adapter.persistence.repository

import kr.hs.sdhs.dimo.adapter.persistence.entity.AccountStatus
import kr.hs.sdhs.dimo.adapter.persistence.entity.Admin
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface JpaAdminRepository : JpaRepository<Admin, Long> {
    @Query("SELECT a from Admin a WHERE a.name = :username")
    fun findByUsername(@Param("username") username : String) : Optional<Admin>

    @Query("SELECT a from Admin a WHERE a.id = :adminId")
    fun findByAdminNo(@Param("adminId") adminId: Long): Optional<Admin>
}