package com.atwoz.mission.exception.membermissions.exceptions;

public class MemberMissionNotFoundException extends RuntimeException {

    public MemberMissionNotFoundException() {
        super("미션을 가지고 있지 않습니다.");
    }
}
