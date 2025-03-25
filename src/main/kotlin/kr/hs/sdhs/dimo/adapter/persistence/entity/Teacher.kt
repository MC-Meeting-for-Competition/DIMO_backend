package kr.hs.sdhs.dimo.adapter.persistence.entity

import kr.hs.sdhs.dimo.domain.Teacher as TeacherDomain
import jakarta.persistence.*

@Entity
@Table(name = "TEACHER")
data class Teacher(
    @Id
    @Column(name = "teacher_no")
    val id: String,

    @Column(name = "teacher_name", nullable = false)
    val name: String,

    @Column(name = "teacher_phone", length = 13)
    val phone: String,

    @Column(name = "teacher_email", nullable = false)
    val email: String,

    @Column(name = "rent_memo", length = 200)
    val rentMemo: String? = null,

    @Column(name = "policy", nullable = false)
    val policy: Boolean,

    @OneToMany(mappedBy = "teacher", cascade = [CascadeType.ALL])
    val rents: List<Rent> = listOf()
) {
    fun toDomain() : TeacherDomain {
        return TeacherDomain(
            id = this.id,
            name = this.name,
            phone = this.phone,
            email = this.email,
            rentMemo = this.rentMemo,
            policy = this.policy,
        )
    }
}
