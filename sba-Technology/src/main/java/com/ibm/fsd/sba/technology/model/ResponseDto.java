package com.ibm.fsd.sba.technology.model;

import lombok.Data;

@Data
public class ResponseDto<T> {

    private String code;
    private String message;
    private T data;

    public ResponseDto(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseDto<T> getSuccessResponseDto(T data) {
        return new ResponseDto("00000", "SUCCESS", data);
    }
}
