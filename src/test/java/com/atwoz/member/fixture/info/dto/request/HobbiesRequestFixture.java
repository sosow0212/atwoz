package com.atwoz.member.fixture.info.dto.request;

import static com.atwoz.member.domain.info.hobby.Hobby.COOK;
import static com.atwoz.member.domain.info.hobby.Hobby.DRAMA;
import static com.atwoz.member.domain.info.hobby.Hobby.WINE;
import static com.atwoz.member.domain.info.hobby.Hobby.WRITE;

import com.atwoz.member.application.info.hobby.dto.HobbyUpdateRequest;
import com.atwoz.member.application.info.hobby.dto.HobbyWriteRequest;
import java.util.List;

@SuppressWarnings("NonAsciiCharacters")
public class HobbiesRequestFixture {

    public static List<HobbyWriteRequest> 회원_취미_생성_요청() {
        return List.of(
                new HobbyWriteRequest(WINE.getCode()),
                new HobbyWriteRequest(COOK.getCode())
        );
    }

    public static List<HobbyUpdateRequest> 회원_취미_수정_요청() {
        return List.of(
                new HobbyUpdateRequest(DRAMA.getCode()),
                new HobbyUpdateRequest(WRITE.getCode())
        );
    }
}
