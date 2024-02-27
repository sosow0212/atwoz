package com.atwoz.member.application.info;

import static com.atwoz.member.domain.info.hobby.HobbyName.COOK;
import static com.atwoz.member.domain.info.hobby.HobbyName.WINE;
import static org.assertj.core.api.Assertions.assertThat;

import com.atwoz.member.application.info.hobby.HobbyService;
import com.atwoz.member.domain.info.hobby.Hobby;
import com.atwoz.member.domain.info.hobby.HobbyRepository;
import com.atwoz.member.infrastructure.info.HobbyFakeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import java.util.List;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class HobbyServiceTest {

    private HobbyService hobbyService;
    private HobbyRepository hobbyRepository;

    @BeforeEach
    void init() {
        hobbyRepository = new HobbyFakeRepository();
        hobbyService = new HobbyService(hobbyRepository);
    }

    @Test
    void 회원의_모든_취미를_저장한다() {
        // given
        Long memberId = 1L;
        List<String> hobbyCodes = List.of(WINE.getCode(), COOK.getCode());

        // when
        hobbyService.saveMemberHobbies(memberId, hobbyCodes);

        // then
        List<Hobby> saveHobbies = hobbyRepository.findAllByMemberId(memberId);
        assertThat(saveHobbies.size()).isEqualTo(2);
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
        assertThat(hobbyRepository.findAllByMemberId(memberId)).isEmpty();
    }

    @Test
    void 회원의_모든_취미를_조회한다() {
        // given
        Long memberId = 1L;
        List<String> hobbyCodes = List.of(WINE.getCode(), COOK.getCode());
        hobbyService.saveMemberHobbies(memberId, hobbyCodes);

        // when
        List<Hobby> memberHobbies = hobbyService.findMemberHobbies(memberId);

        // then
        assertThat(memberHobbies.size()).isEqualTo(2);
    }
}
