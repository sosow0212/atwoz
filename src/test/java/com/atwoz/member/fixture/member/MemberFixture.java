package com.atwoz.member.fixture.member;

import com.atwoz.member.domain.member.Member;
import com.atwoz.member.domain.member.MemberRole;

public class MemberFixture {

    public static Member 일반_유저_생성() {
        return Member.builder()
                .email("email@email.com")
                .nickname("nickname")
                .memberRole(MemberRole.MEMBER)
                .build();
    }

    public static Member 어드민_유저_생성() {
        return Member.builder()
                .email("email@email.com")
                .nickname("nickname")
                .memberRole(MemberRole.ADMIN)
                .build();
    }
}
