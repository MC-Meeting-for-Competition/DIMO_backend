package kr.hs.sdhs.dimo.application.service

import kr.hs.sdhs.dimo.adapter.`in`.dto.RentRequestDTO
import kr.hs.sdhs.dimo.application.port.input.RentEquipmentUseCase
import kr.hs.sdhs.dimo.application.port.output.EquipmentRepositoryPort
import kr.hs.sdhs.dimo.application.port.output.RentRepositoryPort
import kr.hs.sdhs.dimo.application.port.output.StudentRepositoryPort
import kr.hs.sdhs.dimo.application.port.output.TeacherRepositoryPort
import kr.hs.sdhs.dimo.domain.Rent
import kr.hs.sdhs.dimo.exception.CustomException
import kr.hs.sdhs.dimo.exception.ErrorCode
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class RentEquipmentUseCaseImpl(
    private val rentRepository: RentRepositoryPort,
    private val equipmentRepository: EquipmentRepositoryPort,
    private val studentRepository: StudentRepositoryPort,
    private val teacherRepository: TeacherRepositoryPort,
) : RentEquipmentUseCase {
    override fun rentEquipment(rent: RentRequestDTO, rentId : Long): Rent {
        val targetEquipment = equipmentRepository.findById(rentId)
            ?: throw CustomException(ErrorCode.EQUIPMENT_NOT_FOUND)

        // 사용자 정보 조회
        val (renterStudent, renterTeacher) = when (rent.userType.lowercase(Locale.getDefault())) {
            "student" -> Pair(
                studentRepository.findByEmail(rent.email) ?: throw CustomException(ErrorCode.STUDENT_NOT_FOUND),
                null
            )

            "teacher" -> Pair(
                null,
                teacherRepository.findByEmail(rent.email) ?: throw CustomException(ErrorCode.TEACHER_NOT_FOUND)
            )

            else -> throw CustomException(ErrorCode.INVALID_USER_TYPE)
        }

        if (rent.userType == "student" && rent.isLongRent) {
            throw CustomException(ErrorCode.NOT_ACCEPT_LONG_RENT)
        }
        if (renterStudent != null) {
            val maxReturnDate = LocalDate.now().plusMonths(6)
            if (rent.returnDate.isAfter(maxReturnDate)) {
                throw CustomException(ErrorCode.RETURN_DATE_TOO_LONG)
            }
        }

        val updatedEquipment = targetEquipment.rentEquipment()

        equipmentRepository.save(updatedEquipment)

        return rentRepository.save(
            rent.toDomain(
                updatedEquipment.toEntity(),
                renterStudent?.toEntity(),
                renterTeacher?.toEntity()
            )
        )
    }
}