package kr.hs.sdhs.dimo.adapter.persistence.repository

import kr.hs.sdhs.dimo.adapter.persistence.entity.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional

interface JpaStudentRepository : JpaRepository<Student, String> {
    @Query("SELECT s FROM Student s LEFT JOIN FETCH s.rents WHERE s.email = :email")
    fun findByEmail(@Param("email") email: String): Optional<Student>
}