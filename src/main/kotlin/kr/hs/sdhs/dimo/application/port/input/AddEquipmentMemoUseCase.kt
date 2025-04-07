package kr.hs.sdhs.dimo.application.port.input

import kr.hs.sdhs.dimo.domain.Equipment

interface AddEquipmentMemoUseCase {
    fun addEquipmentMemo(id : Long, memo: String) : Equipment
}