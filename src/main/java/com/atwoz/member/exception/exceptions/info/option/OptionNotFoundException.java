package com.atwoz.member.exception.exceptions.info.option;

public class OptionNotFoundException extends RuntimeException {

    public OptionNotFoundException() {
        super("Option이 저장되지 않았습니다.");
    }
}
