package kr.hs.sdhs.dimo.adapter.`in`.dto

import kr.hs.sdhs.dimo.adapter.persistence.entity.RentStatus
import kr.hs.sdhs.dimo.domain.Rent
import java.time.LocalDate

data class RentResponseDTO(
    val id: Long,
    val equipmentId: Long,
    val rentDate: LocalDate,
    val returnDate: LocalDate?,
    val rentStatus: RentStatus,
    val isReturn: Boolean
) {
    companion object {
        fun fromDomain(rent : Rent): RentResponseDTO {
            return RentResponseDTO(
                id = rent.id,
                equipmentId = rent.equipment.id,
                rentDate = rent.rentDate,
                returnDate = rent.returnDate,
                rentStatus = rent.rentStatus,
                isReturn = rent.isReturn
            )
        }
    }
}