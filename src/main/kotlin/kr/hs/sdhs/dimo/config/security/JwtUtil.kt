package kr.hs.sdhs.dimo.config.security

import java.util.*
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.Date
import javax.crypto.SecretKey

@Component
class JwtUtil {
    @Value("\${jwt.secret}")
    lateinit var secretKey: String

    // Use a more standard expiration time (10 hours in milliseconds)
    private val expirationTime = 1000L * 60 * 60 * 10 // 10 hours

    // Helper function to create SecretKey (HS256 requires 256-bit key)
    private fun getSigningKey(): SecretKey {
        // Ensure secretKey is at least 32 bytes (UTF-8 encoded)
        require(secretKey.toByteArray(Charsets.UTF_8).size >= 32) {
            "Secret key must be at least 32 bytes long"
        }
        return Keys.hmacShaKeyFor(secretKey.toByteArray(Charsets.UTF_8))
    }

    fun generateToken(userId: String): String {
        return createToken(emptyMap(), userId)
    }

    fun createToken(claims: Map<String, Any>, subject: String): String {
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + expirationTime))
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact()
    }

    fun extractUsername(token: String): String {
        return getClaims(token).subject
    }

    fun isTokenExpired(token: String): Boolean {
        return getClaims(token).expiration.before(Date())
    }

    fun validateToken(token: String, userDetails: UserDetails): Boolean {
        return try {
            extractUsername(token) == userDetails.username && !isTokenExpired(token)
        } catch (e: Exception) {
            false // Handle any parsing or validation exceptions
        }
    }

    private fun getClaims(token: String): Claims {
        return Jwts.parser()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .body
    }
}