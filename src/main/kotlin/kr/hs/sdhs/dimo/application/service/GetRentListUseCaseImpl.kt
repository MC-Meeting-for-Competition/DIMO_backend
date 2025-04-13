package kr.hs.sdhs.dimo.application.service

import kr.hs.sdhs.dimo.adapter.`in`.dto.RentResponseDTO
import kr.hs.sdhs.dimo.adapter.persistence.entity.RentStatus
import kr.hs.sdhs.dimo.application.port.input.GetRentListUseCase
import kr.hs.sdhs.dimo.application.port.output.RentRepositoryPort
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class GetRentListUseCaseImpl(
    private val rentRepositoryPort: RentRepositoryPort,
) : GetRentListUseCase {
    override fun getRentList(
        studentId: String?,
        teacherId: Long?,
        equipmentId: Long?,
        rentStatus: Int?,
        sort: String,
        direction: String,
        page: Int,
        size: Int
    ): List<RentResponseDTO> {
        val statusEnum = rentStatus?.let {
            try {
                RentStatus.fromValue(it)
            } catch (e: NoSuchElementException) {
                throw IllegalArgumentException("잘못된 rentStatus 값입니다: $it")
            }
        }
        val sortDirection = if (direction.lowercase() == "desc") Sort.Direction.DESC else Sort.Direction.ASC
        val pageable = PageRequest.of(page, size, Sort.by(sortDirection, sort))

        return rentRepositoryPort.findAllFiltered(studentId, teacherId, equipmentId, statusEnum, pageable).map { rent -> RentResponseDTO.fromDomain(rent) }
    }
}