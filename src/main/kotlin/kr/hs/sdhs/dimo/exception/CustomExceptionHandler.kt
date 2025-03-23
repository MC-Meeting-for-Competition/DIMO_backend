package kr.hs.sdhs.dimo.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.bind.support.WebExchangeBindException
import org.springframework.web.server.ServerWebInputException

@RestControllerAdvice
class CustomExceptionHandler {
    @ExceptionHandler(CustomException::class)
    fun handleCustomException(e: CustomException): ResponseEntity<ErrorResponseEntity> {
        return ErrorResponseEntity.toResponseEntity(e.errorCode)
    }

    @ExceptionHandler(ServerWebInputException::class)
    fun handleServerWebInputException(e: ServerWebInputException): ResponseEntity<ErrorResponseEntity> {
        e.printStackTrace()
        val message = "${e.methodParameter?.parameter?.name}의 값이 올바르지 않습니다."
        return ResponseEntity.badRequest().body(ErrorResponseEntity(HttpStatus.BAD_REQUEST.name, message))
    }

    @ExceptionHandler(WebExchangeBindException::class)
    fun handleWebExchangeBindException(e: WebExchangeBindException): ResponseEntity<ErrorResponseEntity> {
        e.printStackTrace()
        val message = e.bindingResult.allErrors[0].defaultMessage
        return ResponseEntity.badRequest().body(message?.let { ErrorResponseEntity(HttpStatus.BAD_REQUEST.name, it) })
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ErrorResponseEntity> {
        e.printStackTrace() // 로그 기록 등을 할 수 있습니다.
        return ErrorResponseEntity.toResponseEntity(ErrorCode.INTERNAL_SERVER_ERROR)
    }
}