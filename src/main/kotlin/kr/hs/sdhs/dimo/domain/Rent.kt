package kr.hs.sdhs.dimo.domain

import jakarta.persistence.*

@Entity
@Table(name = "RENT")
data class Rent(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rent_no")
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "equip_no", nullable = false)
    val equipment: Equipment,

    @ManyToOne
    @JoinColumn(name = "student_no")
    val student: Student? = null,

    @ManyToOne
    @JoinColumn(name = "teacher_no")
    val teacher: Teacher? = null,

    @Column(name = "rent_date", nullable = false)
    val rentDate: java.time.LocalDate,

    @Column(name = "return_date")
    val returnDate: java.time.LocalDate? = null,

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "rent_status", nullable = false)
    val rentStatus: RentStatus,

    @Column(name = "is_return", nullable = false)
    val isReturn: Boolean = false
)
