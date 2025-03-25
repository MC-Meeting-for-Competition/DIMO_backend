package kr.hs.sdhs.dimo.adapter.`in`.dto

import kr.hs.sdhs.dimo.domain.Teacher

data class TeacherResponseDTO(
    val id: String,

    val name: String,

    val phone: String,

    val email: String,

    val rentMemo: String?,

    val policy: Boolean,

    val rents: List<RentResponseDTO> = listOf()
) {
    companion object {
        fun fromDomain(teacher : Teacher) : TeacherResponseDTO {
            return TeacherResponseDTO(
                id = teacher.id,
                name = teacher.name,
                phone = teacher.phone,
                email = teacher.email,
                rentMemo = teacher.rentMemo,
                policy = teacher.policy,
                rents = teacher.rents.map { rent -> RentResponseDTO.fromDomain(rent) }
            )
        }
    }
}