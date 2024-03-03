package com.atwoz.member.application.info;

import com.atwoz.global.event.Events;
import com.atwoz.member.domain.info.hobby.event.HobbyUpdatedEvent;
import com.atwoz.member.domain.info.hobby.event.HobbyWroteEvent;
import com.atwoz.member.domain.info.option.event.OptionUpdatedEvent;
import com.atwoz.member.domain.info.option.event.OptionWroteEvent;
import com.atwoz.member.domain.info.profile.event.ProfileUpdatedEvent;
import com.atwoz.member.domain.info.profile.event.ProfileWroteEvent;
import com.atwoz.member.domain.info.style.event.StyleUpdatedEvent;
import com.atwoz.member.domain.info.style.event.StyleWroteEvent;
import com.atwoz.member.application.info.hobby.dto.HobbyUpdateRequest;
import com.atwoz.member.application.info.hobby.dto.HobbyWriteRequest;
import com.atwoz.member.application.info.dto.InfoUpdateRequest;
import com.atwoz.member.application.info.dto.InfoWriteRequest;
import com.atwoz.member.application.info.style.dto.StyleUpdateRequest;
import com.atwoz.member.application.info.style.dto.StyleWriteRequest;
import com.atwoz.member.domain.info.InfoRepository;
import com.atwoz.member.exception.exceptions.info.InfoNotFoundException;
import com.atwoz.member.ui.info.dto.InfoSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class InfoService {

    private final InfoRepository infoRepository;

    @Transactional
    public void writeInfo(final Long memberId, final InfoWriteRequest request) {
        List<String> hobbyCodes = extractHobbyNamesFromInfoWriteRequest(request);
        List<String> styleCodes = extractStyleNamesFromInfoWriteRequest(request);

        Events.raise(new ProfileWroteEvent(memberId, request.profile()));
        Events.raise(new OptionWroteEvent(memberId, request.option()));

        Events.raise(new HobbyWroteEvent(memberId, hobbyCodes));
        Events.raise(new StyleWroteEvent(memberId, styleCodes));
    }

    private List<String> extractHobbyNamesFromInfoWriteRequest(final InfoWriteRequest request) {
        return request.hobbies()
                .stream()
                .map(HobbyWriteRequest::hobby)
                .toList();
    }

    private List<String> extractStyleNamesFromInfoWriteRequest(final InfoWriteRequest request) {
        return request.styles()
                .stream()
                .map(StyleWriteRequest::style)
                .toList();
    }

    @Transactional
    public void updateInfo(final Long memberId, final InfoUpdateRequest request) {
        List<String> hobbyCodes = extractHobbyNamesFromInfoUpdateRequest(request);
        List<String> styleCodes = extractStyleNamesFromInfoUpdateRequest(request);

        Events.raise(new ProfileUpdatedEvent(memberId, request.profile()));
        Events.raise(new OptionUpdatedEvent(memberId, request.option()));

        Events.raise(new HobbyUpdatedEvent(memberId, hobbyCodes));
        Events.raise(new StyleUpdatedEvent(memberId, styleCodes));
    }

    private List<String> extractHobbyNamesFromInfoUpdateRequest(final InfoUpdateRequest request) {
        return request.hobbies()
                .stream()
                .map(HobbyUpdateRequest::hobby)
                .toList();
    }

    private List<String> extractStyleNamesFromInfoUpdateRequest(final InfoUpdateRequest request) {
        return request.styles()
                .stream()
                .map(StyleUpdateRequest::style)
                .toList();
    }

    public InfoSearchResponse findInfo(final Long memberId) {
        return infoRepository.findByMemberId(memberId)
                .orElseThrow(InfoNotFoundException::new);
    }
}
