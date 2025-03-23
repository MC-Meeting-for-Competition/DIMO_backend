package kr.hs.sdhs.dimo.adapter.out

import kr.hs.sdhs.dimo.adapter.persistence.repository.JpaStudentRepository
import kr.hs.sdhs.dimo.application.port.output.StudentRepositoryPort
import kr.hs.sdhs.dimo.domain.Student
import org.springframework.stereotype.Repository

@Repository
class StudentRepositoryAdapter(
    private val jpaStudentRepository : JpaStudentRepository
) : StudentRepositoryPort{
    override fun save(student: Student): Student {
        return jpaStudentRepository.save(student.toEntity()).toDomain()
    }

    override fun findById(id: Long): Student? {
        TODO("Not yet implemented")
    }

    override fun findAll(): MutableList<Student> {
        TODO("Not yet implemented")
    }

    override fun findByEmail(email: String): Student? {
        return jpaStudentRepository.findByEmail(email).map { it.toDomain() }.orElse(null)
    }
}