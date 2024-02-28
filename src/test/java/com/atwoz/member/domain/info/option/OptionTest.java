package com.atwoz.member.domain.info.option;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class OptionTest {

    @Test
    void 내용_수정_검증() {
        // given
        Long memberId = 1L;
        Smoke smoke = Smoke.NEVER;
        Religion religion = Religion.CHRIST;
        Drink drink = Drink.NEVER;
        Mbti mbti = Mbti.INFJ;
        Graduate graduate = Graduate.SEOUL_FOURTH;

        Option option = new Option(memberId, smoke, religion, drink, mbti, graduate);

        Smoke updateSmoke = Smoke.ELECTRONIC;
        Religion updateReligion = Religion.BUDDHA;
        Drink updateDrink = Drink.ENJOY;
        Mbti updateMbti = Mbti.ENFJ;
        Graduate updateGraduate = Graduate.DOCTOR;

        // when
        option.updateContents(updateSmoke, updateReligion, updateDrink, updateMbti, updateGraduate);

        // then
        assertSoftly(softly -> {
            softly.assertThat(option.getSmoke()).isEqualTo(updateSmoke);
            softly.assertThat(option.getReligion()).isEqualTo(updateReligion);
            softly.assertThat(option.getDrink()).isEqualTo(updateDrink);
            softly.assertThat(option.getMbti()).isEqualTo(updateMbti);
            softly.assertThat(option.getGraduate()).isEqualTo(updateGraduate);
        });
    }
}
