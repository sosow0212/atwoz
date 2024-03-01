package com.atwoz.member.fixture.info.dto.request;

import static com.atwoz.member.domain.info.style.Style.FASHION;
import static com.atwoz.member.domain.info.style.Style.GENTLE;
import static com.atwoz.member.domain.info.style.Style.POSITIVE;
import static com.atwoz.member.domain.info.style.Style.PURE;

import com.atwoz.member.application.info.dto.StyleUpdateRequest;
import com.atwoz.member.application.info.dto.StyleWriteRequest;
import java.util.List;

@SuppressWarnings("NonAsciiCharacters")
public class StylesRequestFixture {

    public static List<StyleWriteRequest> 회원_스타일_생성_요청() {
        return List.of(
                new StyleWriteRequest(POSITIVE.getCode()),
                new StyleWriteRequest(GENTLE.getCode())
        );
    }

    public static List<StyleUpdateRequest> 회원_스타일_수정_요청() {
        return List.of(
                new StyleUpdateRequest(PURE.getCode()),
                new StyleUpdateRequest(FASHION.getCode())
        );
    }
}
