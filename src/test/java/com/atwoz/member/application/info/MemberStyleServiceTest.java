package com.atwoz.member.application.info;

import static com.atwoz.member.domain.info.style.Style.GENTLE;
import static com.atwoz.member.domain.info.style.Style.POLITE;
import static com.atwoz.member.domain.info.style.Style.POSITIVE;
import static com.atwoz.member.domain.info.style.Style.PROUD;
import static com.atwoz.member.fixture.info.MemberStylesFixture.회원_수정_스타일_생성;
import static com.atwoz.member.fixture.info.MemberStylesFixture.회원_일반_스타일_생성;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import com.atwoz.member.application.info.style.MemberStyleService;
import com.atwoz.member.domain.info.style.MemberStyle;
import com.atwoz.member.domain.info.style.MemberStyleRepository;
import com.atwoz.member.infrastructure.info.MemberStyleFakeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import java.util.List;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class MemberStyleServiceTest {

    private MemberStyleService memberStyleService;
    private MemberStyleRepository memberStyleRepository;

    @BeforeEach
    void init() {
        memberStyleRepository = new MemberStyleFakeRepository();
        memberStyleService = new MemberStyleService(memberStyleRepository);
    }

    @Test
    void 회원의_모든_스타일을_저장한다() {
        // given
        Long memberId = 1L;
        List<String> styleCodes = List.of(POSITIVE.getCode(), GENTLE.getCode());
        List<MemberStyle> expectedMemberStyles = 회원_일반_스타일_생성();

        // when
        memberStyleService.saveMemberStyles(memberId, styleCodes);
        List<MemberStyle> saveMemberStyles = memberStyleRepository.findAllByMemberId(memberId);

        // then
        assertThat(saveMemberStyles).containsAll(expectedMemberStyles);
    }

    @Test
    void 회원의_스타일을_수정한다() {
        // given
        Long memberId = 1L;
        List<String> styleCodes = List.of(POSITIVE.getCode(), GENTLE.getCode());
        List<String> updateStyleCodes = List.of(POLITE.getCode(), PROUD.getCode());
        List<MemberStyle> originMemberStyles = 회원_일반_스타일_생성();
        List<MemberStyle> updateMemberStyles = 회원_수정_스타일_생성();

        memberStyleService.saveMemberStyles(memberId, styleCodes);

        // when
        memberStyleService.updateMemberStyles(memberId, updateStyleCodes);
        List<MemberStyle> saveMemberStyles = memberStyleRepository.findAllByMemberId(memberId);

        // then
        assertSoftly(softly -> {
            softly.assertThat(saveMemberStyles).doesNotContainAnyElementsOf(originMemberStyles);
            softly.assertThat(saveMemberStyles).containsAll(updateMemberStyles);
        });
    }
}
