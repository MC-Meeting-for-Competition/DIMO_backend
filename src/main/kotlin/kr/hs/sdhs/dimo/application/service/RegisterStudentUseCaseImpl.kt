package kr.hs.sdhs.dimo.application.service

import kr.hs.sdhs.dimo.application.port.input.RegisterStudentUseCase
import kr.hs.sdhs.dimo.application.port.output.StudentRepositoryPort
import kr.hs.sdhs.dimo.domain.Student
import kr.hs.sdhs.dimo.exception.CustomException
import kr.hs.sdhs.dimo.exception.ErrorCode
import org.springframework.stereotype.Service

@Service
class RegisterStudentUseCaseImpl(
    private val studentRepository : StudentRepositoryPort
) : RegisterStudentUseCase {
    override fun registerStudent(student: Student): Student {
        if(studentRepository.findByEmail(student.email) != null) {
            throw CustomException(ErrorCode.STUDENT_ALREADY_EXIST)
        }
        if(!student.policy) {
            throw CustomException(ErrorCode.NOT_AGREEMENT_POLICY)
        }
        if(student.email.split("@")[1] != "sdh.hs.kr") {
            throw CustomException(ErrorCode.WRONG_EMAIL_ADDRESS)
        }

        return studentRepository.save(student)
    }
}