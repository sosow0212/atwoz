package com.atwoz.member.fixture.info.dto.response;

import static com.atwoz.member.domain.info.hobby.Hobby.COOK;
import static com.atwoz.member.domain.info.hobby.Hobby.WINE;

import com.atwoz.member.infrastructure.info.hobby.dto.HobbySearchResponse;
import java.util.List;

@SuppressWarnings("NonAsciiCharacters")
public class HobbiesSearchResponseFixture {

    public static List<HobbySearchResponse> 회원_취미_조회_응답() {
        return List.of(
                new HobbySearchResponse(WINE.getCode()),
                new HobbySearchResponse(COOK.getCode())
        );
    }
}
