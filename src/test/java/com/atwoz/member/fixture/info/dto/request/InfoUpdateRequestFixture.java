package com.atwoz.member.fixture.info.dto.request;

import static com.atwoz.member.domain.info.hobby.Hobby.DRAMA;
import static com.atwoz.member.domain.info.hobby.Hobby.WRITE;
import static com.atwoz.member.domain.info.style.Style.FASHION;
import static com.atwoz.member.domain.info.style.Style.PURE;
import static com.atwoz.member.fixture.info.dto.request.OptionUpdateRequestFixture.옵션_수정_요청;
import static com.atwoz.member.fixture.info.dto.request.ProfileUpdateRequestFixture.프로필_수정_요청;

import com.atwoz.member.application.info.dto.HobbyUpdateRequest;
import com.atwoz.member.application.info.dto.InfoUpdateRequest;
import com.atwoz.member.application.info.dto.StyleUpdateRequest;
import com.atwoz.member.application.info.dto.option.OptionUpdateRequest;
import com.atwoz.member.application.info.dto.profile.ProfileUpdateRequest;
import java.util.List;

@SuppressWarnings("NonAsciiCharacters")
public class InfoUpdateRequestFixture {

    public static InfoUpdateRequest 정보_수정_요청() {
        ProfileUpdateRequest profileUpdateRequest = 프로필_수정_요청();
        OptionUpdateRequest optionUpdateRequest = 옵션_수정_요청();

        List<HobbyUpdateRequest> hobbies = List.of(
                new HobbyUpdateRequest(DRAMA.getCode()),
                new HobbyUpdateRequest(WRITE.getCode())
        );
        List<StyleUpdateRequest> styles = List.of(
                new StyleUpdateRequest(PURE.getCode()),
                new StyleUpdateRequest(FASHION.getCode())
        );

        return new InfoUpdateRequest(
                profileUpdateRequest,
                optionUpdateRequest,
                hobbies,
                styles
        );
    }
}
