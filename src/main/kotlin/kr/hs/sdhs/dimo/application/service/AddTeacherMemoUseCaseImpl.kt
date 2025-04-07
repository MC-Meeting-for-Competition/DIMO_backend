package kr.hs.sdhs.dimo.application.service

import kr.hs.sdhs.dimo.application.port.input.AddStudentMemoUseCase
import kr.hs.sdhs.dimo.application.port.input.AddTeacherMemoUseCase
import kr.hs.sdhs.dimo.application.port.output.StudentRepositoryPort
import kr.hs.sdhs.dimo.application.port.output.TeacherRepositoryPort
import kr.hs.sdhs.dimo.domain.Student
import kr.hs.sdhs.dimo.domain.Teacher
import kr.hs.sdhs.dimo.exception.CustomException
import kr.hs.sdhs.dimo.exception.ErrorCode
import org.springframework.stereotype.Service

@Service
class AddTeacherMemoUseCaseImpl(
    private val teacherRepositoryPort: TeacherRepositoryPort
) : AddTeacherMemoUseCase {
    override fun addTeacherMemo(id: Long, memo: String): Teacher {
        val targetTeacher = teacherRepositoryPort.findById(id) ?: throw CustomException(ErrorCode.TEACHER_NOT_FOUND)
        targetTeacher.rentMemo = memo
        return teacherRepositoryPort.save(targetTeacher)
    }

}