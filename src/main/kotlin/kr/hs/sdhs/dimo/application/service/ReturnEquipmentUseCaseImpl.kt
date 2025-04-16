package kr.hs.sdhs.dimo.application.service

import jakarta.validation.constraints.Null
import kr.hs.sdhs.dimo.adapter.`in`.dto.ReturnEquipmentRequestDTO
import kr.hs.sdhs.dimo.adapter.persistence.entity.RentStatus
import kr.hs.sdhs.dimo.application.port.input.ReturnEquipmentUseCase
import kr.hs.sdhs.dimo.application.port.output.EquipmentRepositoryPort
import kr.hs.sdhs.dimo.application.port.output.RentRepositoryPort
import kr.hs.sdhs.dimo.application.port.output.StudentRepositoryPort
import kr.hs.sdhs.dimo.application.port.output.TeacherRepositoryPort
import kr.hs.sdhs.dimo.domain.Rent
import kr.hs.sdhs.dimo.domain.Teacher
import kr.hs.sdhs.dimo.exception.CustomException
import kr.hs.sdhs.dimo.exception.ErrorCode
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ReturnEquipmentUseCaseImpl(
    private val rentRepositoryPort: RentRepositoryPort,
    private val equipmentRepositoryPort: EquipmentRepositoryPort,
    private val studentRepositoryPort: StudentRepositoryPort,
    private val teacherRepositoryPort: TeacherRepositoryPort
) : ReturnEquipmentUseCase {
    override fun returnEquipment(equipmentId : Long, request : ReturnEquipmentRequestDTO): Rent {
        var teacherEmail  : String? = null;
        var studentEmail : String? = null;

        when (request.userType) {
            "teacher" -> {
                val targetTeacher = teacherRepositoryPort.findByEmail(request.email)
                if(targetTeacher == null) throw CustomException(ErrorCode.TEACHER_NOT_FOUND)
                teacherEmail = targetTeacher.email
            }
            "student" -> {
                val targetStudent = studentRepositoryPort.findByEmail(request.email)
                if(targetStudent == null) throw CustomException(ErrorCode.STUDENT_NOT_FOUND)
                studentEmail = targetStudent.email
            }
            else -> {
                throw CustomException(ErrorCode.INVALID_USER_TYPE)
            }
        }

        val rentedEquipments = rentRepositoryPort.findAllFiltered(studentEmail, teacherEmail, equipmentId, RentStatus.RENTED,
            Pageable.unpaged())

        if (rentedEquipments.isEmpty()) {
            throw CustomException(ErrorCode.RENT_NOT_FOUND)
        }

        val rent = rentedEquipments.first()
        rent.rentStatus = RentStatus.CHECKING
        val equipment = equipmentRepositoryPort.findById(equipmentId) ?: throw CustomException(ErrorCode.EQUIPMENT_NOT_FOUND)
        equipment.updateStatus(RentStatus.CHECKING)

        val updateRent = rentRepositoryPort.save(rent)
        equipmentRepositoryPort.save(equipment)

        return updateRent
    }
}