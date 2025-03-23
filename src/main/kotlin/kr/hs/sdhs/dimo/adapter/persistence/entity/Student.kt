package kr.hs.sdhs.dimo.adapter.persistence.entity

import jakarta.persistence.*
import kr.hs.sdhs.dimo.domain.Student as StudentDomain

@Entity
@Table(name = "STUDENT")
data class Student(
    @Id
    @Column(name = "student_no", unique = true)
    val id: Long = 0,

    @Column(name = "student_name", nullable = false)
    val name: String,

    @Column(name = "student_phone", length = 13)
    val phone: String,

    @Column(name = "student_email", nullable = false)
    val email: String,

    @Column(name = "rent_memo", length = 200)
    val rentMemo: String? = null,

    @Column(name = "policy", nullable = false)
    val policy: Boolean,

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "gender", nullable = false)
    val gender: Gender,

    @OneToMany(mappedBy = "student", cascade = [CascadeType.ALL])
    val rents: List<Rent> = listOf()
) {
    fun toDomain(): StudentDomain {
        return StudentDomain(
            id = this.id,
            name = this.name,
            phone = this.phone,
            email  = this.email,
            rentMemo = this.rentMemo,
            policy = this.policy,
            gender = this.gender,
            rents = this.rents.map { it.toDomain() } // Rent 변환 필요
        )
    }
}
