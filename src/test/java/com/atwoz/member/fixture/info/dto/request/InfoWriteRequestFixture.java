package com.atwoz.member.fixture.info.dto.request;

import static com.atwoz.member.domain.info.hobby.Hobby.COOK;
import static com.atwoz.member.domain.info.hobby.Hobby.WINE;
import static com.atwoz.member.domain.info.style.Style.GENTLE;
import static com.atwoz.member.domain.info.style.Style.POSITIVE;
import static com.atwoz.member.fixture.info.dto.request.OptionWriteRequestFixture.회원_옵션_생성_요청;
import static com.atwoz.member.fixture.info.dto.request.ProfileWriteRequestFixture.회원_프로필_생성_요청;

import com.atwoz.member.application.info.dto.HobbyWriteRequest;
import com.atwoz.member.application.info.dto.InfoWriteRequest;
import com.atwoz.member.application.info.dto.StyleWriteRequest;
import com.atwoz.member.application.info.dto.option.OptionWriteRequest;
import com.atwoz.member.application.info.dto.profile.ProfileWriteRequest;
import java.util.List;

@SuppressWarnings("NonAsciiCharacters")
public class InfoWriteRequestFixture {

    public static InfoWriteRequest 회원_정보_생성_요청() {
        ProfileWriteRequest profileWriteRequest = 회원_프로필_생성_요청();
        OptionWriteRequest optionWriteRequest = 회원_옵션_생성_요청();

        List<HobbyWriteRequest> hobbyWriteRequests = List.of(
                new HobbyWriteRequest(WINE.getCode()),
                new HobbyWriteRequest(COOK.getCode())
        );
        List<StyleWriteRequest> styleWriteRequests = List.of(
                new StyleWriteRequest(POSITIVE.getCode()),
                new StyleWriteRequest(GENTLE.getCode())
        );

        return new InfoWriteRequest(
                profileWriteRequest,
                optionWriteRequest,
                hobbyWriteRequests,
                styleWriteRequests
        );
    }
}
