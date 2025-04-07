package kr.hs.sdhs.dimo.application.service

import kr.hs.sdhs.dimo.application.port.input.AddStudentMemoUseCase
import kr.hs.sdhs.dimo.application.port.output.StudentRepositoryPort
import kr.hs.sdhs.dimo.domain.Student
import kr.hs.sdhs.dimo.exception.CustomException
import kr.hs.sdhs.dimo.exception.ErrorCode
import org.springframework.stereotype.Service

@Service
class AddStudentMemoUseCaseImpl(
    private val studentRepositoryPort: StudentRepositoryPort
) : AddStudentMemoUseCase {
    override fun addStudentMemo(id: String, memo: String): Student {
        val targetStudent = studentRepositoryPort.findById(id) ?: throw CustomException(ErrorCode.STUDENT_NOT_FOUND)
        targetStudent.rentMemo = memo
        return studentRepositoryPort.save(targetStudent)
    }

}