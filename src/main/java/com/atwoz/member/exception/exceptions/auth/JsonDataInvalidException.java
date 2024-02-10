package com.atwoz.member.exception.exceptions.auth;

public class JsonDataInvalidException extends RuntimeException {

    public JsonDataInvalidException() {
        super("유효하지 않은 JSON 데이터입니다.");
    }
}
