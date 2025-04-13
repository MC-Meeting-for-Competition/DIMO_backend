package kr.hs.sdhs.dimo.application.port.input

import kr.hs.sdhs.dimo.adapter.`in`.dto.RentResponseDTO

interface GetRentListUseCase {
    fun getRentList(
        studentId: String?,
        teacherId: Long?,
        equipmentId: Long?,
        rentStatus: Int?,
        sort: String,
        direction: String,
        page: Int,
        size: Int): List<RentResponseDTO>
}