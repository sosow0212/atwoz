package com.atwoz.member.fixture.infrastructure.info.style;

import static com.atwoz.member.domain.info.style.Style.GENTLE;
import static com.atwoz.member.domain.info.style.Style.POSITIVE;

import com.atwoz.member.infrastructure.info.style.dto.StyleSearchResponse;
import java.util.List;

@SuppressWarnings("NonAsciiCharacters")
public class StylesSearchResponseFixture {

    public static List<StyleSearchResponse> 회원_스타일_조회_응답() {
        return List.of(
                new StyleSearchResponse(POSITIVE.getCode()),
                new StyleSearchResponse(GENTLE.getCode())
        );
    }
}
