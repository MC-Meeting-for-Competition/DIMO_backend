package kr.hs.sdhs.dimo.application.port.input

interface DeleteEquipmentTypeUseCase {
    fun deleteEquipmentType(id : Long) : Unit
}