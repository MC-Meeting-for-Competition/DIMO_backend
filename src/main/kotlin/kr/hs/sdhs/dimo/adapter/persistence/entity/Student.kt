package kr.hs.sdhs.dimo.adapter.persistence.entity

import jakarta.persistence.*

@Entity
@Table(name = "STUDENT")
data class Student(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_no")
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

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "gender", nullable = false)
    val gender: Gender,

    @OneToMany(mappedBy = "student", cascade = [CascadeType.ALL])
    val rents: List<Rent> = listOf()
)
