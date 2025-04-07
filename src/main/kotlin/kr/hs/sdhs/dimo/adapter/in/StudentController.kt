package kr.hs.sdhs.dimo.adapter.`in`

import jakarta.validation.Valid
import kr.hs.sdhs.dimo.adapter.`in`.dto.StudentRequestDTO
import kr.hs.sdhs.dimo.adapter.`in`.dto.StudentResponseDTO
import kr.hs.sdhs.dimo.application.port.input.RegisterStudentUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/students")
class StudentController(
    private val registerStudentUseCase: RegisterStudentUseCase
) {
    @PostMapping
    fun registerStudent(@RequestBody @Valid request : StudentRequestDTO): StudentResponseDTO {
        val student = registerStudentUseCase.registerStudent(request.toDomain())
        return StudentResponseDTO.fromDomain(student)
    }
}