package com.atwoz.member.domain.info.hobby;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import com.atwoz.member.exception.exceptions.info.hobby.HobbyDuplicateException;
import com.atwoz.member.exception.exceptions.info.hobby.HobbyNotFoundException;
import com.atwoz.member.exception.exceptions.info.hobby.HobbySizeException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import java.util.List;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class HobbyTest {

    @Test
    void 취미가_존재하면_정상적으로_가져온다() {
        // given
        List<String> hobbyCodes = List.of("B001", "B002", "B007");
        List<Hobby> expectedHobbies = List.of(Hobby.TRIP, Hobby.GOLF, Hobby.DANCE);

        // when
        List<Hobby> findHobbies = Hobby.findAllByCodes(hobbyCodes);

        // then
        assertSoftly(softly -> {
            softly.assertThat(findHobbies.size()).isEqualTo(hobbyCodes.size());
            softly.assertThat(findHobbies).containsAll(expectedHobbies);
        });
    }

    @Test
    void 취미_갯수가_초과되면_예외가_발생한다() {
        // given
        List<String> hobbyCodes = List.of("B001", "B002", "B007", "B017");

        // when & then
        assertThatThrownBy(() -> Hobby.findAllByCodes(hobbyCodes))
                .isInstanceOf(HobbySizeException.class);
    }

    @Test
    void 아예_선택하지_않으면_예외가_발생한다() {
        // given
        List<String> hobbyCodes = List.of();

        // when & then
        assertThatThrownBy(() -> Hobby.findAllByCodes(hobbyCodes))
                .isInstanceOf(HobbySizeException.class);
    }

    @Test
    void 없는_취미를_선택하면_예외가_발생한다() {
        // given
        List<String> hobbyCodes = List.of("ABCD");

        // when & then
        assertThatThrownBy(() -> Hobby.findAllByCodes(hobbyCodes))
                .isInstanceOf(HobbyNotFoundException.class);
    }

    @Test
    void 중복_선택하면_예외가_발생한다() {
        // given
        List<String> hobbyCodes = List.of("B001", "B001");

        // when & then
        assertThatThrownBy(() -> Hobby.findAllByCodes(hobbyCodes))
                .isInstanceOf(HobbyDuplicateException.class);
    }
}
