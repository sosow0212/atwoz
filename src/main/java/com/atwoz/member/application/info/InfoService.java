package com.atwoz.member.application.info;

import com.atwoz.global.event.Events;
import com.atwoz.member.application.event.HobbyWroteEvent;
import com.atwoz.member.application.event.OptionWroteEvent;
import com.atwoz.member.application.event.ProfileWroteEvent;
import com.atwoz.member.application.event.StyleWroteEvent;
import com.atwoz.member.application.info.dto.HobbyWriteRequest;
import com.atwoz.member.application.info.dto.InfoWriteRequest;
import com.atwoz.member.application.info.dto.StyleWriteRequest;
import com.atwoz.member.application.info.dto.option.OptionWriteRequest;
import com.atwoz.member.application.info.dto.profile.ProfileWriteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class InfoService {

    @Transactional
    public void writeProfile(final Long memberId, final InfoWriteRequest request) {
        ProfileWriteRequest profileWriteRequest = request.profile();
        OptionWriteRequest optionWriteRequest = request.option();
        List<String> hobbyNames = request.hobbies()
                .stream()
                .map(HobbyWriteRequest::hobby)
                .toList();
        List<String> styleNames = request.styles()
                .stream()
                .map(StyleWriteRequest::style)
                .toList();

        Events.raise(new ProfileWroteEvent(memberId, profileWriteRequest));
        Events.raise(new OptionWroteEvent(memberId, optionWriteRequest));

        Events.raise(new HobbyWroteEvent(memberId, hobbyNames));
        Events.raise(new StyleWroteEvent(memberId, styleNames));
    }
}
