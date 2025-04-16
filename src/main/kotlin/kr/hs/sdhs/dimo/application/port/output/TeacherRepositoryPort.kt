package kr.hs.sdhs.dimo.application.port.output

import kr.hs.sdhs.dimo.domain.Teacher
import org.springframework.data.repository.query.Param

interface TeacherRepositoryPort {
    fun findById(id: String): Teacher?
    fun save(teacher: Teacher): Teacher
    fun findByEmail(@Param("email") email : String) : Teacher?
}