package com.atwoz.member.domain.info.option;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.atwoz.member.exception.exceptions.info.option.InvalidMbtiException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class MbtiTest {

    @Test
    void 올바른_선택_시_조회된다() {
        // given
        String mbtiName = "INFJ";

        // when & then
        assertDoesNotThrow(() -> Mbti.findByName(mbtiName));
    }

    @Test
    void 올바른_mbti가_아니면_예외가_발생한다() {
        // given
        String mbtiName = "hello";

        // when & then
        assertThatThrownBy(() -> Mbti.findByName(mbtiName))
                .isInstanceOf(InvalidMbtiException.class);
    }
}
