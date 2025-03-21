package kr.hs.sdhs.dimo.adapter.persistence.entity

import jakarta.persistence.*
import kr.hs.sdhs.dimo.domain.Rent

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
) {
    fun toDomain(): Rent {
        return Rent(
            id = this.id,
            equipmentId = this.equipment.id,  // 도메인에서는 ID만 사용
            studentId = this.student?.id,
            teacherId = this.teacher?.id,
            rentDate = this.rentDate,
            returnDate = this.returnDate,
            rentStatus = this.rentStatus,
            isReturn = this.isReturn
        )
    }
}
