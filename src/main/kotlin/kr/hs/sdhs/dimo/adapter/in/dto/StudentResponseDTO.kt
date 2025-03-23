package kr.hs.sdhs.dimo.adapter.`in`.dto

import kr.hs.sdhs.dimo.adapter.persistence.entity.Gender
import kr.hs.sdhs.dimo.domain.Student

data class StudentResponseDTO(
    val id: Long,

    val name: String,

    val phone: String,

    val email: String,

    val rentMemo: String?,

    val policy: Boolean,

    val gender: Gender,

    val rents: List<RentResponseDTO> = listOf()
) {
    companion object {
        fun fromDomain(student: Student) : StudentResponseDTO {
            return StudentResponseDTO(
                id = student.id,
                name = student.name,
                phone = student.phone,
                email = student.email,
                rentMemo = student.rentMemo,
                policy = student.policy,
                gender = student.gender,
                rents = student.rents.map { rent -> RentResponseDTO.fromDomain(rent) }
            )
        }
    }
}