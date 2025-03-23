package kr.hs.sdhs.dimo.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(val message: String, val httpStatus: HttpStatus) {
    EQUIPMENT_NOT_FOUND("기자재를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    RENTAL_ALREADY_IN_PROGRESS("대여 중인 기자재입니다.", HttpStatus.BAD_REQUEST),
    EQUIPMENT_ALREADY_RETURNED("기자재가 이미 반환되었습니다.", HttpStatus.BAD_REQUEST),
    EQUIPMENT_CHECKING_PROGRESS("기자재를 점검 중입니다.", HttpStatus.BAD_REQUEST),
    EQUIPMENT_IS_BROKEN("기자재가 파손되었습니다", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR("서버 내부 오류", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST("잘못된 요청입니다", HttpStatus.BAD_REQUEST),
    EQUIPMENT_TYPE_NOT_FOUND("등록되지 않은 기기 종류입니다", HttpStatus.NOT_FOUND),
    EQUIPMENT_IS_RENTED("대여 중인 기자재입니다.", HttpStatus.BAD_REQUEST),
    TYPE_HAS_EQUIPMENT("기자재 품목에 기자재가 등록되어있습니다", HttpStatus.BAD_REQUEST),
}


class CustomException(val errorCode: ErrorCode) : RuntimeException(errorCode.message)