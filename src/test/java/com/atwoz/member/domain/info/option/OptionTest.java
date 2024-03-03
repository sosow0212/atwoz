package com.atwoz.member.domain.info.option;

import static com.atwoz.member.fixture.domain.info.option.OptionFixture.회원_일반_옵션_생성;
import static com.atwoz.member.fixture.application.info.option.OptionRequestFixture.회원_옵션_수정_요청_내부;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import com.atwoz.member.domain.info.option.dto.InnerOptionUpdateRequest;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class OptionTest {

    @Test
    void 내용_수정_검증() {
        // given
        Option option = 회원_일반_옵션_생성();
        InnerOptionUpdateRequest request = 회원_옵션_수정_요청_내부();

        // when
        option.updateContentsFrom(request);

        // then
        assertSoftly(softly -> {
            softly.assertThat(option.getSmoke().getName()).isEqualTo(request.smokeName());
            softly.assertThat(option.getReligion().getName()).isEqualTo(request.religionName());
            softly.assertThat(option.getDrink().getName()).isEqualTo(request.drinkName());
            softly.assertThat(option.getMbti().name()).isEqualTo(request.mbtiName());
            softly.assertThat(option.getGraduate().getName()).isEqualTo(request.graduateName());
        });
    }
}
