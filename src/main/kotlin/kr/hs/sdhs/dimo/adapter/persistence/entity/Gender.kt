package kr.hs.sdhs.dimo.adapter.persistence.entity

enum class Gender(val value: Int) {
    UNKNOWN(0), // 알 수 없음
    MALE(1),    // 남성
    FEMALE(2);  // 여성

    companion object {
        fun fromValue(value: Int) = entries.first { it.value == value }
    }
}
