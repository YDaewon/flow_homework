package com.homework.homework.common.response;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final BaseResponseStatus baseResponseStatus;

    public BusinessException(BaseResponseStatus baseResponseStatus) {
        super(baseResponseStatus.getMessage());
        this.baseResponseStatus = baseResponseStatus;
    }
}
