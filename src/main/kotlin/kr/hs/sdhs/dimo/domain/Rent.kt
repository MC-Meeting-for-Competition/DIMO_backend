package kr.hs.sdhs.dimo.domain

import kr.hs.sdhs.dimo.adapter.persistence.entity.RentStatus
import kr.hs.sdhs.dimo.adapter.persistence.entity.Student
import kr.hs.sdhs.dimo.adapter.persistence.entity.Teacher
import kr.hs.sdhs.dimo.adapter.persistence.entity.Rent as RentEntity
import kr.hs.sdhs.dimo.adapter.persistence.entity.Equipment
import java.time.LocalDate

data class Rent(
    val id: Long = 0,
    val equipment: Equipment,
    val student: Student? = null,
    val teacher: Teacher? = null,
    val rentDate: LocalDate,
    val returnDate: LocalDate? = null,
    var rentStatus: RentStatus,
    var isReturn: Boolean = false,
) {
    // 연체 여부 확인
    fun isOverdue(currentDate: LocalDate): Boolean {
        return returnDate?.isBefore(currentDate) == true && !isReturn
    }

    // 반납 가능 여부 확인
    fun canBeReturned(): Boolean {
        return !isReturn && rentStatus != RentStatus.DAMAGED
    }

    // 대여 상태 변경 (반납 처리)
    fun markAsReturned(): Rent {
        if (!canBeReturned()) {
            throw IllegalStateException("반납할 수 없는 기자재입니다.")
        }
        return this.copy(isReturn = true, rentStatus = RentStatus.AVAILABLE)
    }


    fun isAlreadyProcessed(status : RentStatus): Boolean {
        return this.rentStatus == status
    }

    fun updateStatus(newStatus: RentStatus): RentStatus {
        this.rentStatus = newStatus
        return newStatus
    }

    // 엔티티 변환
    fun toEntity(): RentEntity {
        return RentEntity(
            id = this.id,
            equipment = this.equipment, // Equipment 엔티티 할당
            student = this.student,     // Student 엔티티 할당
            teacher = this.teacher,     // Teacher 엔티티 할당
            rentDate = this.rentDate,
            returnDate = this.returnDate,
            rentStatus = this.rentStatus,
            isReturn = this.isReturn
        )
    }
}
