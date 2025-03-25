package kr.hs.sdhs.dimo.domain

import kr.hs.sdhs.dimo.adapter.persistence.entity.Gender
import kr.hs.sdhs.dimo.adapter.persistence.entity.Student as StudentEntity

data class Student(
    val id: String,
    val name: String,
    val phone: String,
    val email: String,
    val rentMemo: String?,
    val policy: Boolean,
    val gender: Gender,
    val rents: List<Rent> = listOf()
) {
    fun addRent(rent: Rent): Student {
        return this.copy(rents = this.rents + rent)
    }

    // 엔티티 변환
    fun toEntity() : StudentEntity {
        return StudentEntity(
            id = this.id,
            name = this.name,
            phone = this.phone,
            email = this.email,
            rentMemo = this.rentMemo,
            policy = this.policy,
            gender = this.gender,
        )
    }
}
