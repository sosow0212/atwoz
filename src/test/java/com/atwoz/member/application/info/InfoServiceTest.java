package com.atwoz.member.application.info;

import static com.atwoz.member.fixture.info.dto.request.InfoUpdateRequestFixture.회원_정보_수정_요청;
import static com.atwoz.member.fixture.info.dto.request.InfoWriteRequestFixture.회원_정보_생성_요청;
import static com.atwoz.member.fixture.info.dto.response.InfoSearchResponseFixture.회원_정보_조회_응답;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import com.atwoz.helper.IntegrationHelper;
import com.atwoz.member.application.event.info.HobbyUpdatedEvent;
import com.atwoz.member.application.event.info.HobbyWroteEvent;
import com.atwoz.member.application.event.info.OptionUpdatedEvent;
import com.atwoz.member.application.event.info.OptionWroteEvent;
import com.atwoz.member.application.event.info.ProfileUpdatedEvent;
import com.atwoz.member.application.event.info.ProfileWroteEvent;
import com.atwoz.member.application.event.info.StyleUpdatedEvent;
import com.atwoz.member.application.event.info.StyleWroteEvent;
import com.atwoz.member.application.info.dto.InfoUpdateRequest;
import com.atwoz.member.application.info.dto.InfoWriteRequest;
import com.atwoz.member.ui.info.dto.InfoSearchResponse;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.event.ApplicationEvents;
import org.springframework.test.context.event.RecordApplicationEvents;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@RecordApplicationEvents
class InfoServiceTest extends IntegrationHelper {

    @Autowired
    private InfoService infoService;

    @Autowired
    private ApplicationEvents events;
    
    @Test
    void 회원의_모든_정보를_저장한다() {
        // given
        Long memberId = 1L;
        InfoWriteRequest request = 회원_정보_생성_요청();

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

    @Test
    void 회원의_모든_정보를_수정한다() {
        // given
        Long memberId = 1L;
        InfoWriteRequest infoWriteRequest = 회원_정보_생성_요청();
        InfoUpdateRequest infoUpdateRequest = 회원_정보_수정_요청();

        infoService.writeInfo(memberId, infoWriteRequest);

        // when
        infoService.updateInfo(memberId, infoUpdateRequest);

        // then
        int profileUpdatedEventCount = (int) events.stream(ProfileUpdatedEvent.class).count();
        int optionUpdatedEventCount = (int) events.stream(OptionUpdatedEvent.class).count();
        int hobbyUpdatedEventCount = (int) events.stream(HobbyUpdatedEvent.class).count();
        int styleUpdatedEventCount = (int) events.stream(StyleUpdatedEvent.class).count();

        assertSoftly(softly -> {
            softly.assertThat(profileUpdatedEventCount).isEqualTo(1);
            softly.assertThat(optionUpdatedEventCount).isEqualTo(1);
            softly.assertThat(hobbyUpdatedEventCount).isEqualTo(1);
            softly.assertThat(styleUpdatedEventCount).isEqualTo(1);
        });
    }

    @Test
    void 회원의_모든_정보를_조회한다() {
        // given
        Long memberId = 1L;
        InfoWriteRequest request = 회원_정보_생성_요청();
        infoService.writeInfo(memberId, request);
        InfoSearchResponse expectedInfoSearchResponse = 회원_정보_조회_응답();

        // when
        InfoSearchResponse infoSearchResponse = infoService.findInfo(memberId);

        // then
        assertThat(infoSearchResponse).isEqualTo(expectedInfoSearchResponse);
    }
}
