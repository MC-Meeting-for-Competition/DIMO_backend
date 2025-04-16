package kr.hs.sdhs.dimo.adapter.`in`

import jakarta.validation.Valid
import kr.hs.sdhs.dimo.adapter.`in`.dto.*
import kr.hs.sdhs.dimo.adapter.persistence.entity.RentStatus
import kr.hs.sdhs.dimo.application.port.input.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("admin")
class AdminController(
    private val registerEquipmentUseCase: RegisterEquipmentUseCase,
    private val deleteEquipmentUseCase: DeleteEquipmentUseCase,
    private val getEquipmentTypeUseCase: GetEquipmentTypeUseCase,
    private val registerEquipmentTypeUseCase: RegisterEquipmentTypeUseCase,
    private val deleteEquipmentTypeUseCase: DeleteEquipmentTypeUseCase,
    private val updateRentStatusUseCase: UpdateRentStatusUseCase,
    private val registerAdminUseCase: RegisterAdminUseCase,
    private val loginAdminUseCase: LoginAdminUseCase,
    private val grantAdminUseCase: GrantAdminUseCase,
    private val addEquipmentMemoUseCase: AddEquipmentMemoUseCase,
    private val addStudentMemoUseCase: AddStudentMemoUseCase,
    private val addTeacherMemoUseCase: AddTeacherMemoUseCase
) {

    @DeleteMapping("/equipment/{id}")
    fun deleteEquipment( @PathVariable id : Long): ResponseEntity<String> {
        deleteEquipmentUseCase.deleteById(id)
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다")
    }

    @PostMapping("/equipment/register")
    fun registerEquipment(@RequestBody @Valid request : EquipmentRequestDTO) : EquipmentResponseDTO {
        val equipmentType = getEquipmentTypeUseCase.findById(request.itemNo)
        val equipment = registerEquipmentUseCase.registerEquipment(request.toDomain(equipmentType))
        return EquipmentResponseDTO.fromDomain(equipment)
    }

    @PostMapping("/equipment-type/register")
    fun registerEquipmentType(@RequestBody @Valid request : EquipmentTypeRequestDTO) : EquipmentTypeResponseDTO {
        val equipmentType = registerEquipmentTypeUseCase.save(request.toDomain())
        return EquipmentTypeResponseDTO.fromDomain(equipmentType)
    }

    @DeleteMapping("/equipment-type/{id}")
    fun delete(@PathVariable id: Long) : ResponseEntity<String> {
        deleteEquipmentTypeUseCase.deleteEquipmentType(id)
        return ResponseEntity("품목 분류가 성공적으로 삭제되었습니다.", HttpStatus.OK)
    }


//    @PatchMapping("/rent/{rentId}/damage")
//    fun markAsDamaged(@PathVariable rentId: Long): RentResponseDTO {
//        val rent = updateRentStatusUseCase.updateRentStatus(rentId, RentStatus.DAMAGED)
//        return RentResponseDTO.fromDomain(rent)
//    }
//
//    @PatchMapping("/rent/{rentId}/available")
//    fun markAsAvailable(@PathVariable rentId: Long): RentResponseDTO {
//        val rent = updateRentStatusUseCase.updateRentStatus(rentId, RentStatus.AVAILABLE)
//        return RentResponseDTO.fromDomain(rent)
//    }

    @PatchMapping("/rent/{equipmentId}/status")
    fun changeRentStatus(@PathVariable equipmentId : Long, @RequestBody request : ChangeRentStatusRequestDTO) : RentResponseDTO {
        val rent = updateRentStatusUseCase.updateRentStatus(equipmentId, request);
        return RentResponseDTO.fromDomain(rent)
    }

    @PostMapping("/login")
    fun login(@RequestBody request : AdminRequestDTO) : ResponseEntity<String> {
        val token = loginAdminUseCase.login(request.toDomain())
        return ResponseEntity(token, HttpStatus.OK)
    }

    @PostMapping("/register")
    fun register(@RequestBody request : AdminRequestDTO) : AdminResponseDTO {
        val admin = registerAdminUseCase.register(request.toDomain())
        return AdminResponseDTO.fromDomain(admin)
    }

    @PatchMapping("/grant/{adminId}")
    fun grant(@PathVariable adminId: Long) : AdminResponseDTO {
        val grantedAdmin = grantAdminUseCase.grantAdmin(adminId)
        return AdminResponseDTO.fromDomain(grantedAdmin)
    }

    @PostMapping("/equipment/{id}/memo")
    fun addMemoToEquipment(@PathVariable id: Long, @RequestBody memo: String) : EquipmentResponseDTO {
        val equipment = addEquipmentMemoUseCase.addEquipmentMemo(id, memo)
        return EquipmentResponseDTO.fromDomain(equipment)
    }

    @PostMapping("/student/memo")
    fun addMemoToStudent(@RequestBody request: AddMemoRequestDTO) : StudentResponseDTO {
        val student = addStudentMemoUseCase.addStudentMemo(request.email, request.memo)
        return StudentResponseDTO.fromDomain(student)
    }

    @PostMapping("/teacher/memo")
    fun addMemoToTeacher(@RequestBody request: AddMemoRequestDTO) : TeacherResponseDTO {
        val teacher = addTeacherMemoUseCase.addTeacherMemo(request.email, request.memo)
        return TeacherResponseDTO.fromDomain(teacher)
    }
}