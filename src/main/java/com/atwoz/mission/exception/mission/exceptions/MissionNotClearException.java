package com.atwoz.mission.exception.mission.exceptions;

public class MissionNotClearException extends RuntimeException {

    public MissionNotClearException() {
        super("미션을 클리어하지 않아 보상을 얻을 수 없습니다.");
    }
}
