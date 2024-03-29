package com.atwoz.member.infrastructure.info.hobby;

import static com.atwoz.member.fixture.domain.member.MemberFixture.일반_유저_생성;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import com.atwoz.member.domain.info.hobby.Hobby;
import com.atwoz.member.domain.info.hobby.MemberHobby;
import com.atwoz.member.domain.member.Member;
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
class MemberHobbyJpaRepositoryTest {

    @Autowired
    private MemberHobbyJpaRepository memberHobbyJpaRepository;

    @Autowired
    private MemberJpaRepository memberJpaRepository;

    private Member member;

    @BeforeEach
    void init() {
        member = memberJpaRepository.save(일반_유저_생성());
    }

    @Test
    void 회원_취미를_저장한다() {
        // given
        Hobby hobby = Hobby.ANIMATION;
        Long memberId = member.getId();
        MemberHobby memberHobby = new MemberHobby(memberId, hobby);

        // when
        MemberHobby saveMemberHobby = memberHobbyJpaRepository.save(memberHobby);

        // then
        assertThat(saveMemberHobby)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(memberHobby);
    }

    @Test
    void 회원_취미를_조회한다() {
        // given
        Hobby hobby = Hobby.ANIMATION;
        Long memberId = member.getId();
        MemberHobby memberHobby = new MemberHobby(memberId, hobby);
        MemberHobby saveMemberHobby = memberHobbyJpaRepository.save(memberHobby);

        // when
        List<MemberHobby> findMemberHobbies = memberHobbyJpaRepository.findAllByMemberId(memberId);

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
        List<MemberHobby> memberHobbies = Stream.of(Hobby.BICYCLE, Hobby.DANCE)
                .map(hobby -> new MemberHobby(memberId, hobby))
                .map(memberHobby -> memberHobbyJpaRepository.save(memberHobby))
                .toList();

        // when
        List<MemberHobby> findMemberHobbies = memberHobbyJpaRepository.findAllByMemberId(memberId);

        // then
        assertSoftly(softly -> {
            softly.assertThat(findMemberHobbies.size()).isEqualTo(2);
            softly.assertThat(findMemberHobbies.containsAll(memberHobbies)).isTrue();
        });
    }
}
