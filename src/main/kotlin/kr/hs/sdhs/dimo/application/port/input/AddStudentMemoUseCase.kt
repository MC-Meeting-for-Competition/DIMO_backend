package kr.hs.sdhs.dimo.application.port.input

import kr.hs.sdhs.dimo.domain.Student

interface AddStudentMemoUseCase {
    fun addStudentMemo(id: String, memo: String) : Student
}