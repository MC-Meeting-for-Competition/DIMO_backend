package kr.hs.sdhs.dimo.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class CustomExceptionHandler {
    @ExceptionHandler(CustomException::class)
    fun handleCustomException(e: CustomException): ResponseEntity<ErrorResponseEntity> {
        return ErrorResponseEntity.toResponseEntity(e.errorCode)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ErrorResponseEntity> {
        e.printStackTrace() // 로그 기록 등을 할 수 있습니다.
        return ErrorResponseEntity.toResponseEntity(ErrorCode.INTERNAL_SERVER_ERROR)
    }
}