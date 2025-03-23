package kr.hs.sdhs.dimo.application.port.output

import kr.hs.sdhs.dimo.domain.Student
import org.springframework.data.repository.query.Param
import java.util.Optional

interface StudentRepositoryPort {
    fun save(student: Student) : Student
    fun findById(id: Long): Student?
    fun findAll() : MutableList<Student>
    fun findByEmail(@Param("email") email : String) : Student?
}