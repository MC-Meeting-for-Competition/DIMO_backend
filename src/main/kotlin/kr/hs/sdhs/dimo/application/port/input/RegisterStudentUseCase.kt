package kr.hs.sdhs.dimo.application.port.input

import kr.hs.sdhs.dimo.domain.Student

interface RegisterStudentUseCase {
    fun registerStudent(student: Student) : Student
}