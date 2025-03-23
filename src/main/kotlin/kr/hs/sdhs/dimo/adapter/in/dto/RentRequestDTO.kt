package kr.hs.sdhs.dimo.adapter.`in`.dto

import jakarta.validation.constraints.Email
import java.time.LocalDate
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import kr.hs.sdhs.dimo.adapter.persistence.entity.Equipment
import kr.hs.sdhs.dimo.adapter.persistence.entity.RentStatus
import kr.hs.sdhs.dimo.adapter.persistence.entity.Student
import kr.hs.sdhs.dimo.adapter.persistence.entity.Teacher
import kr.hs.sdhs.dimo.domain.Rent

data class RentRequestDTO(
    // 학생/선생님 구분 (학생은 학번, 선생님은 00000으로 대체)
    @field:NotBlank(message = "학생 또는 선생님의 구분이 필요합니다.")
    val userType: String,  // "student" 또는 "teacher"로 구분

    @field:NotBlank(message = "이메일을 입력해주세요.")
    @field:Email(message = "이메일 형식이 올바르지 않아요.")
    val email : String,

    // 전화번호
    @field:NotBlank(message = "전화번호를 입력해주세요.")
    @field:Size(min = 10, max = 13, message = "전화번호는 10~13자리로 입력해주세요.")
    val phone: String,

    // 대여자 서약서 동의 여부
    @field:NotNull(message = "서약서 동의 여부를 선택해주세요.")
    val rentAgreement: Boolean,

    // 개인정보 수집 및 이용 동의 여부
    @field:NotNull(message = "개인정보 수집 및 이용 동의 여부를 선택해주세요.")
    val privacyAgreement: Boolean,

    // 대여일
    @field:NotNull(message = "대여일을 입력해주세요.")
    val rentDate: LocalDate,

    // 반납 예정일
    @field:NotNull(message = "반납 예정일을 입력해주세요.")
    val returnDate: LocalDate,

    @field:NotNull(message = "대여할 기자재 품번을 입력해주세요.")
    var equipmentId : Long
) {
    fun toDomain(
        equipment: Equipment, // Equipment 엔티티 객체
        student: Student? = null, // Student 엔티티 객체
        teacher: Teacher? = null // Teacher 엔티티 객체
    ): Rent {
        // userType에 따라 대여자가 학생인지 선생님인지 구분
        val rentStudent = if (userType == "student") student else null
        val rentTeacher = if (userType == "teacher") teacher else null

        return Rent(
            id = 0, // 기본 값은 0으로 설정
            equipment = equipment,
            student = rentStudent,
            teacher = rentTeacher,
            rentDate = this.rentDate,
            returnDate = this.returnDate,
            rentStatus = RentStatus.RENTED, // 대여 상태는 기본적으로 대여 상태로 설정
            isReturn = false // 처음엔 반납되지 않음
        )
    }
}