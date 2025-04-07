package kr.hs.sdhs.dimo.application.service

import kr.hs.sdhs.dimo.application.port.input.RegisterTeacherUseCase
import kr.hs.sdhs.dimo.application.port.output.TeacherRepositoryPort
import kr.hs.sdhs.dimo.domain.Teacher
import kr.hs.sdhs.dimo.exception.CustomException
import kr.hs.sdhs.dimo.exception.ErrorCode
import org.springframework.stereotype.Service

@Service
class RegisterTeacherUseCaseImpl(
    private val teacherRepository: TeacherRepositoryPort
) : RegisterTeacherUseCase {
    override fun registerTeacher(teacher: Teacher): Teacher {
        if(teacherRepository.findByEmail(teacher.email) != null) {
            throw CustomException(ErrorCode.TEACHER_ALREADY_EXIST)
        }
        if(!teacher.policy) {
            throw CustomException(ErrorCode.NOT_AGREEMENT_POLICY)
        }
        if(teacher.email.split("@")[1] != "sdh.hs.kr") {
            throw CustomException(ErrorCode.WRONG_EMAIL_ADDRESS)
        }

        return teacherRepository.save(teacher)
    }
}