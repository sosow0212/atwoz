package com.atwoz.member.application.info.hobby;

import static com.atwoz.member.domain.info.hobby.Hobby.COOK;
import static com.atwoz.member.domain.info.hobby.Hobby.DANCE;
import static com.atwoz.member.domain.info.hobby.Hobby.DRAMA;
import static com.atwoz.member.domain.info.hobby.Hobby.WINE;
import static com.atwoz.member.domain.info.hobby.Hobby.WRITE;
import static com.atwoz.member.fixture.domain.info.hobby.MemberHobbiesFixture.회원_수정_취미_생성;
import static com.atwoz.member.fixture.domain.info.hobby.MemberHobbiesFixture.회원_일반_취미_생성;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import com.atwoz.member.domain.info.hobby.MemberHobby;
import com.atwoz.member.domain.info.hobby.MemberHobbyRepository;
import com.atwoz.member.exception.exceptions.info.hobby.HobbyDuplicateException;
import com.atwoz.member.exception.exceptions.info.hobby.HobbySizeException;
import com.atwoz.member.exception.exceptions.info.hobby.InvalidHobbyException;
import com.atwoz.member.infrastructure.info.hobby.MemberHobbyFakeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
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
        List<MemberHobby> expectedMemberHobbies = 회원_일반_취미_생성();

        // when
        memberHobbyService.saveMemberHobbies(memberId, hobbyCodes);
        List<MemberHobby> saveMemberHobbies = memberHobbyRepository.findAllByMemberId(memberId);

        // then
        assertThat(saveMemberHobbies).containsAll(expectedMemberHobbies);
    }

    @Test
    void 회원의_취미를_수정한다() {
        // given
        Long memberId = 1L;
        List<MemberHobby> originMemberHobbies = 회원_일반_취미_생성();
        List<MemberHobby> updateMemberHobbies = 회원_수정_취미_생성();
        List<String> updateHobbyCodes = List.of(DRAMA.getCode(), WRITE.getCode());

        memberHobbyRepository.saveAll(originMemberHobbies);

        // when
        memberHobbyService.updateMemberHobbies(memberId, updateHobbyCodes);
        List<MemberHobby> saveMemberHobbies = memberHobbyRepository.findAllByMemberId(memberId);

        // then
        assertSoftly(softly -> {
            softly.assertThat(saveMemberHobbies).doesNotContainAnyElementsOf(originMemberHobbies);
            softly.assertThat(saveMemberHobbies).containsAll(updateMemberHobbies);
        });
    }

    @Nested
    class 취미_서비스_예외 {

        @Test
        void 제한을_벗어난_취미를_저장하면_예외가_발생한다() {
            // given
            Long memberId = 1L;
            List<String> hobbyCodes = List.of(WINE.getCode(), DRAMA.getCode(), DANCE.getCode(), WRITE.getCode());

            // when & then
            assertThatThrownBy(() -> memberHobbyService.saveMemberHobbies(memberId, hobbyCodes))
                    .isInstanceOf(HobbySizeException.class);
        }

        @Test
        void 취미를_아예_저장하지_않으면_예외가_발생한다() {
            // given
            Long memberId = 1L;
            List<String> hobbyCodes = List.of();

            // when & then
            assertThatThrownBy(() -> memberHobbyService.saveMemberHobbies(memberId, hobbyCodes))
                    .isInstanceOf(HobbySizeException.class);
        }

        @Test
        void 취미를_중복_저장하면_예외가_발생한다() {
            // given
            Long memberId = 1L;
            List<String> hobbyCodes = List.of(WINE.getCode(), WINE.getCode());

            // when & then
            assertThatThrownBy(() -> memberHobbyService.saveMemberHobbies(memberId, hobbyCodes))
                    .isInstanceOf(HobbyDuplicateException.class);
        }

        @Test
        void 없는_취미를_저장하면_예외가_발생한다() {
            // given
            Long memberId = 1L;
            List<String> hobbyCodes = List.of("hello");

            // when & then
            assertThatThrownBy(() -> memberHobbyService.saveMemberHobbies(memberId, hobbyCodes))
                    .isInstanceOf(InvalidHobbyException.class);
        }

        @Test
        void 제한을_벗어난_취미를_수정하면_예외가_발생한다() {
            // given
            Long memberId = 1L;
            List<MemberHobby> originMemberHobbies = 회원_일반_취미_생성();
            List<String> updateHobbyCodes = List.of(WINE.getCode(), DRAMA.getCode(), DANCE.getCode(), WRITE.getCode());

            memberHobbyRepository.saveAll(originMemberHobbies);

            // when & then
            assertThatThrownBy(() -> memberHobbyService.updateMemberHobbies(memberId, updateHobbyCodes))
                    .isInstanceOf(HobbySizeException.class);
        }

        @Test
        void 수정할_취미를_아예_선택하지_않으면_예외가_발생한다() {
            // given
            Long memberId = 1L;
            List<MemberHobby> originMemberHobbies = 회원_일반_취미_생성();
            List<String> updateHobbyCodes = List.of();

            memberHobbyRepository.saveAll(originMemberHobbies);

            // when & then
            assertThatThrownBy(() -> memberHobbyService.updateMemberHobbies(memberId, updateHobbyCodes))
                    .isInstanceOf(HobbySizeException.class);
        }

        @Test
        void 취미를_중복_수정하면_예외가_발생한다() {
            // given
            Long memberId = 1L;
            List<MemberHobby> originMemberHobbies = 회원_일반_취미_생성();
            List<String> updateHobbyCodes = List.of(WINE.getCode(), WINE.getCode());

            memberHobbyRepository.saveAll(originMemberHobbies);

            // when & then
            assertThatThrownBy(() -> memberHobbyService.updateMemberHobbies(memberId, updateHobbyCodes))
                    .isInstanceOf(HobbyDuplicateException.class);
        }

        @Test
        void 없는_취미로_수정하면_예외가_발생한다() {
            // given
            Long memberId = 1L;
            List<MemberHobby> originMemberHobbies = 회원_일반_취미_생성();
            List<String> updateHobbyCodes = List.of("hello");

            memberHobbyRepository.saveAll(originMemberHobbies);

            // when & then
            assertThatThrownBy(() -> memberHobbyService.updateMemberHobbies(memberId, updateHobbyCodes))
                    .isInstanceOf(InvalidHobbyException.class);
        }
    }
}
