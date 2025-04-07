package kr.hs.sdhs.dimo.exception

import org.springframework.http.ResponseEntity

data class ErrorResponseEntity(
    val code : String,
    val message: String
) {
    companion object {
        fun toResponseEntity(errorCode: ErrorCode): ResponseEntity<ErrorResponseEntity> {
            val errorResponse = ErrorResponseEntity(
                code = errorCode.name,
                message = errorCode.message
            )
            return ResponseEntity.status(errorCode.httpStatus).body(ErrorResponseEntity(errorResponse.code, errorResponse.message))
        }
    }
}