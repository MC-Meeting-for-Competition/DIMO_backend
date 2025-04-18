package kr.hs.sdhs.dimo.domain

import kr.hs.sdhs.dimo.adapter.persistence.entity.Teacher as TeacherEntity

data class Teacher(
    var id : String,
    val name: String,
    val phone: String,
    val email : String,
    var rentMemo: String? = null,
    val policy: Boolean,
    val rents: List<Rent> = listOf()
) {

    // 교사가 대여한 기기 목록에 추가
    fun addRent(rent: Rent): Teacher {
        return this.copy(rents = this.rents + rent)
    }

    // 엔티티 변환
    fun toEntity(): TeacherEntity {
        return TeacherEntity(
            name = this.name,
            phone = this.phone,
            email = this.email,
            rentMemo = this.rentMemo,
            policy = this.policy,
            id = this.id
        )
    }
}
