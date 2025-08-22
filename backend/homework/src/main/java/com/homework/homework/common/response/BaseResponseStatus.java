package com.homework.homework.common.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BaseResponseStatus {
    SUCCESS(true, HttpStatus.OK, 200, "요청에 성공하였습니다."),
    AUTHORIZATION_SUCCESS(true, HttpStatus.OK, 200, "토큰 발급에 성공하였습니다."),
    BAD_REQUEST(false, HttpStatus.BAD_REQUEST, 400, "입력값을 확인해주세요."),
    UNAUTHORIZED(false, HttpStatus.UNAUTHORIZED, 401, "인증이 필요합니다."),
    FORBIDDEN(false, HttpStatus.FORBIDDEN, 403, "권한이 없습니다."),
    NOT_FOUND(false, HttpStatus.NOT_FOUND, 404, "대상을 찾을 수 없습니다."),

    EXTENSION_NOT_FOUND(false, HttpStatus.NOT_FOUND, 1001, "확장자 명을 찾을 수 없습니다."),
    CUSTOM_NOT_CHANGED(false, HttpStatus.BAD_REQUEST, 1002, "사용자 정의 확장자는 활성화/비활성화를 변경할 수 없습니다."),
    NOT_NORMAL_NAME(false, HttpStatus.BAD_REQUEST, 1003, "정상적인 확장자 명이 아닙니다."),
    EXTENSION_ALREADY_EXISTS(false, HttpStatus.BAD_REQUEST, 1004, "이미 존재하는 확장자 명입니다."),
    ONLY_CUSTOM_DELETE(false, HttpStatus.BAD_REQUEST, 1005, "사용자 정의 확장자만 삭제할 수 있습니다.")
;

    private final boolean isSuccess;
    @JsonIgnore
    private final HttpStatus httpStatus;
    private final int code;
    private final String message;

    BaseResponseStatus(boolean isSuccess, HttpStatus httpStatus, int code, String message) {
        this.isSuccess = isSuccess;
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}
