package com.atwoz.global.exception.exceptions;

public class PropertyNotFoundException extends RuntimeException {

    public PropertyNotFoundException() {
        super("속성 값에 맞는 타입을 찾지 못했습니다.");
    }
}
