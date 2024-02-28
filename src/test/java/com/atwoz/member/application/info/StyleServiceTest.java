package com.atwoz.member.application.info;

import static com.atwoz.member.domain.info.style.Style.GENTLE;
import static com.atwoz.member.domain.info.style.Style.POSITIVE;
import static org.assertj.core.api.Assertions.assertThat;

import com.atwoz.member.application.info.style.StyleService;
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
class StyleServiceTest {

    private StyleService styleService;
    private MemberStyleRepository memberStyleRepository;

    @BeforeEach
    void init() {
        memberStyleRepository = new MemberStyleFakeRepository();
        styleService = new StyleService(memberStyleRepository);
    }

    @Test
    void 회원의_모든_스타일을_저장한다() {
        // given
        Long memberId = 1L;
        List<String> styleCodes = List.of(POSITIVE.getCode(), GENTLE.getCode());

        // when
        styleService.saveMemberStyles(memberId, styleCodes);

        // then
        List<MemberStyle> saveStyles = memberStyleRepository.findAllByMemberId(memberId);
        assertThat(saveStyles.size()).isEqualTo(2);
    }

    @Test
    void 회원의_모든_스타일을_삭제한다() {
        // given
        Long memberId = 1L;
        List<String> styleCodes = List.of(POSITIVE.getCode(), GENTLE.getCode());
        styleService.saveMemberStyles(memberId, styleCodes);

        // when
        styleService.deleteMemberStyles(memberId);

        // then
        assertThat(memberStyleRepository.findAllByMemberId(memberId)).isEmpty();
    }

    @Test
    void 회원의_모든_스타일을_조회한다() {
        // given
        Long memberId = 1L;
        List<String> styleCodes = List.of(POSITIVE.getCode(), GENTLE.getCode());
        styleService.saveMemberStyles(memberId, styleCodes);

        // when
        List<MemberStyle> memberStyles = styleService.findMemberStyles(memberId);

        // then
        assertThat(memberStyles.size()).isEqualTo(2);
    }
}
