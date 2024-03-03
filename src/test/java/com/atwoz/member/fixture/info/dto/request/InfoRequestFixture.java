package com.atwoz.member.fixture.info.dto.request;

import static com.atwoz.member.fixture.info.dto.request.HobbiesRequestFixture.회원_취미_생성_요청;
import static com.atwoz.member.fixture.info.dto.request.HobbiesRequestFixture.회원_취미_수정_요청;
import static com.atwoz.member.fixture.info.dto.request.OptionRequestFixture.회원_옵션_수정_요청;
import static com.atwoz.member.fixture.info.dto.request.OptionRequestFixture.회원_옵션_생성_요청;
import static com.atwoz.member.fixture.info.dto.request.ProfileRequestFixture.회원_프로필_수정_요청;
import static com.atwoz.member.fixture.info.dto.request.ProfileRequestFixture.회원_프로필_생성_요청;
import static com.atwoz.member.fixture.info.dto.request.StylesRequestFixture.회원_스타일_생성_요청;
import static com.atwoz.member.fixture.info.dto.request.StylesRequestFixture.회원_스타일_수정_요청;

import com.atwoz.member.application.info.dto.hobby.HobbyUpdateRequest;
import com.atwoz.member.application.info.dto.hobby.HobbyWriteRequest;
import com.atwoz.member.application.info.dto.InfoUpdateRequest;
import com.atwoz.member.application.info.dto.InfoWriteRequest;
import com.atwoz.member.application.info.dto.style.StyleUpdateRequest;
import com.atwoz.member.application.info.dto.style.StyleWriteRequest;
import com.atwoz.member.application.info.dto.option.OptionUpdateRequest;
import com.atwoz.member.application.info.dto.option.OptionWriteRequest;
import com.atwoz.member.application.info.dto.profile.ProfileUpdateRequest;
import com.atwoz.member.application.info.dto.profile.ProfileWriteRequest;
import java.util.List;

@SuppressWarnings("NonAsciiCharacters")
public class InfoRequestFixture {

    public static InfoWriteRequest 회원_정보_생성_요청() {
        ProfileWriteRequest profileWriteRequest = 회원_프로필_생성_요청();
        OptionWriteRequest optionWriteRequest = 회원_옵션_생성_요청();

        List<HobbyWriteRequest> hobbyWriteRequests = 회원_취미_생성_요청();
        List<StyleWriteRequest> styleWriteRequests = 회원_스타일_생성_요청();

        return new InfoWriteRequest(
                profileWriteRequest,
                optionWriteRequest,
                hobbyWriteRequests,
                styleWriteRequests
        );
    }

    public static InfoUpdateRequest 회원_정보_수정_요청() {
        ProfileUpdateRequest profileUpdateRequest = 회원_프로필_수정_요청();
        OptionUpdateRequest optionUpdateRequest = 회원_옵션_수정_요청();

        List<HobbyUpdateRequest> hobbies = 회원_취미_수정_요청();
        List<StyleUpdateRequest> styles = 회원_스타일_수정_요청();

        return new InfoUpdateRequest(
                profileUpdateRequest,
                optionUpdateRequest,
                hobbies,
                styles
        );
    }
}
