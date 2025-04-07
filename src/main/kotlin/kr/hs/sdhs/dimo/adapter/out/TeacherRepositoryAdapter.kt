package kr.hs.sdhs.dimo.adapter.out

import kr.hs.sdhs.dimo.adapter.persistence.repository.JpaTeacherRepository
import kr.hs.sdhs.dimo.application.port.output.TeacherRepositoryPort
import kr.hs.sdhs.dimo.domain.Teacher
import org.springframework.stereotype.Repository

@Repository
class TeacherRepositoryAdapter(
    private val jpaTeacherRepository : JpaTeacherRepository
) : TeacherRepositoryPort {
    override fun findById(id: Long): Teacher? {
        return jpaTeacherRepository.findById(id).map { it.toDomain() }.orElse(null)
    }

    override fun save(teacher: Teacher): Teacher {
        return jpaTeacherRepository.save(teacher.toEntity()).toDomain()
    }

    override fun findByEmail(email: String): Teacher? {
        return jpaTeacherRepository.findByEmail(email).map { it.toDomain() }.orElse(null)
    }
}