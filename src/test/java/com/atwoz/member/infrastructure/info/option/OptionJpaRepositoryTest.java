package com.atwoz.member.infrastructure.info.option;

import static com.atwoz.member.fixture.domain.member.MemberFixture.일반_유저_생성;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import com.atwoz.member.domain.info.option.Drink;
import com.atwoz.member.domain.info.option.Graduate;
import com.atwoz.member.domain.info.option.Mbti;
import com.atwoz.member.domain.info.option.Option;
import com.atwoz.member.domain.info.option.Religion;
import com.atwoz.member.domain.info.option.Smoke;
import com.atwoz.member.domain.member.Member;
import com.atwoz.member.infrastructure.member.MemberJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@DataJpaTest
class OptionJpaRepositoryTest {

    @Autowired
    private OptionJpaRepository optionJpaRepository;

    @Autowired
    private MemberJpaRepository memberJpaRepository;

    private Member member;

    @BeforeEach
    void init() {
        member = memberJpaRepository.save(일반_유저_생성());
    }

    @Test
    void option을_저장한다() {
        // given
        long memberId = member.getId();
        Smoke smoke = Smoke.NEVER;
        Religion religion = Religion.CHRIST;
        Drink drink = Drink.NEVER;
        Mbti mbti = Mbti.INFJ;
        Graduate graduate = Graduate.SEOUL_FOURTH;

        Option newOption = new Option(memberId, smoke, religion, drink, mbti, graduate);

        // when
        Option saveOption = optionJpaRepository.save(newOption);

        // then
        assertThat(saveOption)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(newOption);
    }

    @Test
    void option을_조회한다() {
        // given
        long memberId = member.getId();
        Smoke smoke = Smoke.NEVER;
        Religion religion = Religion.CHRIST;
        Drink drink = Drink.NEVER;
        Mbti mbti = Mbti.INFJ;
        Graduate graduate = Graduate.SEOUL_FOURTH;

        Option newOption = new Option(memberId, smoke, religion, drink, mbti, graduate);
        Option saveOption = optionJpaRepository.save(newOption);

        // when
        Optional<Option> findOption = optionJpaRepository.findByMemberId(memberId);

        // then
        assertSoftly(softly -> {
            softly.assertThat(findOption).isPresent();
            softly.assertThat(findOption.get())
                    .usingRecursiveComparison()
                    .ignoringFields("id")
                    .isEqualTo(saveOption);
        });
    }
}
