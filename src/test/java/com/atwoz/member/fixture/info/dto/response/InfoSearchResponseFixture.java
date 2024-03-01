package com.atwoz.member.fixture.info.dto.response;

import static com.atwoz.member.domain.info.hobby.Hobby.COOK;
import static com.atwoz.member.domain.info.hobby.Hobby.WINE;
import static com.atwoz.member.domain.info.style.Style.GENTLE;
import static com.atwoz.member.domain.info.style.Style.POSITIVE;
import static com.atwoz.member.fixture.info.dto.response.OptionSearchResponseFixture.회원_옵션_조회_응답;
import static com.atwoz.member.fixture.info.dto.response.ProfileSearchResponseFixture.회원_프로필_조회_응답;

import com.atwoz.member.ui.info.dto.HobbySearchResponse;
import com.atwoz.member.ui.info.dto.InfoSearchResponse;
import com.atwoz.member.ui.info.dto.StyleSearchResponse;
import com.atwoz.member.ui.info.dto.option.OptionSearchResponse;
import com.atwoz.member.ui.info.dto.profile.ProfileSearchResponse;
import java.util.List;

@SuppressWarnings("NonAsciiCharacters")
public class InfoSearchResponseFixture {

    public static InfoSearchResponse 회원_정보_조회_응답() {
        ProfileSearchResponse profileSearchResponse = 회원_프로필_조회_응답();
        OptionSearchResponse optionSearchResponse = 회원_옵션_조회_응답();

        List<HobbySearchResponse> hobbies = List.of(
                new HobbySearchResponse(WINE.getCode()),
                new HobbySearchResponse(COOK.getCode())
        );
        List<StyleSearchResponse> styles = List.of(
                new StyleSearchResponse(POSITIVE.getCode()),
                new StyleSearchResponse(GENTLE.getCode())
        );

        return new InfoSearchResponse(profileSearchResponse, optionSearchResponse, hobbies, styles);
    }
}
