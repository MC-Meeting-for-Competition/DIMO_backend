package kr.hs.sdhs.dimo.adapter.`in`

import jakarta.validation.Valid
import kr.hs.sdhs.dimo.adapter.`in`.dto.TeacherRequestDTO
import kr.hs.sdhs.dimo.adapter.`in`.dto.TeacherResponseDTO
import kr.hs.sdhs.dimo.application.port.input.RegisterTeacherUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/teachers")
class TeacherController(
    private val registerTeacherUseCase: RegisterTeacherUseCase
) {
    @PostMapping
    fun registerTeacher(@RequestBody @Valid request : TeacherRequestDTO) : TeacherResponseDTO {
        val teacher = registerTeacherUseCase.registerTeacher(request.toDomain())
        return TeacherResponseDTO.fromDomain(teacher)
    }
}