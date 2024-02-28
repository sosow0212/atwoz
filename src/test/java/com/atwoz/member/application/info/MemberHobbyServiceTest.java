package com.atwoz.member.application.info;

import static com.atwoz.member.domain.info.hobby.Hobby.COOK;
import static com.atwoz.member.domain.info.hobby.Hobby.WALK;
import static com.atwoz.member.domain.info.hobby.Hobby.WINE;
import static com.atwoz.member.domain.info.hobby.Hobby.WRITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import com.atwoz.member.application.info.hobby.MemberHobbyService;
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

    private MemberHobbyService memberHobbyService;
    private MemberHobbyRepository memberHobbyRepository;

    @BeforeEach
    void init() {
        memberHobbyRepository = new MemberHobbyFakeRepository();
        memberHobbyService = new MemberHobbyService(memberHobbyRepository);
    }

    @Test
    void 회원의_모든_취미를_저장한다() {
        // given
        Long memberId = 1L;
        List<String> hobbyCodes = List.of(WINE.getCode(), COOK.getCode());
        List<MemberHobby> expectedMemberHobbies = List.of(
                new MemberHobby(memberId, WINE),
                new MemberHobby(memberId, COOK)
        );

        // when
        memberHobbyService.saveMemberHobbies(memberId, hobbyCodes);

        // then
        List<MemberHobby> saveMemberHobbies = memberHobbyRepository.findAllByMemberId(memberId);
        assertThat(saveMemberHobbies).containsAll(expectedMemberHobbies);
    }

    @Test
    void 회원의_취미를_수정한다() {
        // given
        Long memberId = 1L;
        List<String> hobbyCodes = List.of(WINE.getCode(), COOK.getCode());
        List<String> updateHobbyCodes = List.of(WALK.getCode(), WRITE.getCode());
        List<MemberHobby> originMemberHobbies = List.of(
                new MemberHobby(memberId, WINE),
                new MemberHobby(memberId, COOK)
        );
        List<MemberHobby> updateMemberHobbies = List.of(
                new MemberHobby(memberId, WALK),
                new MemberHobby(memberId, WRITE)
        );

        memberHobbyService.saveMemberHobbies(memberId, hobbyCodes);

        // when
        memberHobbyService.updateMemberHobbies(memberId, updateHobbyCodes);

        // then
        List<MemberHobby> saveMemberHobbies = memberHobbyRepository.findAllByMemberId(memberId);
        assertSoftly(softly -> {
            softly.assertThat(saveMemberHobbies).doesNotContainAnyElementsOf(originMemberHobbies);
            softly.assertThat(saveMemberHobbies).containsAll(updateMemberHobbies);
        });
    }
}
