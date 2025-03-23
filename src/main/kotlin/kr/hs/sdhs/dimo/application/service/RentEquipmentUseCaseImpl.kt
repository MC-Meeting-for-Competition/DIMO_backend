package kr.hs.sdhs.dimo.application.service

import kr.hs.sdhs.dimo.adapter.`in`.dto.RentRequestDTO
import kr.hs.sdhs.dimo.adapter.`in`.dto.RentResponseDTO
import kr.hs.sdhs.dimo.application.port.input.RentEquipmentUseCase
import kr.hs.sdhs.dimo.application.port.output.EquipmentRepositoryPort
import kr.hs.sdhs.dimo.application.port.output.RentRepositoryPort
import kr.hs.sdhs.dimo.application.port.output.StudentRepositoryPort
import kr.hs.sdhs.dimo.application.port.output.TeacherRepositoryPort
import kr.hs.sdhs.dimo.domain.Equipment
import kr.hs.sdhs.dimo.domain.Rent
import kr.hs.sdhs.dimo.exception.CustomException
import kr.hs.sdhs.dimo.exception.ErrorCode
import org.springframework.stereotype.Service
import java.util.*

@Service
class RentEquipmentUseCaseImpl(
    private val rentRepository: RentRepositoryPort,
    private val equipmentRepository : EquipmentRepositoryPort,
    private val studentRepository : StudentRepositoryPort,
    private val teacherRepository: TeacherRepositoryPort,
) : RentEquipmentUseCase {
    override fun rentEquipment(rent: RentRequestDTO): Rent {
        // 장비 정보 조회
        val targetEquipment = equipmentRepository.findById(rent.equipmentId)
            ?: throw CustomException(ErrorCode.EQUIPMENT_NOT_FOUND)

        // 사용자가 학생인 경우
        val renterStudent = if (rent.userType.equals("student", ignoreCase = true)) {
            studentRepository.findByEmail(rent.email)
                ?: throw CustomException(ErrorCode.STUDENT_NOT_FOUND)
        } else null

        // 사용자가 교사인 경우
        val renterTeacher = if (rent.userType.equals("teacher", ignoreCase = true)) {
            teacherRepository.findByEmail(rent.email)
                ?: throw CustomException(ErrorCode.TEACHER_NOT_FOUND)
        } else null

        // 유효하지 않은 userType 처리
        if (renterStudent == null && renterTeacher == null) {
            throw CustomException(ErrorCode.INVALID_USER_TYPE)
        }

        // Rent 도메인 객체 생성
        val rentEntity = rentRepository.save(rent.toDomain(
            targetEquipment.toEntity(),
            renterStudent?.toEntity(),
            renterTeacher?.toEntity()
        ))

        return rentEntity
    }
}