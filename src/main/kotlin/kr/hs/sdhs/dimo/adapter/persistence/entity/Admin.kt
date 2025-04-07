package kr.hs.sdhs.dimo.adapter.persistence.entity

import jakarta.persistence.*
import kr.hs.sdhs.dimo.domain.Admin

@Entity
@Table(name = "ADMIN")
data class Admin(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_no")
    val id: Long = 0,

    @Column(name = "admin_name", nullable = false, length = 20, unique = true)
    val name: String,

    @Column(name = "password", nullable = false)
    val password: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val role: Role = Role.ADMIN,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val accountStatus : AccountStatus = AccountStatus.PENDING,
) {
    fun toDomain() : Admin {
        return Admin(
            id = this.id,
            name = this.name,
            password = this.password,
            role = this.role,
            accountStatus = this.accountStatus
        )
    }
}
