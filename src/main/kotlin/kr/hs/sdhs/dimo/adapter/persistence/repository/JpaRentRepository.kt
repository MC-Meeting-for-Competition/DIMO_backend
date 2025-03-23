package kr.hs.sdhs.dimo.adapter.persistence.repository

import kr.hs.sdhs.dimo.adapter.persistence.entity.Rent
import org.springframework.data.jpa.repository.JpaRepository

interface JpaRentRepository : JpaRepository<Rent, Long> {
}