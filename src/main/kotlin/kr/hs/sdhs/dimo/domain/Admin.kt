package kr.hs.sdhs.dimo.domain

import jakarta.persistence.*

@Entity
@Table(name = "ADMIN")
data class Admin(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_no")
    val id: Long = 0,

    @Column(name = "admin_name", nullable = false, length = 20)
    val name: String,

    @Column(name = "password", nullable = false)
    val password: String
)
