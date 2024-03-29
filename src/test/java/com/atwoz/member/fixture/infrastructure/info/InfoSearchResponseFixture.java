package com.atwoz.member.fixture.infrastructure.info;

import static com.atwoz.member.fixture.infrastructure.info.hobby.HobbiesSearchResponseFixture.회원_취미_조회_응답;
import static com.atwoz.member.fixture.infrastructure.info.option.OptionSearchResponseFixture.회원_옵션_조회_응답;
import static com.atwoz.member.fixture.infrastructure.info.profile.ProfileSearchResponseFixture.회원_프로필_조회_응답;
import static com.atwoz.member.fixture.infrastructure.info.style.StylesSearchResponseFixture.회원_스타일_조회_응답;

import com.atwoz.member.infrastructure.info.hobby.dto.HobbySearchResponse;
import com.atwoz.member.infrastructure.info.dto.InfoSearchResponse;
import com.atwoz.member.infrastructure.info.style.dto.StyleSearchResponse;
import com.atwoz.member.infrastructure.info.option.dto.OptionSearchResponse;
import com.atwoz.member.infrastructure.info.profile.dto.ProfileSearchResponse;
import java.util.List;

@SuppressWarnings("NonAsciiCharacters")
public class InfoSearchResponseFixture {

    public static InfoSearchResponse 회원_정보_조회_응답() {
        ProfileSearchResponse profileSearchResponse = 회원_프로필_조회_응답();
        OptionSearchResponse optionSearchResponse = 회원_옵션_조회_응답();

        List<HobbySearchResponse> hobbies = 회원_취미_조회_응답();
        List<StyleSearchResponse> styles = 회원_스타일_조회_응답();

        return new InfoSearchResponse(profileSearchResponse, optionSearchResponse, hobbies, styles);
    }
}
