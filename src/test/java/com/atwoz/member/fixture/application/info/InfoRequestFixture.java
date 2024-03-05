package com.atwoz.member.fixture.application.info;

import static com.atwoz.member.fixture.application.info.hobby.HobbiesRequestFixture.회원_취미_생성_요청;
import static com.atwoz.member.fixture.application.info.hobby.HobbiesRequestFixture.회원_취미_수정_요청;
import static com.atwoz.member.fixture.application.info.option.OptionRequestFixture.회원_옵션_수정_요청;
import static com.atwoz.member.fixture.application.info.option.OptionRequestFixture.회원_옵션_생성_요청;
import static com.atwoz.member.fixture.application.info.profile.ProfileRequestFixture.회원_프로필_수정_요청;
import static com.atwoz.member.fixture.application.info.profile.ProfileRequestFixture.회원_프로필_생성_요청;
import static com.atwoz.member.fixture.application.info.style.StylesRequestFixture.회원_스타일_생성_요청;
import static com.atwoz.member.fixture.application.info.style.StylesRequestFixture.회원_스타일_수정_요청;

import com.atwoz.member.application.info.hobby.dto.HobbyUpdateRequest;
import com.atwoz.member.application.info.hobby.dto.HobbyWriteRequest;
import com.atwoz.member.application.info.dto.InfoUpdateRequest;
import com.atwoz.member.application.info.dto.InfoWriteRequest;
import com.atwoz.member.application.info.style.dto.StyleUpdateRequest;
import com.atwoz.member.application.info.style.dto.StyleWriteRequest;
import com.atwoz.member.application.info.option.dto.OptionUpdateRequest;
import com.atwoz.member.application.info.option.dto.OptionWriteRequest;
import com.atwoz.member.application.info.profile.dto.ProfileUpdateRequest;
import com.atwoz.member.application.info.profile.dto.ProfileWriteRequest;
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
