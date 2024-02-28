package com.atwoz.member.application.info;

import static com.atwoz.member.domain.info.hobby.Hobby.COOK;
import static com.atwoz.member.domain.info.hobby.Hobby.WINE;
import static org.assertj.core.api.Assertions.assertThat;

import com.atwoz.member.application.info.hobby.HobbyService;
import com.atwoz.member.domain.info.hobby.MemberHobby;
import com.atwoz.member.domain.info.hobby.MemberHobbyRepository;
import com.atwoz.member.infrastructure.info.MemberHobbyFakeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import java.util.List;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class MemberHobbyServiceTest {

    private HobbyService hobbyService;
    private MemberHobbyRepository memberHobbyRepository;

    @BeforeEach
    void init() {
        memberHobbyRepository = new MemberHobbyFakeRepository();
        hobbyService = new HobbyService(memberHobbyRepository);
    }

    @Test
    void 회원의_모든_취미를_저장한다() {
        // given
        Long memberId = 1L;
        List<String> hobbyCodes = List.of(WINE.getCode(), COOK.getCode());

        // when
        hobbyService.saveMemberHobbies(memberId, hobbyCodes);

        // then
        List<MemberHobby> saveMemberHobbies = memberHobbyRepository.findAllByMemberId(memberId);
        assertThat(saveMemberHobbies.size()).isEqualTo(hobbyCodes.size());
    }

    @Test
    void 회원의_모든_취미를_삭제한다() {
        // given
        Long memberId = 1L;
        List<String> hobbyCodes = List.of(WINE.getCode(), COOK.getCode());
        hobbyService.saveMemberHobbies(memberId, hobbyCodes);

        // when
        hobbyService.deleteMemberHobbies(memberId);

        // then
        assertThat(memberHobbyRepository.findAllByMemberId(memberId)).isEmpty();
    }

    @Test
    void 회원의_모든_취미를_조회한다() {
        // given
        Long memberId = 1L;
        List<String> hobbyCodes = List.of(WINE.getCode(), COOK.getCode());
        hobbyService.saveMemberHobbies(memberId, hobbyCodes);

        // when
        List<MemberHobby> memberHobbies = hobbyService.findMemberHobbies(memberId);

        // then
        assertThat(memberHobbies.size()).isEqualTo(hobbyCodes.size());
    }
}
