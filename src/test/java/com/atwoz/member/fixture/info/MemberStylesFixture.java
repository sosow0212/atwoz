package com.atwoz.member.fixture.info;

import com.atwoz.member.domain.info.style.MemberStyle;
import com.atwoz.member.domain.info.style.Style;
import java.util.List;

@SuppressWarnings("NonAsciiCharacters")
public class MemberStylesFixture {

    public static List<MemberStyle> 회원_일반_스타일_생성() {
        Long memberId = 1L;
        return List.of(
                new MemberStyle(memberId, Style.POSITIVE),
                new MemberStyle(memberId, Style.GENTLE)
        );
    }

    public static List<MemberStyle> 회원_수정_스타일_생성() {
        Long memberId = 1L;
        return List.of(
                new MemberStyle(memberId, Style.POLITE),
                new MemberStyle(memberId, Style.PROUD)
        );
    }
}
