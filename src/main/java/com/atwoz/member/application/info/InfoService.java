package com.atwoz.member.application.info;

import com.atwoz.global.event.Events;
import com.atwoz.member.application.event.info.HobbyWroteEvent;
import com.atwoz.member.application.event.info.OptionWroteEvent;
import com.atwoz.member.application.event.info.ProfileWroteEvent;
import com.atwoz.member.application.event.info.StyleWroteEvent;
import com.atwoz.member.application.info.dto.HobbyWriteRequest;
import com.atwoz.member.application.info.dto.InfoWriteRequest;
import com.atwoz.member.application.info.dto.StyleWriteRequest;
import com.atwoz.member.domain.info.InfoRepository;
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

    public InfoSearchResponse findInfo(final Long memberId) {
        return infoRepository.findByMemberId(memberId);
    }
}
