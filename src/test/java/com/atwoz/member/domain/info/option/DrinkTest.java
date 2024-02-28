package com.atwoz.member.domain.info.option;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.atwoz.member.exception.exceptions.info.option.DrinkInvalidException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class DrinkTest {

    @Test
    void 올바른_선택_시_조회된다() {
        // given
        String drinkName = "전혀 마시지 않음";

        // when & then
        assertDoesNotThrow(() -> Drink.findByName(drinkName));
    }

    @Test
    void 올바른_음주_단계가_아니면_예외가_발생한다() {
        // given
        String drinkName = "hello";

        // when & then
        assertThatThrownBy(() -> Drink.findByName(drinkName))
                .isInstanceOf(DrinkInvalidException.class);
    }
}
