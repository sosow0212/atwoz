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
class HobbyNameTest {

    @Test
    void 취미가_존재하면_정상적으로_가져온다() {
        // given
        List<String> hobbyNames = List.of("국내여행 · 해외여행", "골프", "댄스");
        List<HobbyName> expectedHobbyNames = List.of(HobbyName.TRIP, HobbyName.GOLF, HobbyName.DANCE);

        // when
        List<HobbyName> findHobbyNames = HobbyName.findAllByNames(hobbyNames);

        // then
        assertSoftly(softly -> {
            softly.assertThat(findHobbyNames.size()).isEqualTo(hobbyNames.size());
            softly.assertThat(findHobbyNames).containsAll(expectedHobbyNames);
        });
    }

    @Test
    void 취미_갯수가_초과되면_예외가_발생한다() {
        // given
        List<String> hobbyNames = List.of("국내여행 · 해외여행", "골프", "댄스", "쇼핑");

        // when & then
        assertThatThrownBy(() -> HobbyName.findAllByNames(hobbyNames))
                .isInstanceOf(HobbySizeException.class);
    }

    @Test
    void 아예_선택하지_않으면_예외가_발생한다() {
        // given
        List<String> hobbyNames = List.of();

        // when & then
        assertThatThrownBy(() -> HobbyName.findAllByNames(hobbyNames))
                .isInstanceOf(HobbySizeException.class);
    }

    @Test
    void 없는_취미를_선택하면_예외가_발생한다() {
        // given
        List<String> hobbyNames = List.of("hello");

        // when & then
        assertThatThrownBy(() -> HobbyName.findAllByNames(hobbyNames))
                .isInstanceOf(HobbyNotFoundException.class);
    }

    @Test
    void 중복_선택하면_예외가_발생한다() {
        // given
        List<String> hobbyNames = List.of("댄스", "댄스");

        // when & then
        assertThatThrownBy(() -> HobbyName.findAllByNames(hobbyNames))
                .isInstanceOf(HobbyDuplicateException.class);
    }
}
