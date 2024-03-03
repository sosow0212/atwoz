package com.atwoz.member.application.info;

import static com.atwoz.member.fixture.application.info.InfoRequestFixture.회원_정보_생성_요청;
import static com.atwoz.member.fixture.application.info.InfoRequestFixture.회원_정보_수정_요청;
import static com.atwoz.member.fixture.infrastructure.info.InfoSearchResponseFixture.회원_정보_조회_응답;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.atwoz.global.event.Events;
import com.atwoz.member.application.info.hobby.event.HobbyUpdatedEvent;
import com.atwoz.member.application.info.hobby.event.HobbyWroteEvent;
import com.atwoz.member.application.info.option.event.OptionWroteEvent;
import com.atwoz.member.application.info.profile.event.ProfileUpdatedEvent;
import com.atwoz.member.application.info.profile.event.ProfileWroteEvent;
import com.atwoz.member.application.info.style.event.StyleUpdatedEvent;
import com.atwoz.member.application.info.style.event.StyleWroteEvent;
import com.atwoz.member.application.info.dto.InfoUpdateRequest;
import com.atwoz.member.application.info.dto.InfoWriteRequest;
import com.atwoz.member.domain.info.InfoRepository;
import com.atwoz.member.infrastructure.info.InfoFakeRepository;
import com.atwoz.member.infrastructure.info.dto.InfoSearchResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.event.RecordApplicationEvents;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@RecordApplicationEvents
class InfoServiceTest {

    private InfoService infoService;

    private InfoRepository infoRepository;

    @MockBean
    private ApplicationEventPublisher eventPublisher;

    @BeforeEach
    void init() {
        infoRepository = new InfoFakeRepository();
        infoService = new InfoService(infoRepository);
        eventPublisher = mock(ApplicationEventPublisher.class);
        Events.setPublisher(eventPublisher);
    }

    @Test
    void 회원의_모든_정보를_저장한다() {
        // given
        Long memberId = 1L;
        InfoWriteRequest request = 회원_정보_생성_요청();

        // when
        infoService.writeInfo(memberId, request);

        // then
        assertSoftly(softly -> {
            softly.assertThatCode(() -> verify(eventPublisher, times(1)).publishEvent(any(ProfileWroteEvent.class)))
                    .doesNotThrowAnyException();
            softly.assertThatCode(() -> verify(eventPublisher, times(1)).publishEvent(any(OptionWroteEvent.class)))
                    .doesNotThrowAnyException();
            softly.assertThatCode(() -> verify(eventPublisher, times(1)).publishEvent(any(HobbyWroteEvent.class)))
                    .doesNotThrowAnyException();
            softly.assertThatCode(() -> verify(eventPublisher, times(1)).publishEvent(any(StyleWroteEvent.class)))
                    .doesNotThrowAnyException();
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
        assertSoftly(softly -> {
            softly.assertThatCode(() -> verify(eventPublisher, times(1)).publishEvent(any(ProfileUpdatedEvent.class)))
                    .doesNotThrowAnyException();
            softly.assertThatCode(() -> verify(eventPublisher, times(1)).publishEvent(any(ProfileUpdatedEvent.class)))
                    .doesNotThrowAnyException();
            softly.assertThatCode(() -> verify(eventPublisher, times(1)).publishEvent(any(HobbyUpdatedEvent.class)))
                    .doesNotThrowAnyException();
            softly.assertThatCode(() -> verify(eventPublisher, times(1)).publishEvent(any(StyleUpdatedEvent.class)))
                    .doesNotThrowAnyException();
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
