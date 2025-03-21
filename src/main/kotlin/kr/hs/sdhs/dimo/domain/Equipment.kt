package kr.hs.sdhs.dimo.domain

import kr.hs.sdhs.dimo.adapter.persistence.entity.Equipment
import kr.hs.sdhs.dimo.exception.CustomException
import kr.hs.sdhs.dimo.exception.ErrorCode

data class Equipment(
    val id: Long = 0,
    val itemNo: Int,
    val serialNo: String? = null,
    var status: Int,
    val memo: String? = null,
    val rents: List<Rent> = listOf()
) {
    companion object {
        const val STATUS_CAN_USE = 1  // 대여 가능
        const val STATUS_RENTED = 2  // 대여 중
        const val STATUS_LONG_RENTED = 3 // 장기 대여 중
        const val STATUS_RETURNED = 4 // 반환됨
        const val STATUS_CHECKING = 5 // 점검 중
        const val STATUS_BROKEN = 6 // 파손
    }

    // 장비가 사용 가능한지 확인
    fun isCanUse(): Boolean {
        return status == STATUS_CAN_USE
    }

    // 장비가 대여 중인지 확인
    fun isRented(): Boolean {
        return status == STATUS_RENTED || status == STATUS_LONG_RENTED
    }

    // 장비가 점검 중인지 확인
    fun isChecking(): Boolean {
        return status == STATUS_CHECKING
    }

    // 장비가 파손된 상태인지 확인
    fun isBroken(): Boolean {
        return status == STATUS_BROKEN
    }

    // 메모가 있는지 확인
    fun hasMemo(): Boolean {
        return !memo.isNullOrEmpty()
    }

    // 장비 대여 처리
    fun rentEquipment() {
        if (isRented()) {
            throw CustomException(ErrorCode.RENTAL_ALREADY_IN_PROGRESS)
        }
        if (isChecking()) {
            throw CustomException(ErrorCode.EQUIPMENT_CHECKING_PROGRESS)
        }
        if (isBroken()) {
            throw CustomException(ErrorCode.EQUIPMENT_IS_BROKEN)
        }
        status = STATUS_RENTED
        // rents.add(Rent(...))  // 대여 기록 추가 로직
    }

    // 장비 반환 처리
    fun returnEquipment() {
        if (!isRented()) {
            throw CustomException(ErrorCode.EQUIPMENT_ALREADY_RETURNED)
        }
        status = STATUS_RETURNED
        // 반환 처리 후 추가 로직 (예시)
    }

    // 장기 대여 처리
    fun rentLongEquipment() {
        if (isRented()) {
            throw CustomException(ErrorCode.RENTAL_ALREADY_IN_PROGRESS)
        }
        if (isChecking()) {
            throw CustomException(ErrorCode.EQUIPMENT_CHECKING_PROGRESS)
        }
        if (isBroken()) {
            throw CustomException(ErrorCode.EQUIPMENT_IS_BROKEN)
        }
        status = STATUS_LONG_RENTED
        // rents.add(Rent(...))  // 장기 대여 기록 추가 로직
    }

    // 장비 점검 상태로 변경
    fun startChecking() {
        if (isRented()) {
            throw CustomException(ErrorCode.RENTAL_ALREADY_IN_PROGRESS)
        }
        status = STATUS_CHECKING
    }

    // 장비 파손 처리
    fun markAsBroken() {
        if (isRented()) {
            throw CustomException(ErrorCode.RENTAL_ALREADY_IN_PROGRESS)
        }
        status = STATUS_BROKEN
    }

    fun toEntity() : kr.hs.sdhs.dimo.adapter.persistence.entity.Equipment {
        return Equipment(
            id = id,
            itemNo = itemNo,
            serialNo = serialNo,
            memo = memo,
            status = status
        )
    }
}
