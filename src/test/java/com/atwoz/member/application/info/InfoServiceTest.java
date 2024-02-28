package com.atwoz.member.application.info;

import static com.atwoz.member.domain.info.hobby.Hobby.COOK;
import static com.atwoz.member.domain.info.hobby.Hobby.WINE;
import static com.atwoz.member.domain.info.style.Style.GENTLE;
import static com.atwoz.member.domain.info.style.Style.POSITIVE;
import static com.atwoz.member.fixture.info.dto.request.OptionWriteRequestFixture.옵션_생성_요청;
import static com.atwoz.member.fixture.info.dto.request.ProfileWriteRequestFixture.프로필_생성_요청;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import com.atwoz.member.application.event.HobbyWroteEvent;
import com.atwoz.member.application.event.OptionWroteEvent;
import com.atwoz.member.application.event.ProfileWroteEvent;
import com.atwoz.member.application.event.StyleWroteEvent;
import com.atwoz.member.application.info.dto.HobbyWriteRequest;
import com.atwoz.member.application.info.dto.InfoWriteRequest;
import com.atwoz.member.application.info.dto.StyleWriteRequest;
import com.atwoz.member.application.info.dto.option.OptionWriteRequest;
import com.atwoz.member.application.info.dto.profile.ProfileWriteRequest;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.ApplicationEvents;
import org.springframework.test.context.event.RecordApplicationEvents;
import java.util.List;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@SpringBootTest
@RecordApplicationEvents
class InfoServiceTest {

    @Autowired
    private InfoService infoService;

    @Autowired
    private ApplicationEvents events;
    
    @Test
    void 모든_정보를_저장한다() {
        // given
        Long memberId = 1L;
        ProfileWriteRequest profileWriteRequest = 프로필_생성_요청();
        OptionWriteRequest optionWriteRequest = 옵션_생성_요청();

        List<HobbyWriteRequest> hobbyWriteRequests = List.of(
            new HobbyWriteRequest(WINE.getCode()),
            new HobbyWriteRequest(COOK.getCode())
        );

        List<StyleWriteRequest> styleWriteRequests = List.of(
            new StyleWriteRequest(POSITIVE.getCode()),
            new StyleWriteRequest(GENTLE.getCode())
        );

        InfoWriteRequest request = new InfoWriteRequest(
                profileWriteRequest,
                optionWriteRequest,
                hobbyWriteRequests,
                styleWriteRequests
        );

        // when
        infoService.writeInfo(memberId, request);

        // then
        int profileWroteEventCount = (int) events.stream(ProfileWroteEvent.class).count();
        int optionWroteEventCount = (int) events.stream(OptionWroteEvent.class).count();
        int hobbyWroteEventCount = (int) events.stream(HobbyWroteEvent.class).count();
        int styleWroteEventCount = (int) events.stream(StyleWroteEvent.class).count();

        assertSoftly(softly -> {
            softly.assertThat(profileWroteEventCount).isEqualTo(1);
            softly.assertThat(optionWroteEventCount).isEqualTo(1);
            softly.assertThat(hobbyWroteEventCount).isEqualTo(1);
            softly.assertThat(styleWroteEventCount).isEqualTo(1);
        });
    }
}
