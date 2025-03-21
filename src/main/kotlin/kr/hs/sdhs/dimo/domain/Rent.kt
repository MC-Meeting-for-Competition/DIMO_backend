package kr.hs.sdhs.dimo.domain

import kr.hs.sdhs.dimo.adapter.persistence.entity.RentStatus
import java.time.LocalDate

data class Rent(
    val id: Long = 0,
    val equipmentId: Long,
    val studentId: Long? = null,
    val teacherId: Long? = null,
    val rentDate: LocalDate,
    val returnDate: LocalDate? = null,
    val rentStatus: RentStatus,
    val isReturn: Boolean = false
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

    // 특정 사용자의 대여 기록인지 확인
    fun isRentedByUser(userId: Long): Boolean {
        return (studentId == userId || teacherId == userId) && !isReturn
    }
}
