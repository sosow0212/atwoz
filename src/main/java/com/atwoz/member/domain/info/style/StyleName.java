package com.atwoz.member.domain.info.style;

import com.atwoz.member.exception.exceptions.info.style.StyleDuplicateException;
import com.atwoz.member.exception.exceptions.info.style.StyleNotFoundException;
import com.atwoz.member.exception.exceptions.info.style.StyleSizeException;
import lombok.Getter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public enum StyleName {

    FASHION("패션 센스"),
    FRIENDLY("다정다감"),
    FUNNY("유머 감각"),
    GOOD_RATIO("좋은 비율"),
    PRETTY_SMILE("웃는 미소가 예쁨"),
    LOW_VOICE("저음 목소리"),
    CONSIDERABLE("넓은 배려심"),
    EXTROVERT("외향형"),
    INTROVERT("내향형"),
    ATTRACTIVE("애교쟁이"),
    GOOD_SKIN("좋은 피부"),
    POSITIVE("긍정적"),
    CUTE("귀여움"),
    RELIABLE("듬직함"),
    PRETTY_HAND("예쁜 손"),
    LOVELY("사랑스러움"),
    MOOD("분위기 있음"),
    REAL_BETTER("실물이 더 좋음"),
    INNOCENT("천진난만"),
    INTELLECTUAL("지적 섹시"),
    POLITE("예의 바름"),
    GENTLE("진중함"),
    GOOD_SPEECH("나긋나긋한 말투"),
    ONE_LOVE("순정파"),
    PURE("청순함"),
    BOLD_DOUBLE_EYELID("짙은 쌍꺼풀"),
    NONE_DOUBLE_EYELID("무쌍꺼풀"),
    INNER_DOUBLE_EYELID("속쌍꺼풀"),
    PROUD("도도함"),
    MAKER("분위기 메이커");

    private static final int MAX_SIZE = 3;

    private final String name;

    StyleName(final String name) {
        this.name = name;
    }

    public static List<StyleName> findAllByNames(final List<String> names) {
        validateSize(names);
        return names.stream()
                .map(StyleName::from)
                .toList();
    }

    public static void validateSize(final List<String> names) {
        if (names.isEmpty()) {
            throw new StyleSizeException();
        }

        Set<String> uniqueNames = new HashSet<>(names);
        if (uniqueNames.size() != names.size()) {
            throw new StyleDuplicateException();
        }

        int validSize = Arrays.stream(values())
                .filter(hobbyName -> uniqueNames.contains(hobbyName.getName()))
                .toList()
                .size();
        if (validSize > MAX_SIZE) {
            throw new StyleSizeException();
        }
    }

    private static StyleName from(final String name) {
        return Arrays.stream(values())
                .filter(styleName -> styleName.isSame(name))
                .findFirst()
                .orElseThrow(StyleNotFoundException::new);
    }

    private boolean isSame(final String name) {
        return this.name.equals(name);
    }
}
