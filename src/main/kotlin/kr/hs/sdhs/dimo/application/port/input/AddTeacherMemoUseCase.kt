package kr.hs.sdhs.dimo.application.port.input

import kr.hs.sdhs.dimo.domain.Teacher

interface AddTeacherMemoUseCase {
    fun addTeacherMemo(email: String, memo: String) : Teacher
}