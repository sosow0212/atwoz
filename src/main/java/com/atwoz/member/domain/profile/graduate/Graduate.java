package com.atwoz.member.domain.profile.graduate;

public enum Graduate {

    SEOUL_FOURTH("서울 4년제"),
    ETC_FOURTH("지방 4년제"),
    PROFESSIONAL("전문대"),
    FOREIGN("해외대"),
    MASTER("석사"),
    DOCTOR("박사"),
    LAW_SCHOOL("로스쿨"),
    HIGH_SCHOOL("고등학교 졸업"),
    ETC("기타");

    Graduate(final String name) {
    }
}
