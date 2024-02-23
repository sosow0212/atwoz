package com.atwoz.member.infrastructure.info;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import com.atwoz.member.domain.info.hobby.Hobby;
import com.atwoz.member.domain.info.hobby.HobbyName;
import com.atwoz.member.domain.member.Member;
import com.atwoz.member.fixture.member.MemberFixture;
import com.atwoz.member.infrastructure.info.hobby.HobbyJpaRepository;
import com.atwoz.member.infrastructure.member.MemberJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import java.util.stream.Stream;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@DataJpaTest
class HobbyJpaRepositoryTest {

    @Autowired
    private HobbyJpaRepository hobbyJpaRepository;

    @Autowired
    private MemberJpaRepository memberJpaRepository;

    private Member member;

    @BeforeEach
    void init() {
        member = memberJpaRepository.save(MemberFixture.일반_유저_생성());
    }

    @Test
    void 회원_취미를_저장한다() {
        // given
        HobbyName hobbyName = HobbyName.ANIMATION;
        Long memberId = member.getId();
        Hobby memberHobby = new Hobby(memberId, hobbyName);

        // when
        Hobby saveMemberHobby = hobbyJpaRepository.save(memberHobby);

        // then
        assertThat(saveMemberHobby)
                .usingRecursiveComparison()
                .isEqualTo(memberHobby);
    }

    @Test
    void 회원_취미를_조회한다() {
        // given
        HobbyName hobbyName = HobbyName.ANIMATION;
        Long memberId = member.getId();
        Hobby memberHobby = new Hobby(memberId, hobbyName);
        Hobby saveMemberHobby = hobbyJpaRepository.save(memberHobby);

        // when
        List<Hobby> findMemberHobbies = hobbyJpaRepository.findAllByMemberId(memberId);

        // then
        assertSoftly(softly -> {
            softly.assertThat(findMemberHobbies.size()).isEqualTo(1);
            softly.assertThat(findMemberHobbies).contains(saveMemberHobby);
        });
    }

    @Test
    void 회원_취미_목록을_저장_및_조회한다() {
        // given
        Long memberId = member.getId();
        List<Hobby> memberHobbies = Stream.of(HobbyName.BICYCLE, HobbyName.DANCE)
                .map(hobbyName -> new Hobby(memberId, hobbyName))
                .map(hobby -> hobbyJpaRepository.save(hobby))
                .toList();

        // when
        List<Hobby> findMemberHobbies = hobbyJpaRepository.findAllByMemberId(memberId);

        // then
        assertSoftly(softly -> {
            softly.assertThat(findMemberHobbies.size()).isEqualTo(2);
            softly.assertThat(findMemberHobbies.containsAll(memberHobbies)).isTrue();
        });
    }
}
