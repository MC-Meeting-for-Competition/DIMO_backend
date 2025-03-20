package kr.hs.sdhs.dimo.domain

enum class RentStatus(val value: Int) {
    AVAILABLE(0),  // 대여 가능
    RENTED(1),     // 대여 중
    DAMAGED(2);    // 손상됨

    companion object {
        fun fromValue(value: Int) = entries.first { it.value == value }
    }
}
