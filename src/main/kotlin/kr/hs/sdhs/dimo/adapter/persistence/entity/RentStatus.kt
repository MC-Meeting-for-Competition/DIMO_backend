package kr.hs.sdhs.dimo.adapter.persistence.entity

enum class RentStatus(val value: Int) {
    AVAILABLE(0),  // 대여 가능
    RENTED(1),     // 대여 중
    DAMAGED(2),    // 손상됨
    LONG_RENTED(3), // 장기 대여
    CHECKING(4); // 점검 중

    companion object {
        fun fromValue(value: Int) = entries.first { it.value == value }
    }
}
