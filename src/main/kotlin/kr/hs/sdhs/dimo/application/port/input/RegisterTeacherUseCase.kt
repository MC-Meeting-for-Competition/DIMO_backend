package kr.hs.sdhs.dimo.application.port.input

import kr.hs.sdhs.dimo.domain.Teacher

interface RegisterTeacherUseCase {
    fun registerTeacher(teacher: Teacher): Teacher
}