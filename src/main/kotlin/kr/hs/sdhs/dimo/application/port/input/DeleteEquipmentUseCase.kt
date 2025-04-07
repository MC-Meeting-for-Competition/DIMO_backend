package kr.hs.sdhs.dimo.application.port.input

interface DeleteEquipmentUseCase {
    fun deleteById(id: Long) : Unit
}