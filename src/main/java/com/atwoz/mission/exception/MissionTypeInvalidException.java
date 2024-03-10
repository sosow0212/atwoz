package com.atwoz.mission.exception;

public class MissionTypeInvalidException extends RuntimeException {

    public MissionTypeInvalidException() {
        super("미션 타입은 daily 혹은 challenge 로 입력해주세요.");
    }
}
