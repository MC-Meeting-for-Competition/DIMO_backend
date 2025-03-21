package kr.hs.sdhs.dimo.adapter.persistence.entity

import jakarta.persistence.*

@Entity
@Table(name = "TEACHER")
data class Teacher(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_no")
    val id: Long = 0,

    @Column(name = "student_name", nullable = false)
    val name: String,

    @Column(name = "student_phone", length = 13)
    val phone: String,

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "rent_status", nullable = false)
    val rentStatus: RentStatus,

    @Column(name = "rent_memo", length = 200)
    val rentMemo: String? = null,

    @Column(name = "policy", nullable = false)
    val policy: Boolean,

    @OneToMany(mappedBy = "teacher", cascade = [CascadeType.ALL])
    val rents: List<Rent> = listOf()
)
