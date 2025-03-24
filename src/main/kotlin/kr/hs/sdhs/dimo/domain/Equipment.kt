package kr.hs.sdhs.dimo.domain

import kr.hs.sdhs.dimo.adapter.persistence.entity.RentStatus
import kr.hs.sdhs.dimo.adapter.persistence.entity.Equipment as EquipmentEntity
import kr.hs.sdhs.dimo.exception.CustomException
import kr.hs.sdhs.dimo.exception.ErrorCode



data class Equipment(
    val id: Long = 0,
    val type: EquipmentType,
    val serialNo: String,
    var status: RentStatus = RentStatus.AVAILABLE,
    val memo: String? = null,
    val rents: List<Rent> = mutableListOf()
) {
    // 장비가 사용 가능한지 확인
    fun isAvailable() = status == RentStatus.AVAILABLE

    // 장비가 대여 중인지 확인
    fun isRented() = status == RentStatus.RENTED || status == RentStatus.LONG_RENTED

    // 장비가 점검 중인지 확인
    fun isChecking() = status == RentStatus.CHECKING

    // 장비가 파손된 상태인지 확인
    fun isBroken() = status == RentStatus.DAMAGED

    // 장비 대여 처리
    fun rentEquipment(): Equipment {
        if (!isAvailable()) throw CustomException(ErrorCode.EQUIPMENT_NOT_AVAILABLE)
        return copy(status = RentStatus.RENTED)
    }

    // 장비 반환 처리
    fun returnEquipment(): Equipment {
        if (!isRented()) throw CustomException(ErrorCode.EQUIPMENT_ALREADY_RETURNED)
        return copy(status = RentStatus.AVAILABLE)
    }

    // 장기 대여 처리
    fun rentLongEquipment(): Equipment {
        if (!isAvailable()) throw CustomException(ErrorCode.EQUIPMENT_NOT_AVAILABLE)
        return copy(status = RentStatus.LONG_RENTED)
    }

    // 장비 점검 상태로 변경
    fun startChecking(): Equipment {
        if (isRented()) throw CustomException(ErrorCode.RENTAL_ALREADY_IN_PROGRESS)
        return copy(status = RentStatus.CHECKING)
    }

    // 장비 파손 처리
    fun markAsBroken(): Equipment {
        if (isRented()) throw CustomException(ErrorCode.RENTAL_ALREADY_IN_PROGRESS)
        return copy(status = RentStatus.DAMAGED)
    }

    fun updateStatus(newStatus: RentStatus): RentStatus {
        this.status = newStatus
        return newStatus
    }

    // 엔티티 변환
    fun toEntity(): EquipmentEntity {
        return EquipmentEntity(
            id = id,
            type = type.toEntity(),
            serialNo = serialNo,
            memo = memo,
            status = status // `ordinal`을 사용하지 않고, Enum을 그대로 저장 가능하면 유지.
        )
    }
}