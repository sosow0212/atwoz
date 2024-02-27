package com.atwoz.member.application.info;

import com.atwoz.global.event.Events;
import com.atwoz.member.application.event.HobbyWroteEvent;
import com.atwoz.member.application.event.OptionWroteEvent;
import com.atwoz.member.application.event.ProfileWroteEvent;
import com.atwoz.member.application.event.StyleWroteEvent;
import com.atwoz.member.application.info.dto.HobbyWriteRequest;
import com.atwoz.member.application.info.dto.InfoWriteRequest;
import com.atwoz.member.application.info.dto.StyleWriteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class InfoService {

    @Transactional
    public void writeInfo(final Long memberId, final InfoWriteRequest request) {
        List<String> hobbyNames = extractHobbyNamesFromInfoWriteRequest(request);
        List<String> styleNames = extractStyleNamesFromInfoWriteRequest(request);

        Events.raise(new ProfileWroteEvent(memberId, request.profile()));
        Events.raise(new OptionWroteEvent(memberId, request.option()));

        Events.raise(new HobbyWroteEvent(memberId, hobbyNames));
        Events.raise(new StyleWroteEvent(memberId, styleNames));
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
}
