package kr.hs.sdhs.dimo.adapter.persistence.entity

import kr.hs.sdhs.dimo.domain.Teacher as TeacherDomain
import jakarta.persistence.*

@Entity
@Table(name = "TEACHER")
data class Teacher(
    @Id
    @Column(name = "teacher_no", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

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
    val rents: List<Rent> = listOf(),

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val role: Role = Role.USER
) {
    fun toDomain() : TeacherDomain {
        return TeacherDomain(
            name = this.name,
            phone = this.phone,
            email = this.email,
            rentMemo = this.rentMemo,
            policy = this.policy,
        )
    }
}
