package com.atwoz.member.fixture.info;

import com.atwoz.member.domain.info.hobby.Hobby;
import com.atwoz.member.domain.info.hobby.MemberHobby;
import java.util.List;

@SuppressWarnings("NonAsciiCharacters")
public class MemberHobbiesFixture {

    public static List<MemberHobby> 회원_일반_취미_생성() {
        Long memberId = 1L;
        return List.of(
                new MemberHobby(memberId, Hobby.WINE),
                new MemberHobby(memberId, Hobby.COOK)
        );
    }

    public static List<MemberHobby> 회원_수정_취미_생성() {
        Long memberId = 1L;
        return List.of(
                new MemberHobby(memberId, Hobby.DRAMA),
                new MemberHobby(memberId, Hobby.WRITE)
        );
    }
}
