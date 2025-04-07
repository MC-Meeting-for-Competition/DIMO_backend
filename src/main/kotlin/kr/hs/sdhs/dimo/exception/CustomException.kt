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
    STUDENT_ALREADY_EXIST("이미 등록된 학생입니다", HttpStatus.CONFLICT),
    NOT_AGREEMENT_POLICY("개인정보 정책 동의가 필요합니다", HttpStatus.FORBIDDEN),
    WRONG_EMAIL_ADDRESS("학교 이메일 계정으로 시도해주세요", HttpStatus.BAD_REQUEST),
    STUDENT_NOT_FOUND("학생 정보를 찾을 수 없습니다", HttpStatus.NOT_FOUND),
    TEACHER_NOT_FOUND("교사 정보를 찾을 수 없습니다", HttpStatus.NOT_FOUND),
    INVALID_USER_TYPE("학생/교사 중 선택해주세요", HttpStatus.BAD_REQUEST),
    TEACHER_ALREADY_EXIST("이미 등록된 교사입니다", HttpStatus.CONFLICT),
    DUPLICATE_EQUIPMENT("중복된 기자재입니다", HttpStatus.CONFLICT),
    RENT_NOT_FOUND("대여 정보를 찾을 수 없습니다", HttpStatus.NOT_FOUND),
    ALREADY_PROCESSED("이미 처리된 요청입니다", HttpStatus.CONFLICT),
    RETURN_NOT_ACCEPT("반납할 수 없는 기자재입니다", HttpStatus.FORBIDDEN),
    EQUIPMENT_NOT_AVAILABLE("기자재를 대여할 수 없습니다", HttpStatus.BAD_REQUEST),
    NOT_ACCEPT_LONG_RENT("장기 대여는 교사만 가능합니다", HttpStatus.FORBIDDEN),
    RETURN_DATE_TOO_LONG("6개월 이상 대여는 불가능합니다", HttpStatus.BAD_REQUEST),
    ADMIN_NOT_FOUND("관리자를 찾을 수 없습니다", HttpStatus.NOT_FOUND),
    WRONG_USERNAME_OR_PASSWORD("올바르지 않은 아이디 또는 비밀번호입니다", HttpStatus.BAD_REQUEST),
    NOT_ACCEPTED_ADMIN_ACCOUNT("허용되지 않은 관리자 계정입니다", HttpStatus.FORBIDDEN),
    EXIST_ADMIN("이미 존재하는 관리자 계정입니다", HttpStatus.FORBIDDEN),
}


class CustomException(val errorCode: ErrorCode) : RuntimeException(errorCode.message)